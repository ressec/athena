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
package com.heliosphere.athena.base.command.internal.processor;

import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.command.internal.exception.CommandException;
import com.heliosphere.athena.base.command.internal.protocol.ICommandProtocolType;
import com.heliosphere.athena.base.terminal.CommandTerminal;

/**
 * Provides a basic behavior for an executable command. An executable command can be
 * automatically executed by a command coordinator.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ExecutableCommand
{
	/**
	 * Returns the command protocol type.
	 * <hr>
	 * @return Command protocol type.
	 */
	Enum<? extends ICommandProtocolType> getProtocolType();

	/**
	 * Executes the given command.
	 * <hr>
	 * @param terminal Terminal where the result of the execution of the command should be written.
	 * @param command Command to execute.
	 * @throws CommandException Thrown in case an error occurred while processing the command.
	 */
	void execute(CommandTerminal terminal, ICommand command) throws CommandException;
}
