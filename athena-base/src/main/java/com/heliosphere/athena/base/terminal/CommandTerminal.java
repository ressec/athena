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

import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.file.internal.FileException;

import lombok.NonNull;

/**
 * Provides a command terminal (a console being able to process commands) based on {@code Text IO}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 * @see ICommand
 */
public class CommandTerminal extends AbstractTerminal
{
	/**
	 * Creates a new basic terminal.
	 */
	public CommandTerminal()
	{
		super();
	}

	/**
	 * Creates a new basic terminal given the path name of an XMl file containing commands to register.
	 * <hr>
	 * @param pathname XML path name containing the commands to register. 
	 * @throws FileException In case an error occurred while trying to access the file.
	 */
	public CommandTerminal(final @NonNull String pathname) throws FileException
	{
		super(pathname);
	}
}
