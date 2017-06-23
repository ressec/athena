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

public interface ICommandProcessor
{
	/**
	 * Executes the given command.
	 * <hr>
	 * @param command Command to execute.
	 * @return Result.
	 * @throws CommandException Thrown in case an error occurred while processing the command.
	 */
	Object execute(ICommand command) throws CommandException;
}
