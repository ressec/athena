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
package com.heliosphere.athena.base.message.internal;

import java.io.Serializable;

import com.heliosphere.athena.base.message.internal.type.MessageUsageType;

/**
 * Interface providing a basic behavior for Heliosphere' messages type (protocol).
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IMessageType extends Serializable
{
	/**
	 * Creates a message type based on its string representation.
	 * <hr>
	 * @param value String representation.
	 * @return Message type.
	 */
	Enum<? extends IMessageType> fromString(String value);

	/**
	 * Returns the message content expected type class.
	 * <p>
	 * @return Message content type class.
	 */
	Class<? extends IMessageContent> getContentClass();

	/**
	 * Returns the message usage type (restriction).
	 * <p>
	 * @return Message usage type.
	 */
	MessageUsageType getUsageType();
}
