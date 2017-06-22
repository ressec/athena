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
package com.heliosphere.athena.base.command.internal.protocol;

/**
 * Provides a basic behavior for command category type enumerations.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICommandCategoryType
{
	/**
	 * Creates a command category type based on its string representation.
	 * <hr>
	 * @param value String representation.
	 * @return Command category type.
	 */
	Enum<? extends ICommandCategoryType> fromString(String value);

	/**
	 * Returns the prefix for the command category.
	 * <p>
	 * @return Command category prefix.
	 */
	String getPrefix();
}
