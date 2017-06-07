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
package com.heliosphere.athena.base.command.internal.type;

/**
 * Provides a basic behavior for command category type enumerations.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICommandCategoryType extends IEnumType
{
	/**
	 * Creates a command category type from a given prefix.
	 * <p>
	 * @param prefix String representing the command category prefix.
	 * @return Command category type.
	 */
	Enum<? extends ICommandCategoryType> fromPrefix(final String prefix);

	/**
	 * Returns the prefix for the command category.
	 * <p>
	 * @return Command category prefix.
	 */
	String getPrefix();
}
