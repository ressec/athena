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
import com.heliosphere.athena.base.command.internal.type.CommandCategoryType;
import com.heliosphere.athena.base.file.internal.FileException;

import lombok.NonNull;

public class Terminal extends AbstractTerminal
{
	/**
	 * Creates a new basic terminal.
	 */
	public Terminal()
	{
		super();
	}

	/**
	 * Creates a new basic terminal given the path name of an XMl file containing commands to register.
	 * <hr>
	 * @param pathname XML path name containing the commands to register. 
	 * @throws FileException In case an error occurred while trying to access the file.
	 */
	public Terminal(final @NonNull String pathname) throws FileException
	{
		super(pathname);
	}

	@Override
	public final void process(final @NonNull ICommand command)
	{
		switch ((CommandCategoryType) command.getMetadata().getCategory())
		{
			case NORMAL:
				getTerminal().println("Command [category=" + command.getMetadata().getCategory() + ", group=" + command.getMetadata().getGroup() + ", name=" + command.getMetadata().getName() + "] has been processed.");
				break;

			case ADMINISTRATION:
			case DEBUG:
			case SUPER_ADMINISTRATION:
			case SYSTEM:

			default:
				break;
		}
	}
}
