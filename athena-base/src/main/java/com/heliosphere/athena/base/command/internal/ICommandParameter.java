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

/**
 * Provides a basic behavior for a command parameter.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICommandParameter
{
	/**
	 * Returns the command parameter metadata reference.
	 * <p>
	 * @return Command parameter metadata reference.
	 */
	ICommandParameterMetadata getCommandParameterReference();

	/**
	 * Returns the original text of the command parameter (as entered on the command line).
	 * <p>
	 * @return Command parameter original text.
	 */
	String getText();

	/**
	 * Returns the command parameter value.
	 * <p>
	 * @return Command parameter value.
	 */
	Object getValue();
}
