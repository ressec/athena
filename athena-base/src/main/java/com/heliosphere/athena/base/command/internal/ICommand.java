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
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICommand
{
	/**
	 * Returns the command metadata (definition).
	 * <p>
	 * @return Command metadata.
	 */
	ICommandMetadata getMetadata();

	/**
	 * Sets the command metadata (definition).
	 * <p>
	 * @param metadata Command definition.
	 */
	void setMetadata(ICommandMetadata metadata);

	/**
	 * Returns the original text of the command (as entered on the command line).
	 * <p>
	 * @return Command original text.
	 */
	String getText();

	/**
	 * Adds a parameter to the command.
	 * <hr>
	 * @param parameter Parameter to add.
	 */
	void addParameter(ICommandParameter parameter);

	/**
	 * Returns the command parameters.
	 * <p>
	 * @return Command parameters.
	 */
	List<ICommandParameter> getParameters();

	/**
	 * Returns the command parameter given its name.
	 * <hr>
	 * @param name Parameter name.
	 * @return {@link ICommandParameter} or {@code null} if the given parameter has not been found.
	 */
	ICommandParameter getParameter(String name);
}
