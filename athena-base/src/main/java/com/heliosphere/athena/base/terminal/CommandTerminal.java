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
package com.heliosphere.athena.base.terminal;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.heliosphere.athena.base.command.file.xml.XmlCommandFile;
import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.command.internal.ICommandListener;
import com.heliosphere.athena.base.command.internal.coordinator.CommandCoordinator;
import com.heliosphere.athena.base.command.internal.coordinator.ICommandCoordinator;
import com.heliosphere.athena.base.command.internal.exception.CommandException;
import com.heliosphere.athena.base.command.internal.exception.CommandInitializationException;
import com.heliosphere.athena.base.command.internal.exception.CommandNotFoundException;
import com.heliosphere.athena.base.command.internal.interpreter.ICommandInterpreter;
import com.heliosphere.athena.base.command.interpreter.CommandInterpreter;
import com.heliosphere.athena.base.file.internal.FileException;

import jline.Terminal;
import lombok.NonNull;

/**
 * Provides a command terminal (a console being able to process commands) based on {@code Text IO}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 * @see ICommand
 */
public final class CommandTerminal extends AbstractTerminal
{
	/**
	 * Initial command prompt.
	 */
	@SuppressWarnings("nls")
	private final static String PROMPT = "(unregistered):> ";

	/**
	 * Current command prompt.
	 */
	private String prompt = PROMPT;

	/**
	 * Command listeners.
	 */
	private List<ICommandListener> listeners = new ArrayList<>();

	/**
	 * Text entered on the console.
	 */
	protected String text = null;

	/**
	 * Command interpreter.
	 */
	protected ICommandInterpreter interpreter = null;

	/**
	 * Command coordinator.
	 */
	protected ICommandCoordinator coordinator = null;

	/**
	 * Creates a new basic terminal given the path name of an XMl file containing commands to register.
	 * <hr>
	 * @param name Terminal's window title. 
	 * @param config Terminal configuration file name. 
	 * @param commandFilename XML path name containing the commands to register. 
	 * @throws FileException In case an error occurred while trying to access the file.
	 */
	public CommandTerminal(final @NonNull String name, final @NonNull String config, final @NonNull String commandFilename) throws FileException
	{
		super(name, config);

		interpreter = new CommandInterpreter();
		registerCommands(commandFilename);

		coordinator = new CommandCoordinator(this);
	}

	/**
	 * Returns the command interpreter used by this {@link Terminal}.
	 * <hr>
	 * @return {@link ICommandInterpreter}. 
	 */
	public final ICommandInterpreter getInterpreter()
	{
		return interpreter;
	}

	/**
	 * Returns the command coordinator used by this {@link Terminal}.
	 * <hr>
	 * @return {@link ICommandCoordinator}. 
	 */
	public final ICommandCoordinator getCoordinator()
	{
		return coordinator;
	}

	/**
	 * Adds a command listener.
	 * <hr>
	 * @param listener Command listener to add.
	 */
	public final void registerListener(final @NonNull ICommandListener listener)
	{
		listeners.add(listener);
	}

	/**
	 * Registers a set of command metadata (definitions) contained in a {@code XML} file.
	 * <hr>
	 * @param pathname XML command file path name.
	 * @throws FileException Thrown in case an error occurred while trying to access the file.
	 */
	public final void registerCommands(final @NonNull String pathname) throws FileException
	{
		XmlCommandFile file = new XmlCommandFile(pathname);
		file.load();

		try
		{
			interpreter.registerCommands(file.getContent());
		}
		catch (CommandInitializationException e)
		{
			throw new FileException(e);
		}
	}

	/**
	 * Processes a command.
	 * <hr>
	 * @param command Command to process.
	 */
	@SuppressWarnings("unused")
	public final void process(final ICommand command)
	{
		try
		{
			// Does the command can be executed by the command coordinator?
			coordinator.execute(command);
		}
		catch (CommandNotFoundException e)
		{
			// No, then delegates the execution to a registered listener.
			for (ICommandListener listener : listeners)
			{
				listener.onCommand(command);
			}
		}
	}

	/**
	 * Sets the new command prompt.
	 * <hr>
	 * @param prompt Command prompt to set.
	 */
	public final void setPrompt(final @NonNull String prompt)
	{
		this.prompt = prompt;
	}

	@SuppressWarnings("nls")
	@Override
	public final void run()
	{
		ICommand command = null;

		while (status != TerminalStatusType.STOPPED)
		{
			if (status == TerminalStatusType.RUNNING)
			{
				io.print(prompt);
				text = io.read(false);

				try
				{
					command = interpreter.interpret(text);
					process(command);
				}
				catch (CommandException e)
				{
					printException(e);
				}
			}

			try
			{
				Thread.sleep(WAIT_INTERVAL); // Value is expressed in milliseconds.
			}
			catch (InterruptedException e)
			{
				getTerminal().println("Terminal interrupted!");
			}
		}
	}

	@SuppressWarnings("nls")
	public final void printException(final Exception e)
	{
		appendToPane("[error] " + e.getMessage() + "\n\n", Color.ORANGE);
		resume();
	}

	//	public final void printInfo(final ITerminalMessage message)
	//	{
	//
	//	}
}
