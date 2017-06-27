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

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.beryx.textio.TextTerminal;
import org.beryx.textio.swing.SwingTextTerminal;

import com.heliosphere.athena.base.command.file.xml.XmlCommandFile;
import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.command.internal.ICommandListener;
import com.heliosphere.athena.base.command.internal.exception.CommandException;
import com.heliosphere.athena.base.command.internal.interpreter.ICommandInterpreter;
import com.heliosphere.athena.base.command.interpreter.CommandInterpreter;
import com.heliosphere.athena.base.file.internal.FileException;
import com.heliosphere.athena.base.file.internal.resource.IResource;
import com.heliosphere.athena.base.file.internal.resource.Resource;

import jline.Terminal;
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
	 * Terminal state.
	 */
	private TerminalStatusType status = TerminalStatusType.UNKNOWN;

	/**
	 * Wait interval.
	 */
	private static volatile int WAIT_INTERVAL = 100;

	/**
	 * Terminal thread.
	 */
	private volatile Thread thread = null;

	/**
	 * Initial command prompt.
	 */
	@SuppressWarnings("nls")
	private final static String PROMPT = "Command (unregistered):> ";

	/**
	 * Current command prompt.
	 */
	private String prompt = PROMPT;

	/**
	 * Command listeners.
	 */
	private List<ICommandListener> listeners = new ArrayList<>();

	/**
	 * Text IO entry point.
	 */
	protected SwingTextTerminal io = null;

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
		io = new SwingTextTerminal();
		interpreter = new CommandInterpreter();
	}

	/**
	 * Creates a new abstract terminal and registers a set of commands given a {@code XML file}.
	 * <hr>
	 * @param name Terminal's session name.
	 * @param terminalConfigurationFilename Terminal configuration file name.
	 * @param commandFilename XML file path and name containing the command definitions.
	 * @throws FileException Thrown in case an error occurred while trying to access the file.
	 */
	public AbstractTerminal(String name, String terminalConfigurationFilename, String commandFilename) throws FileException
	{
		this();

		Properties properties = new Properties();
		IResource resource = new Resource(terminalConfigurationFilename);

		try (FileInputStream input = new FileInputStream(resource.getFile()))
		{
			properties.load(input);
			io.initProperties(properties);
			io.getFrame().setTitle(name);
		}
		catch (Exception e)
		{
			throw new FileException(e);
		}

		registerCommands(commandFilename);
	}

	/**
	 * Returns the underlying text terminal.
	 * <hr>
	 * @return Text terminal.
	 */
	@SuppressWarnings("rawtypes")
	public final TextTerminal getTerminal()
	{
		return io;
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
		interpreter.registerCommands(file.getContent());
	}

	/**
	 * Processes a command.
	 * <hr>
	 * @param command Command to process.
	 */
	public final void process(final ICommand command)
	{
		for (ICommandListener listener : listeners)
		{
			listener.onCommand(command);
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
					getTerminal().println(e.getMessage());
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

	/**
	 * Starts the terminal.
	 */
	public final void start()
	{
		if (status == TerminalStatusType.UNKNOWN || status == TerminalStatusType.STOPPED)
		{
			thread = new Thread(this);
			status = TerminalStatusType.RUNNING;
			thread.start();
		}
	}

	/**
	 * Stops the terminal.
	 */
	public final void stop()
	{
		if (status == TerminalStatusType.RUNNING)
		{
			status = TerminalStatusType.STOPPED;
		}
	}

	/**
	 * resumes the terminal.
	 */
	public final void resume()
	{
		if (status == TerminalStatusType.PAUSED)
		{
			status = TerminalStatusType.RUNNING;
		}
	}

	/**
	 * Pauses the terminal.
	 */
	public final void pause()
	{
		if (status == TerminalStatusType.RUNNING)
		{
			status = TerminalStatusType.PAUSED;
		}
	}
}
