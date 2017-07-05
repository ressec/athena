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
package com.heliosphere.athena.base.message.internal.protocol;

/**
 * Provides a basic behavior for message protocol domain enumerations.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IMessageProtocolDomain
{
	/**
	 * Creates a message protocol domain based on its string representation.
	 * <hr>
	 * @param value String representation.
	 * @return Message protocol domain.
	 */
	Enum<? extends IMessageProtocolDomain> fromString(String value);
}
