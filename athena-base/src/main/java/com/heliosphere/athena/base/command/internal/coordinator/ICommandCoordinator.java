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

import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.command.internal.ICommandMetadata;
import com.heliosphere.athena.base.command.internal.exception.CommandNotFoundException;
import com.heliosphere.athena.base.command.internal.interpreter.ICommandInterpreter;
import com.heliosphere.athena.base.command.internal.processor.ExecutableCommand;

/**
 * Provides a basic behavior for a command coordinator.
 * <p>
 * A command coordinator is an entity which is responsible to automate the execution of some pre-defined commands
 * when issued on a terminal through a command processor.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 * @see ICommandMetadata
 * @see ExecutableCommand
 * @see ICommandInterpreter
 * @see ITerminal
 */
public interface ICommandCoordinator
{
	/**
	 * Returns the command interpreter.
	 * <hr>
	 * @return {@link ICommandInterpreter}.
	 */
	ICommandInterpreter getCommandInterpreter();

	/**
	 * Executes a command execution.
	 * <hr>
	 * @param command Command to execute.
	 * @throws CommandNotFoundException Thrown to indicate the command coordinator has not been able to execute the command
	 * because its definition has not been found (For example: command definition not registered by the command coordinator).
	 * <br> In such a case, it's the responsibility of the caller to handle the execution of this command. 
	 */
	void execute(ICommand command) throws CommandNotFoundException;

	/**
	 * Registers a new command processor.
	 * <hr>
	 * @param processor Command processor to register for the command type.
	 */
	void registerExecutable(ExecutableCommand processor);
}
