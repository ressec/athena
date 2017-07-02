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

import java.awt.Color;
import java.util.List;

import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.command.internal.ICommandMetadata;
import com.heliosphere.athena.base.command.internal.ICommandParameter;
import com.heliosphere.athena.base.command.internal.ICommandParameterMetadata;
import com.heliosphere.athena.base.command.internal.exception.CommandException;
import com.heliosphere.athena.base.command.internal.processor.ExecutableCommand;
import com.heliosphere.athena.base.command.internal.protocol.ICommandCategoryType;
import com.heliosphere.athena.base.command.internal.protocol.ICommandProtocolType;
import com.heliosphere.athena.base.command.protocol.DefaultCommandProtocol;
import com.heliosphere.athena.base.terminal.CommandTerminal;

/**
 * Provides a concrete implementation for the standard {@code Help} command.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public final class HelpCommandProcessor implements ExecutableCommand
{
	/**
	 * Command protocol type.
	 */
	public final static Enum<? extends ICommandProtocolType> COMMAND_PROTOCOL_TYPE = DefaultCommandProtocol.HELP;

	/**
	 * Command definitions.
	 */
	private List<ICommandMetadata> definitions;

	/**
	 * Creates a new {@code help} command processor.
	 * <hr>
	 * @param definitions Command definitions (generally registered by a command interpreter).
	 */
	public HelpCommandProcessor(final List<ICommandMetadata> definitions)
	{
		this.definitions = definitions;
	}

	@SuppressWarnings("nls")
	@Override
	public final void execute(final CommandTerminal terminal, final ICommand command) throws CommandException
	{
		if (command != null)
		{
			if (command.getParameters() == null)
			{
				extractHelpForAllCommands(terminal, command);
				return;
			}

			for (ICommandParameter parameter : command.getParameters())
			{
				if (parameter.getMetadata().getName().equals("category"))
				{
					extractCommandCategories(terminal, command);
				}
				else if (parameter.getMetadata().getName().equals("set"))
				{
					extractCommandsForCategory(terminal, command);
				}
				else if (parameter.getMetadata().getName().equals("name"))
				{
					extractCommandHelp(terminal, command);
				}
			}
		}
		else
		{
			terminal.appendToPane("Command cannot be null!\n", Color.ORANGE);
		}
	}

	/**
	 * Extract the documentation for all registered commands.
	 * <hr>
	 * @param terminal Terminal where the output of the help command as to be written.
	 * @param command {@link ICommand} to execute.
	 */
	@SuppressWarnings("nls")
	private final void extractHelpForAllCommands(final CommandTerminal terminal, final ICommand command)
	{
		terminal.getTerminal().println();
		terminal.appendToPane("Available commands are: \n", Color.LIGHT_GRAY);
		for (ICommandMetadata metadata : definitions)
		{
			terminal.appendToPane(" [", Color.LIGHT_GRAY);
			terminal.appendToPane(String.format("%1$-15s", metadata.getName()), Color.ORANGE);
			terminal.appendToPane("] ", Color.LIGHT_GRAY);
			terminal.appendToPane(metadata.getDescription() + "\n", Color.WHITE);
		}
		terminal.getTerminal().println();
	}

	/**
	 * Extracts the documentation for all command categories. 
	 * <hr>
	 * @param terminal Terminal where the output should be written.
	 * @param command {@link ICommand} to execute.
	 */
	@SuppressWarnings({ "nls", "static-method" })
	private final void extractCommandCategories(final CommandTerminal terminal, final ICommand command)
	{
		terminal.getTerminal().println();
		terminal.appendToPane("Available command categories are: \n", Color.LIGHT_GRAY);
		Enum<?>[] enums = ((ICommandProtocolType) command.getMetadata().getProtocolType()).getCategory().getDeclaringClass().getEnumConstants();

		for (int i = 0; i < enums.length; i++)
		{
			terminal.appendToPane(" [", Color.LIGHT_GRAY);
			terminal.appendToPane(((ICommandCategoryType) enums[i]).getPrefix(), Color.ORANGE);
			terminal.appendToPane("] ", Color.LIGHT_GRAY);
			terminal.appendToPane(enums[i].name() + "\n", Color.WHITE);
		}
		terminal.getTerminal().println();
	}

	/**
	 * Extracts the documentation for a given command category. 
	 * <hr>
	 * @param terminal Terminal where the output should be written.
	 * @param command {@link ICommand} to execute.
	 */
	private final void extractCommandsForCategory(final CommandTerminal terminal, final ICommand command)
	{
	}

	/**
	 * Extracts the detailed help for a given command.
	 * <hr>
	 * @param terminal Terminal where the output should be written.
	 * @param command {@link ICommand} to execute.
	 */
	@SuppressWarnings("nls")
	private final void extractCommandHelp(final CommandTerminal terminal, final ICommand command)
	{
		ICommandMetadata other = null;

		other = getCommandDefinitionByName((String) command.getParameter("name").getValue());
		if (other != null)
		{
			terminal.appendToPane(" \n", Color.WHITE);
			terminal.appendToPane("-------------------- \n", Color.GREEN);

			terminal.appendToPane("Help on command: '", Color.WHITE);
			terminal.appendToPane(other.getName(), Color.YELLOW);
			terminal.appendToPane("' - ", Color.WHITE);
			terminal.appendToPane(other.getDescription() + "\n", Color.CYAN);

			terminal.appendToPane(" \n", Color.WHITE);

			// Aliases.
			if (!other.getAliases().isEmpty())
			{
				terminal.appendToPane("Aliases:\n", Color.WHITE);
				for (String alias : other.getAliases())
				{
					terminal.appendToPane("|   ", Color.WHITE);
					terminal.appendToPane(alias + "\n", Color.YELLOW);
				}
			}
			else
			{
				terminal.appendToPane("No alias defined!\n", Color.WHITE);
			}

			// Parameters.
			if (!other.getParameters().isEmpty())
			{
				terminal.appendToPane("Parameters:\n", Color.WHITE);
				for (ICommandParameterMetadata parameter : other.getParameters())
				{
					terminal.appendToPane(" - '", Color.WHITE);
					terminal.appendToPane(parameter.getName(), Color.YELLOW);
					terminal.appendToPane("' - ", Color.WHITE);
					terminal.appendToPane(parameter.getDescription() + "\n", Color.CYAN);
					terminal.appendToPane(" |__ RegExp   : ", Color.WHITE);
					terminal.appendToPane(parameter.getRegExp() + "\n", Color.YELLOW);

					// Examples.
					if (parameter.getExamples() != null && !parameter.getExamples().isEmpty())
					{
						terminal.appendToPane(" |__ Examples :\n", Color.WHITE);
						for (String example : parameter.getExamples())
						{
							terminal.appendToPane("     |_ ", Color.WHITE);
							terminal.appendToPane(example + "\n", Color.YELLOW);
						}
					}
					else
					{
						terminal.appendToPane(" |__ No example defined!\n", Color.WHITE);
					}
				}
			}
			else
			{
				terminal.appendToPane("No parmater defined!\n", Color.WHITE);
			}

			terminal.appendToPane("-------------------- \n", Color.GREEN);
			terminal.appendToPane(" \n", Color.WHITE);
		}
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

	@Override
	public final Enum<? extends ICommandProtocolType> getProtocolType()
	{
		return COMMAND_PROTOCOL_TYPE;
	}
}
