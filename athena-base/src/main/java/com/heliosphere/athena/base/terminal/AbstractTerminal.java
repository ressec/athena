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

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import com.heliosphere.athena.base.command.file.xml.XmlCommandFile;
import com.heliosphere.athena.base.command.internal.CommandException;
import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.command.internal.interpreter.CommandInterpreter;
import com.heliosphere.athena.base.command.internal.interpreter.ICommandInterpreter;
import com.heliosphere.athena.base.file.internal.FileException;

import lombok.NonNull;

/**
 * Provides an abstract implementation of a terminal (a console being able to process commands) based on {@code Text IO}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public abstract class AbstractTerminal implements Runnable
{
	/**
	 * Running state.
	 */
	private static volatile boolean RUNNING = false;

	/**
	 * Wait interval.
	 */
	private static volatile int WAIT_INTERVAL = 100;

	/**
	 * Prompt.
	 */
	@SuppressWarnings("nls")
	private final static String PROMPT = "Command:";

	/**
	 * Text IO entry point.
	 */
	protected TextIO io = null;

	/**
	 * Text entered on the console.
	 */
	protected String text = null;

	/**
	 * Command interpreter.
	 */
	protected ICommandInterpreter interpreter = null;

	/**
	 * Creates a new abstract terminal.
	 */
	public AbstractTerminal()
	{
		io = TextIoFactory.getTextIO();
		interpreter = new CommandInterpreter();
	}

	/**
	 * Creates a new abstract terminal and registers a set of commands given a {@code XML file}.
	 * <hr>
	 * @param pathname XML file path and name containing the command definitions.
	 * @throws FileException Thrown in case an error occurred while trying to access the file.
	 */
	public AbstractTerminal(String pathname) throws FileException
	{
		this();
		registerCommands(pathname);
	}

	/**
	 * Returns the underlying text terminal.
	 * <hr>
	 * @return Text terminal.
	 */
	@SuppressWarnings("rawtypes")
	protected final TextTerminal getTerminal()
	{
		return io.getTextTerminal();
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
		interpreter.registerCommands(file.getContent());
	}

	/**
	 * Processes a command.
	 * <hr>
	 * @param command Command to process.
	 */
	public abstract void process(ICommand command);

	@SuppressWarnings("nls")
	@Override
	public final void run()
	{
		ICommand command = null;

		while (RUNNING)
		{
			try
			{
				Thread.sleep(WAIT_INTERVAL); // Value is expressed in milliseconds.
				text = io.newStringInputReader().read(PROMPT);

				try
				{
					command = interpreter.interpret(text);
					process(command);
				}
				catch (CommandException e)
				{
					getTerminal().println(e.getMessage());
				}
			}
			catch (InterruptedException e)
			{
				getTerminal().println("Terminal interrupted!");
			}
		}
	}

	public final void start()
	{
		if (!RUNNING)
		{
			Thread thread = new Thread(this);
			RUNNING = true;
			thread.start();
		}
	}
}
