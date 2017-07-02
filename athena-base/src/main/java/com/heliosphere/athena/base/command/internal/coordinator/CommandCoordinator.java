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
package com.heliosphere.athena.base.command.internal.coordinator;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.command.internal.exception.CommandException;
import com.heliosphere.athena.base.command.internal.exception.CommandNotFoundException;
import com.heliosphere.athena.base.command.internal.interpreter.ICommandInterpreter;
import com.heliosphere.athena.base.command.internal.processor.ExecutableCommand;
import com.heliosphere.athena.base.command.internal.protocol.ICommandProtocolType;
import com.heliosphere.athena.base.terminal.CommandTerminal;

/**
 * An actor responsible to execute pre-implemented commands when they are issued (generally on a terminal).
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse - Heliosphere</a>
 * @version 1.0.0
 */
public class CommandCoordinator implements ICommandCoordinator
{
	/**
	 * Pre-defined command processors.
	 */
	private Map<Enum<? extends ICommandProtocolType>, ExecutableCommand> executables = new HashMap<>();

	/**
	 * Command terminal.
	 */
	private CommandTerminal terminal;

	/**
	 * Create a new command coordinator.
	 * <hr>
	 * @param terminal Command terminal.
	 */
	public CommandCoordinator(final CommandTerminal terminal)
	{
		this.terminal = terminal;
	}

	@Override
	public final ICommandInterpreter getCommandInterpreter()
	{
		return terminal.getInterpreter();
	}

	@SuppressWarnings("nls")
	@Override
	public final void registerExecutable(final ExecutableCommand executable)
	{
		if (executable == null)
		{
			terminal.appendToPane("Executable command cannot be null!\n", Color.ORANGE);
		}
		else if (executables.get(executable.getProtocolType()) != null)
		{
			terminal.appendToPane("A command definition is already registered for: ", Color.ORANGE);
			terminal.appendToPane(executable.getProtocolType() + "\n", Color.ORANGE);
		}
		else
		{
			executables.put(executable.getProtocolType(), executable);
		}
	}

	@Override
	@SuppressWarnings("nls")
	public final void execute(final ICommand command) throws CommandNotFoundException
	{
		// Do we have a command processor for this command type?
		ExecutableCommand processor = executables.get(command.getMetadata().getProtocolType());
		if (processor != null)
		{
			try
			{
				// Execute the command through the command processor.
				processor.execute(terminal, command);
			}
			catch (CommandException e)
			{
				terminal.appendToPane(String.format("[ERROR] %1s\n", e.getMessage()), Color.ORANGE);
			}
		}
		else
		{
			throw new CommandNotFoundException();
		}
	}
}