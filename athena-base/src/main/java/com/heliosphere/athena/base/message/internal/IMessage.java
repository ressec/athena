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
import com.heliosphere.athena.base.message.internal.protocol.IMessageProtocol;
import com.heliosphere.athena.base.message.internal.protocol.IMessageProtocolCategory;
import com.heliosphere.athena.base.message.internal.protocol.IMessageProtocolDomain;
import com.heliosphere.athena.base.message.internal.protocol.IMessageProtocolGroup;
import com.heliosphere.athena.base.message.internal.protocol.MessageResponseStatus;

/**
 * Interface providing a basic behavior for Heliosphere' messages with protocols using enumerations.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface IMessage extends Serializable
{
	/**
	 * Returns the message protocol.
	 * <hr>
	 * @return Message protocol.
	 */
	Enum<? extends IMessageProtocol> getProtocol();

	/**
	 * Returns the message protocol category.
	 * <hr>
	 * @return Message protocol category.
	 */
	Enum<? extends IMessageProtocolCategory> getCategory();

	/**
	 * Returns the message protocol group.
	 * <hr>
	 * @return Message protocol group.
	 */
	Enum<? extends IMessageProtocolGroup> getGroup();

	/**
	 * Returns the message protocol domain.
	 * <hr>
	 * @return Message protocol domain.
	 */
	Enum<? extends IMessageProtocolDomain> getDomain();

	/**
	 * Returns the message correlation identifier.
	 * <hr>
	 * @return Message correlation identifier (can be null).
	 */
	long getCorrelationId();

	/**
	 * Returns the message response type (only available is message is of nature: reply).
	 * <p>
	 * @return Message response status.
	 */
	MessageResponseStatus getResponseStatus();

	/**
	 * Message content.
	 * <hr>
	 * @return Message content.
	 */
	Object getContent();

	/**
	 * Validates the message.
	 * <hr>
	 * @throws MessageException Thrown in case an error occurred during message validation.
	 */
	void validate() throws MessageException;
}
