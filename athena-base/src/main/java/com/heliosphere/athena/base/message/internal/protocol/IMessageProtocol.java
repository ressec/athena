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
 * Provides a basic behavior for message protocol types.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IMessageProtocol
{
	/**
	 * Creates a message category type based on its string representation.
	 * <p>
	 * <b>Example:</b><p> 
	 * <code>FakeMessageProtocol.fromString("QUIT");</code>
	 * <hr>
	 * @param value String representation.
	 * @return Message protocol enumerated value.
	 */
	Enum<? extends IMessageProtocol> fromString(String value);

	/**
	 * Returns the message nature.
	 * <hr>
	 * @return Message nature.
	 */
	MessageProtocolNature getNature();

	/**
	 * Returns the message protocol category.
	 * <p>
	 * @return Message protocol category.
	 */
	Enum<? extends IMessageProtocolCategory> getCategory();

	/**
	 * Returns the message protocol group.
	 * <p>
	 * @return Message protocol group.
	 */
	Enum<? extends IMessageProtocolGroup> getGroup();

	/**
	 * Returns the message protocol domain.
	 * <p>
	 * @return Message protocol domain.
	 */
	Enum<? extends IMessageProtocolDomain> getDomain();
}
