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

import com.heliosphere.athena.base.command.internal.interpreter.ICommandInterpreter;
import com.heliosphere.athena.base.command.internal.processor.ICommandProcessor;
import com.heliosphere.athena.base.command.internal.protocol.ICommandProtocolType;

/**
 * Provides a basic behavior for a command coordinator.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
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
	 * Registers a new command processor.
	 * <hr>
	 * @param type Command protocol type.
	 * @param processor Command processor to register for the command type.
	 */
	void registerProcessor(Enum<? extends ICommandProtocolType> type, ICommandProcessor processor);
}
