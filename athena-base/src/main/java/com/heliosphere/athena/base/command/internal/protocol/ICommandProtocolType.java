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
 * Provides a basic behavior for command type enumerations.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICommandProtocolType
{
	/**
	 * Creates a command category type based on its string representation.
	 * <p>
	 * <b>Example:</b><p> 
	 * <code>FakeCommandProtocolType.fromString("QUIT");</code>
	 * <hr>
	 * @param value String representation.
	 * @return Command category type.
	 */
	Enum<? extends ICommandProtocolType> fromString(String value);

	/**
	 * Returns the command category type.
	 * <p>
	 * @return Command category type.
	 */
	Enum<? extends ICommandCategoryType> getCategory();

	/**
	 * Returns the command group type.
	 * <p>
	 * @return Command group type.
	 */
	Enum<? extends ICommandGroupType> getGroup();
}
