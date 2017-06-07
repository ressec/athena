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
package com.heliosphere.athena.base.command.internal;

import java.util.List;

/**
 * Provides a basic behavior for a command.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICommand
{
	/**
	 * Returns the command metadata.
	 * <p>
	 * @return Command metadata.
	 */
	ICommandMetadata getCommandReference();

	/**
	 * Returns the original text of the command (as entered on the command line).
	 * <p>
	 * @return Command original text.
	 */
	String getText();

	/**
	 * Returns the command parameters.
	 * <p>
	 * @return Command parameters.
	 */
	List<ICommandParameter> getParameters();
}
