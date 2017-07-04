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
import java.util.Collections;
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
import com.heliosphere.athena.base.command.internal.exception.CommandInitializationException;
import com.heliosphere.athena.base.command.internal.interpreter.ICommandInterpreter;
import com.heliosphere.athena.base.command.internal.protocol.ICommandCategoryType;
import com.heliosphere.athena.base.command.internal.protocol.ICommandDomainType;
import com.heliosphere.athena.base.command.internal.protocol.ICommandGroupType;
import com.heliosphere.athena.base.command.internal.protocol.ICommandProtocolType;
import com.heliosphere.athena.base.command.protocol.DefaultCommandCategoryType;
import com.heliosphere.athena.base.exception.InvalidArgumentException;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;

/**
 * Represents a command interpreter that is responsible to interpret a text entered on a command-line 
 * or a terminal and transform it to a command.
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
	private final String COMMAND_REGEXP = "^\\s*+((\\*|/|#|%|.)\\s*+([a-zA-Z0-9_]*))";

	/**
	 * Collection of commands known by the command interpreter (grouped by command protocol type).
	 */
	private Map<Enum<? extends ICommandProtocolType>, ICommandMetadata> commands = null;

	/**
	 * Working copy of the original text command.
	 */
	private String copy = null;

	/**
	 * Command protocol.
	 */
	private Enum<? extends ICommandProtocolType> protocol;

	/**
	 * Creates a new command interpreter.
	 */
	public CommandInterpreter()
	{
		commands = new HashMap<>();
	}

	@Override
	public final List<ICommandMetadata> getCommandDefinitions()
	{
		return Collections.unmodifiableList(new ArrayList<>(commands.values()));
	}

	@SuppressWarnings("nls")
	@Override
	public final void registerCommand(final @NonNull ICommandMetadata metadata) throws CommandInitializationException
	{
		// Is the command protocol type declared at the command level or at the parameter level?
		if (metadata.getProtocolType() != null)
		{
			// Declared at command level.
			if (commands.get(metadata.getProtocolType()) == null)
			{
				commands.put(metadata.getProtocolType(), metadata);
			}
			else
			{
				throw new CommandInitializationException("A command is already defined for protocol type: " + metadata.getProtocolType());
			}
		}
		else
		{
			// Declared at parameter level.
			for (ICommandParameterMetadata parameter : metadata.getParameters())
			{
				if (commands.get(parameter.getProtocolType()) == null)
				{
					commands.put(parameter.getProtocolType(), metadata);
				}
				else
				{
					throw new CommandInitializationException("A command is already defined for protocol type: " + parameter.getProtocolType());
				}
			}
		}
	}

	@Override
	public final void registerCommands(final @NonNull List<ICommandMetadata> metadata) throws CommandInitializationException
	{
		for (ICommandMetadata definition : metadata)
		{
			registerCommand(definition);
		}
	}

	/**
	 * Finds commands matching the given command category.
	 * <hr>
	 * @param category Command category.
	 * @return List of commands.
	 */
	protected final List<ICommandMetadata> findByCategory(final @NonNull Enum<? extends ICommandCategoryType> category)
	{
		List<ICommandMetadata> result = new ArrayList<>();

		for (ICommandMetadata command : commands.values())
		{
			if (command.getProtocolType() != null)
			{
				if (((ICommandProtocolType) command.getProtocolType()).getCategory() == category)
				{
					result.add(command);
				}
			}
			else
			{
				for (ICommandParameterMetadata parameter : command.getParameters())
				{
					if (parameter.getProtocolType() != null)
					{
						if (((ICommandProtocolType) parameter.getProtocolType()).getCategory() == category)
						{
							result.add(command);
							break; // Only one entry when command protocol is defined at parameter level!
						}
					}
				}
			}
		}

		return result;
	}

	/**
	 * Finds commands matching the given command group.
	 * <hr>
	 * @param group Command group.
	 * @return List of commands.
	 */
	protected final List<ICommandMetadata> findByGroup(final @NonNull Enum<? extends ICommandGroupType> group)
	{
		List<ICommandMetadata> result = new ArrayList<>();

		for (ICommandMetadata command : commands.values())
		{
			if (command.getProtocolType() != null)
			{
				if (((ICommandProtocolType) command.getProtocolType()).getGroup() == group)
				{
					result.add(command);
				}
			}
			else
			{
				for (ICommandParameterMetadata parameter : command.getParameters())
				{
					if (parameter.getProtocolType() != null)
					{
						if (((ICommandProtocolType) parameter.getProtocolType()).getGroup() == group)
						{
							result.add(command);
							break; // Only one entry when command protocol is defined at parameter level!
						}
					}
				}
			}
		}

		return result;
	}

	/**
	 * Finds commands matching the given command domain.
	 * <hr>
	 * @param domain Command domain.
	 * @return List of commands.
	 */
	protected final List<ICommandMetadata> findByDomain(final @NonNull Enum<? extends ICommandDomainType> domain)
	{
		List<ICommandMetadata> result = new ArrayList<>();

		for (ICommandMetadata command : commands.values())
		{
			if (command.getProtocolType() != null)
			{
				if (((ICommandProtocolType) command.getProtocolType()).getDomain() == domain)
				{
					result.add(command);
				}
			}
			else
			{
				for (ICommandParameterMetadata parameter : command.getParameters())
				{
					if (parameter.getProtocolType() != null)
					{
						if (((ICommandProtocolType) parameter.getProtocolType()).getDomain() == domain)
						{
							result.add(command);
							break; // Only one entry when command protocol is defined at parameter level!
						}
					}
				}
			}
		}

		return result;
	}

	/**
	 * Retrieves a command definition based on its name or alias.
	 * <hr>
	 * @param protocol Command protocol type.
	 * @param nameOrAlias  Name or alias of the command.
	 * @return Command definition if found, otherwise {@code null} is returned.
	 */
	protected final ICommandMetadata getCommandByProtocol(final @NonNull Enum<? extends ICommandProtocolType> protocol, final @NonNull String nameOrAlias)
	{
		ICommandMetadata command = commands.get(protocol);
		if (command != null)
		{
			if (command.getName().equals(nameOrAlias))
			{
				return command;
			}

			for (String alias : command.getAliases())
			{
				if (alias.equals(nameOrAlias))
				{
					return command;
				}
			}
		}

		return null;
	}

	/**
	 * Retrieves a command definition based on its name or alias.
	 * <hr>
	 * @param category Command protocol category type.
	 * @param nameOrAlias  Name or alias of the command.
	 * @return Command definition if found, otherwise {@code null} is returned.
	 */
	protected final ICommandMetadata getCommandByCategory(final @NonNull Enum<? extends ICommandCategoryType> category, final @NonNull String nameOrAlias)
	{
		List<ICommandMetadata> list = findByCategory(category);

		for (ICommandMetadata command : list)
		{
			if (command != null)
			{
				if (command.getName().equals(nameOrAlias))
				{
					return command;
				}

				for (String alias : command.getAliases())
				{
					if (alias.equals(nameOrAlias))
					{
						return command;
					}
				}
			}
		}

		return null;
	}

	@SuppressWarnings("nls")
	@Override
	public final ICommand interpret(final @NonNull String text) throws CommandException
	{
		ICommand command = null;

		copy = text.trim();

		ICommandMetadata definition = extractCommand(text);
		if (definition != null)
		{
			List<ICommandParameter> parameters = extractParameters(definition);
			if (definition.getParameters().size() > 0 && parameters.size() == 0 && protocol == null)
			{
				throw new CommandException("Unable to interpret command from: " + text);
			}
			if (parameters.isEmpty() || protocol == null)
			{
				protocol = definition.getProtocolType();
			}

			command = new Command(protocol, text);
			command.setMetadata(definition);
			for (ICommandParameter parameter : parameters)
			{
				command.addParameter(parameter);
			}
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
	 * @param original Original text to interpret as a command.
	 * @return Command definition or {@code null} if no command definition has been identified.
	 * @throws CommandException Thrown in case an error occurred while extracting the command definition.
	 */
	@SuppressWarnings("nls")
	protected ICommandMetadata extractCommand(final String original) throws CommandException
	{
		String name = null;
		Enum<? extends ICommandCategoryType> category = null;
		ICommandMetadata definition = null;

		Pattern pattern = Pattern.compile(COMMAND_REGEXP);
		Matcher matcher = pattern.matcher(copy);

		// Find the command itself ; i.e. category + command
		if (matcher.find()) 
		{
			try
			{
				String value = matcher.group().trim();
				
				// A command cannot have blank characters!
				value = value.replace(" ", "");
				category = DefaultCommandCategoryType.fromPrefix(value.substring(0, 1));
				name = value.substring(1, value.length());
				definition = getCommandByCategory(category, name);
				if (definition == null) 
				{
					throw new CommandException("No command definition found matching: " + original);
				}
				protocol = definition.getProtocolType();
			}
			catch (Exception e)
			{
				throw new CommandException(e);
			}

			// Reduce the original text by removing the found command pattern.
			copy = copy.replace(matcher.group(1).trim(), "").trim();
		}
		
		return definition;
	}

	/**
	 * Extract the parameters and their values.
	 * <hr>
	 * @param definition Command definition.
	 * @return Command containing the extracted parameters.
	 */
	protected List<ICommandParameter> extractParameters(final ICommandMetadata definition)
	{
		boolean finish = false;
		List<ICommandParameter> parameters = new ArrayList<>();
		ICommandParameter parameter = null;

		while (finish == false)
		{
			parameter = getNextParameter(definition);
			if (parameter != null)
			{
				parameters.add(parameter);
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

		return parameters;
	}

	/**
	 * Get the next parameter.
	 * <hr>
	 * @param definition Command definition.
	 * @return Command parameter or {@code null} if no parameter has been identified.
	 */
	@SuppressWarnings("nls")
	protected ICommandParameter getNextParameter(final ICommandMetadata definition)
	{
		ICommandParameter parameter = null;
		Pattern pattern = null;
		Matcher matcher = null;
		String tag = null;
		String value = null;

		// Get the next parameter present in the working copy of the command text.
		for (ICommandParameterMetadata metadata : definition.getParameters())
		{
			if (!metadata.getTag().isEmpty()) 
			{
				// Does this parameter exist in the command text?
				pattern = Pattern.compile(metadata.getRegExp());
				matcher = pattern.matcher(copy);

				if (matcher.find())
				{
					tag = matcher.group(1);
					for (int index = 2; index <= matcher.groupCount(); index++)
					{
						if (matcher.group(index) != null)
						{
							value = matcher.group(index).trim();
						}
					}
				}

				if (tag != null)
				{
					tag = tag.replace("=", "");
					if (tag.equals(metadata.getTag())) // We found a matching parameter!
					{
						// Let's extract set the parameter.
						parameter = new CommandParameter(matcher.group(0), metadata, value);
						protocol = metadata.getProtocolType();

						// Remove the parameter text from the command text.
						copy = copy.replace(matcher.group(0), "").trim();
						return parameter;
					}
				}			
			}
			else
			{
				parameter = new CommandParameter(copy, metadata, copy);

				// Remove the parameter text from the command text.
				copy = copy.replace(copy, "").trim();
				if (metadata.getProtocolType() != null) 
				{
					protocol = metadata.getProtocolType();
				}
				
				return parameter;
			}
		}

		return parameter;
	}
}
