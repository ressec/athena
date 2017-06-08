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
package com.heliosphere.athena.base.command.internal.interpreter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.heliosphere.athena.base.command.internal.Command;
import com.heliosphere.athena.base.command.internal.CommandException;
import com.heliosphere.athena.base.command.internal.CommandParameter;
import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.command.internal.ICommandMetadata;
import com.heliosphere.athena.base.command.internal.ICommandParameter;
import com.heliosphere.athena.base.command.internal.ICommandParameterMetadata;
import com.heliosphere.athena.base.command.internal.type.CommandCategoryType;
import com.heliosphere.athena.base.command.internal.type.ICommandCategoryType;

import lombok.NonNull;

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

	/**
	 * Registers a new command definition.
	 * <hr>
	 * @param definition Command definition.
	 */
	public final void registerCommand(final @NonNull ICommandMetadata definition)
	{
		List<ICommandMetadata> list = commands.get(definition.getCategory());

		if (list == null)
		{
			list = new ArrayList<>();
		}
		else
		{
			if (list.contains(definition))
			{
				return;
			}
		}

		list.add(definition);
		commands.put(definition.getCategory(), list);
	}

	/**
	 * Registers a set of command definitions.
	 * <hr>
	 * @param definitions List of command definitions to register.
	 */
	public final void registerCommands(final @NonNull List<ICommandMetadata> definitions)
	{
		for (ICommandMetadata definition : definitions)
		{
			registerCommand(definition);
		}
	}

	/**
	 * Finds commands matching the given command category.
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
			for (int i = 0; i <= matcher.groupCount(); i++)
			{
				System.out.println("Group " + i + " : " + matcher.group(i));
			}

			category = CommandCategoryType.fromPrefix(matcher.group(2).trim());
			name = matcher.group(3).trim();
			definition = getCommand(category, name);

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
				for (int i = 0; i <= matcher.groupCount(); i++)
				{
					System.out.println("Group " + i + " : " + matcher.group(i));
				}
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
