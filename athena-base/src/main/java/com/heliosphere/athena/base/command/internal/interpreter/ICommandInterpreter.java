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
package com.heliosphere.athena.base.command.internal.interpreter;

import com.heliosphere.athena.base.command.internal.CommandException;
import com.heliosphere.athena.base.command.internal.ICommand;

/**
 * Provides a basic behavior for a command interpreter.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICommandInterpreter
{
	/**
	 * Interprets a command based on a given text representing one authorized form of the command.
	 * <p>
	 * @param text Text representing the command (generally a form of the command entered by a user on a command line).
	 * @return Interpreted command or {@code null} if no command has been found corresponding to the given text.
	 * @throws CommandException Error thrown in case an error occurred while interpreting the command ; generally no command has been found!
	 */
	ICommand interpret(String text) throws CommandException;
}
