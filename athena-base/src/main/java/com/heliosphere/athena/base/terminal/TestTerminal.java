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

import com.heliosphere.athena.base.file.internal.FileException;

public class TestTerminal
{
	/**
	 * Main entry point of the application.
	 * <hr>
	 * @param arguments Arguments entered on the command line.
	 */
	@SuppressWarnings("nls")
	public static void main(String[] arguments)
	{
		try
		{
			CommandTerminal terminal = new CommandTerminal("/config/command/chat-commands.xml");
			terminal.start();
		}
		catch (FileException e)
		{
			e.printStackTrace();
		}
	}

}
