/*
 * Copyright(c) 2017 - Heliosphere Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Heliosphere's project which is licensed under the 
 * Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package com.heliosphere.athena.base.command.interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.heliosphere.athena.base.command.Command;
import com.heliosphere.athena.base.command.CommandParameter;
import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.command.internal.ICommandMetadata;
import com.heliosphere.athena.base.command.internal.ICommandParameter;
import com.heliosphere.athena.base.command.internal.ICommandParameterMetadata;
import com.heliosphere.athena.base.command.internal.exception.CommandException;
import com.heliosphere.athena.base.command.internal.interpreter.ICommandInterpreter;
import com.heliosphere.athena.base.command.internal.protocol.ICommandCategoryType;
import com.heliosphere.athena.base.command.protocol.DefaultCommandCategoryType;
import com.heliosphere.athena.base.exception.InvalidArgumentException;
import com.heliosphere.athena.base.message.internal.protocol.IMessageType;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;

/**
 * Represents a command interpreter that is responsible to interpret a text entered on a command-line or a terminal and transform it to a command.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
@Log4j
public final class CommandInterpreter implements ICommandInterpreter
{
	/**
	 * Command regular expression.
	 */
	@SuppressWarnings("nls")
	private final String COMMAND_REGEXP = "^\\s*+((\\*|/|#|%|.)\\s*+([a-zA-Z0-9\\s]*)).*+$";

	/**
	 * Collection of commands known by the command interpreter (grouped by command category type).
	 */
	private Map<Enum<? extends ICommandCategoryType>, List<ICommandMetadata>> commands = null;

	/**
	 * Working copy of the original text command.
	 */
	private String copy = null;

	/**
	 * Creates a new command interpreter.
	 */
	public CommandInterpreter()
	{
		commands = new HashMap<>();
	}

	@Override
	public final void registerCommand(final @NonNull ICommandMetadata metadata)
	{
		List<ICommandMetadata> list = commands.get(metadata.getCategoryType());

		if (list == null)
		{
			list = new ArrayList<>();
		}
		else
		{
			if (list.contains(metadata))
			{
				return;
			}
		}

		// Validate the command.
		//validate(metadata);

		list.add(metadata);
		commands.put(metadata.getCategoryType(), list);
	}

	@Override
	public final void registerCommands(final @NonNull List<ICommandMetadata> metadata)
	{
		for (ICommandMetadata definition : metadata)
		{
			registerCommand(definition);
		}
	}

//	/**
//	 * Validates the command definition.
//	 * <hr>
//	 * @param metadata Command definition to validate.
//	 */
//	@SuppressWarnings({ "nls", "static-method" })
//	private void validate(final @NonNull ICommandMetadata metadata)
//	{
//		Class<?> protocolClass;
//
//		try
//		{
//			// Validate the message protocol provided.
//			protocolClass = Class.forName(metadata.getMessageProtocolClass());
//			Enum<? extends IMessageType> enumerated = ((IMessageType) (Enum<?>) protocolClass.getEnumConstants()[0]).fromString(metadata.getMessageProtocolEntry());
//			metadata.setMessageType(enumerated);
//		}
//		catch (ClassNotFoundException e)
//		{
//			log.error(String.format("Cannot register command: %1s due to: %2s", metadata.getName(), e.getMessage()));
//		}
//	}

	/**
	 * Finds commands matching the given command category.
	 * <hr>
	 * @param category Command category.
	 * @return List of commands.
	 */
	protected final List<ICommandMetadata> findByCategory(final @NonNull Enum<? extends ICommandCategoryType> category)
	{
		return commands.get(category);
	}

	/**
	 * Registers a set of command definitions.
	 * <hr>
	 * @param category Command category.
	 * @param nameOrAlias  Name or alias of the command.
	 * @return Command definition if found, otherwise {@code null} is returned.
	 */
	protected final ICommandMetadata getCommand(final @NonNull Enum<? extends ICommandCategoryType> category, final @NonNull String nameOrAlias)
	{
		List<ICommandMetadata> definitions = findByCategory(category);

		// Lookup by name.
		for (ICommandMetadata definition : definitions)
		{
			if (definition.getName().equals(nameOrAlias))
			{
				return definition;
			}
		}

		// Lookup by alias.
		for (ICommandMetadata definition : definitions)
		{
			for (String alias : definition.getAliases())
			{
				if (alias.equals(nameOrAlias))
				{
					return definition;
				}
			}
		}

		return null;
	}

	@SuppressWarnings("nls")
	@Override
	public final ICommand interpret(final @NonNull String text) throws CommandException
	{
		ICommand command = new Command(text);
		copy = text.trim();

		ICommandMetadata definition = extractCommand();
		if (definition != null)
		{
			command.setMetadata(definition);
			command = extractParameters(command);
		}
		else
		{
			throw new CommandException("Unknown command: " + text);
		}

		return command;
	}

	/**
	 * Extracts the command definition.
	 * <hr>
	 * @return Command definition or {@code null} if no command definition has been identified.
	 */
	@SuppressWarnings("nls")
	protected ICommandMetadata extractCommand()
	{
		String name = null;
		Enum<? extends ICommandCategoryType> category = null;
		ICommandMetadata definition = null;

		Pattern pattern = Pattern.compile(COMMAND_REGEXP);
		Matcher matcher = pattern.matcher(copy);

		if (matcher.matches() && matcher.groupCount() > 2) // Should contain the command prefix and its name.
		{
			/**
			 * Text   :'    /     who      -n      Candy-sAlbukerque -l 14-18'
			 * ---------------------------------------------------------------
			 * Group 0:'    /     who      -n      Candy-sAlbukerque -l 14-18'
			 * Group 1:'/     who'      
			 * Group 2:'/'
			 * Group 3:'who      '
			 */
			//			for (int i = 0; i <= matcher.groupCount(); i++)
			//			{
			//				System.out.println("Group " + i + " : " + matcher.group(i));
			//			}

			try
			{
				category = DefaultCommandCategoryType.fromPrefix(matcher.group(2).trim());
				name = matcher.group(3).trim();
				definition = getCommand(category, name);
			}
			catch (InvalidArgumentException e)
			{
				return null;
			}

			// Reduce the original text by removing the found command pattern.
			copy = copy.replace(matcher.group(1), "");
		}

		return definition;
	}

	/**
	 * Extract the parameters and their values.
	 * <hr>
	 * @param command Command in which to inject the extracted parameters.
	 * @return Command containing the extracted parameters.
	 */
	protected ICommand extractParameters(final @NonNull ICommand command)
	{
		boolean finish = false;
		ICommandParameter parameter = null;

		while (finish == false)
		{
			parameter = getNextParameter(command);
			if (parameter != null)
			{
				command.addParameter(parameter);
				if (copy.isEmpty())
				{
					finish = true;
				}
			}
			else
			{
				finish = true;
			}
		}

		return command;
	}

	/**
	 * Get the next parameter.
	 * <hr>
	 * @param command Command.
	 * @return Command parameter or {@code null} if no parameter has been identified.
	 */
	@SuppressWarnings("nls")
	protected ICommandParameter getNextParameter(final @NonNull ICommand command)
	{
		ICommandParameter parameter = null;
		Pattern pattern = null;
		Matcher matcher = null;
		String tag = null;
		List<String> values = new ArrayList<>();

		// Get the next parameter present in the working copy of the command text.
		for (ICommandParameterMetadata metadata : command.getMetadata().getParameters())
		{
			// Does this parameter exist in the command text?
			pattern = Pattern.compile(metadata.getRegExp());
			matcher = pattern.matcher(copy);

			if (matcher.find())
			{
				//				for (int i = 0; i <= matcher.groupCount(); i++)
				//				{
				//					System.out.println("Group " + i + " : " + matcher.group(i));
				//				}
				tag = matcher.group(1);
				for (int index = 2; index <= matcher.groupCount(); index++)
				{
					if (matcher.group(index) != null)
					{
						values.add(matcher.group(index).trim());
					}
				}
			}

			if (tag != null && tag.equals(metadata.getTag())) // We found a matching parameter!
			{
				// Let's extract set the parameter.
				parameter = new CommandParameter(matcher.group(0), metadata, values);

				// Remove the parameter text from the command text.
				copy = copy.replace(matcher.group(0), "").trim();
				return parameter;
			}
		}

		return parameter;
	}
}
