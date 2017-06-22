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

import com.heliosphere.athena.base.message.internal.exception.MessageException;
import com.heliosphere.athena.base.message.internal.protocol.IMessageType;
import com.heliosphere.athena.base.message.internal.protocol.MessageCategoryType;
import com.heliosphere.athena.base.message.internal.protocol.MessageResponseType;

/**
 * Interface providing a basic behavior for Heliosphere' messages.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IMessage extends Serializable
{
	/**
	 * Returns the message type.
	 * <hr>
	 * @return Message type.
	 */
	Enum<? extends IMessageType> getType();

	/**
	 * Returns the message category type.
	 * <hr>
	 * @return Message category type.
	 */
	MessageCategoryType getCategoryType();

	/**
	 * Message response type.
	 * <hr>
	 * @return Message response type.
	 */
	MessageResponseType getResponseType();

	/**
	 * Message content.
	 * <hr>
	 * @return Message content.
	 */
	IMessageContent getContent();

	/**
	 * Validates the message.
	 * <hr>
	 * @throws MessageException Thrown in case an error occurred during {@link IMessage} validation.
	 */
	void validate() throws MessageException;
}
