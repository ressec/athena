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
package com.heliosphere.athena.base.command.processor;

import java.util.ArrayList;
import java.util.List;

import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.command.internal.ICommandMetadata;
import com.heliosphere.athena.base.command.internal.ICommandParameter;
import com.heliosphere.athena.base.command.internal.ICommandParameterMetadata;
import com.heliosphere.athena.base.command.internal.exception.CommandException;
import com.heliosphere.athena.base.command.internal.processor.AbstractCommandProcessor;
import com.heliosphere.athena.base.command.internal.protocol.ICommandCategoryType;
import com.heliosphere.athena.base.command.internal.protocol.ICommandCodeType;
import com.heliosphere.athena.base.command.protocol.DefaultCommandCodeType;

/**
 * Provides a concrete implementation for the standard {@code Help} command.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public final class HelpCommandProcessor extends AbstractCommandProcessor
{
	/**
	 * Command code type.
	 */
	public final static Enum<? extends ICommandCodeType> COMMAND_TYPE = DefaultCommandCodeType.HELP;

	/**
	 * Command code type.
	 */
	private Enum<? extends ICommandCodeType> type;

	/**
	 * Command definitions.
	 */
	private List<ICommandMetadata> definitions;

	/**
	 * Creates a new command processor for a given command code type.
	 * <hr>
	 * @param type Command type.
	 * @param definitions Command definitions.
	 */
	public HelpCommandProcessor(final Enum<? extends ICommandCodeType> type, final List<ICommandMetadata> definitions)
	{
		this.type = type;
		this.definitions = definitions;
	}

	@SuppressWarnings("nls")
	@Override
	public final Object execute(final ICommand command) throws CommandException
	{
		if (command != null)
		{
			if (command.getParameters() == null)
			{
				return extractAllCommands(command);
			}

			for (ICommandParameter parameter : command.getParameters())
			{
				if (parameter.getMetadata().getName().equals("category"))
				{
					return extractCommandCategories(command);
				}
				if (parameter.getMetadata().getName().equals("set"))
				{
					return extractCommandsForCategory(command);
				}
				if (parameter.getMetadata().getName().equals("name"))
				{
					return extractCommandHelp(command);
				}
			}
		}

		return "Unable to process the command!";
	}

	/**
	 * 
	 * @param command
	 * @return
	 */
	@SuppressWarnings("nls")
	private final List<String> extractAllCommands(final ICommand command)
	{
		List<String> results = new ArrayList<>();

		results.add(" ");
		results.add("Available commands are:");
		for (ICommandMetadata metadata : definitions)
		{
			results.add(String.format(" %1$-12s %2$s", metadata.getName(), metadata.getDescription()));
		}

		results.add("\r\n");

		return results;
	}

	/**
	 * 
	 * @param command
	 * @return
	 */
	@SuppressWarnings("nls")
	private final List<String> extractCommandCategories(final ICommand command)
	{
		List<String> results = new ArrayList<>();

		results.add(" ");
		results.add("Command categories are:");
		Enum<?>[] enums = command.getMetadata().getProtocolCategory().getDeclaringClass().getEnumConstants();
		for (int i = 0; i < enums.length; i++)
		{
			results.add(String.format(" %1s %2s", ((ICommandCategoryType) enums[i]).getPrefix(), enums[i].name()));
		}

		results.add("\r\n");

		return results;
	}

	/**
	 * 
	 * @param command
	 * @return
	 */
	private final List<String> extractCommandsForCategory(final ICommand command)
	{
		List<String> results = new ArrayList<>();

		//TODO Implement!

		results.add("\r\n");

		return results;
	}

	/**
	 * Returns the detailed help for the given command.
	 * <hr>
	 * @param command {@link ICommand}.
	 * @return List of text lines composing the detailed help for the command.
	 */
	@SuppressWarnings("nls")
	private final List<String> extractCommandHelp(final ICommand command)
	{
		List<String> results = new ArrayList<>();
		ICommandMetadata other = null;

		other = getCommandDefinitionByName((String) command.getParameter("name").getValue());
		if (other != null)
		{
			results.add(" ");
			results.add(String.format("Help on command '%1s': %2s", other.getName(), other.getDescription()));
			results.add(" ");

			// Aliases.
			if (!other.getAliases().isEmpty())
			{
				results.add("Aliases:");
				for (String alias : other.getAliases())
				{
					results.add("|   " + alias);
				}
			}
			else
			{
				results.add("No alias defined!");
			}

			results.add(" ");

			// Parameters.
			if (!other.getParameters().isEmpty())
			{
				results.add("Parameters:");
				for (ICommandParameterMetadata parameter : other.getParameters())
				{
					results.add(" - '" + parameter.getName() + "' " + parameter.getDescription());
					results.add(" |__ RegExp   : " + parameter.getRegExp());

					// Examples.
					if (parameter.getExamples() != null && !parameter.getExamples().isEmpty())
					{
						results.add(" |__ Examples : ");
						for (String example : parameter.getExamples())
						{
							results.add("     |_ " + example);
						}
					}
					else
					{
						results.add(" |__ No example defined!");
					}

					results.add(" ");
				}
			}
			else
			{
				results.add("No parameter defined!");
			}

			results.add("\r\n");
		}

		return results;
	}

	/**
	 * Retrieves a command definition (metadata) based on a given name.
	 * <hr>
	 * @param name Name of the command to retrieve.
	 * @return {@link ICommandMetadata} if found, {@code null} otherwise.
	 */
	private final ICommandMetadata getCommandDefinitionByName(final String name)
	{
		for (ICommandMetadata command : definitions)
		{
			if (command.getName().equalsIgnoreCase(name))
			{
				return command;
			}
		}

		return null;
	}
}
