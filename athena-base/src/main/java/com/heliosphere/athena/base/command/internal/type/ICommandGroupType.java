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
 * Provides a basic behavior for command group type enumerations.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICommandGroupType
{
	/**
	 * Creates a command group type from a given value.
	 * <p>
	 * @param value String representing the command group.
	 * @return {@code Enum<? extends ICommandGroupType}.
	 */
	Enum<? extends ICommandGroupType> from(final String value);
}