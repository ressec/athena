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
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.file.internal.FileException;

import lombok.NonNull;

/**
 * Provides an output only terminal based on {@code Text IO}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 * @see ICommand
 */
public final class OutputTerminal extends AbstractTerminal
{
	/**
	 * Received text to output on the console.
	 */
	private Queue<String> queue = new ConcurrentLinkedQueue<>();

	/**
	 * Creates a new basic output terminal.
	 * <hr>
	 * @param name Terminal's session name. 
	 * @param terminalConfigurationFilename Terminal configuration file name. 
	 * @throws FileException In case an error occurred while trying to access the file.
	 */
	public OutputTerminal(final @NonNull String name, final @NonNull String terminalConfigurationFilename) throws FileException
	{
		super(name, terminalConfigurationFilename);

		// Disable text input!
		io.getFrame().setEnabled(false);
	}

	@SuppressWarnings("nls")
	@Override
	public final void run()
	{
		while (status != TerminalStatusType.STOPPED)
		{
			if (status == TerminalStatusType.RUNNING)
			{
				String text = queue.peek();
				if (text != null)
				{
					getTerminal().println(text);
				}
			}

			try
			{
				Thread.sleep(WAIT_INTERVAL); // Value is expressed in milliseconds.
			}
			catch (InterruptedException e)
			{
				getTerminal().println("Terminal interrupted! Reason is: " + e.getMessage());
			}
		}
	}

	/**
	 * Adds some text to be written to the console.
	 * <hr>
	 * @param text Text to add.
	 */
	public final void addText(final String text)
	{
		queue.offer(text);
	}

	/**
	 * Prints on the console a {@code say} message.
	 * <hr>
	 * @param timestamp Time stamp of the message.
	 * @param user User name being the sender of the message.
	 * @param text Text of the message.
	 */
	@SuppressWarnings("nls")
	public final void printSay(final String timestamp, final String user, final String text)
	{
		appendToPane(timestamp, Color.LIGHT_GRAY);
		appendToPane(" " + user, Color.CYAN);
		appendToPane(" " + text + "\n", Color.ORANGE);

		resume();
	}
}
