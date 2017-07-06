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
package com.heliosphere.athena.base.message;

import com.heliosphere.athena.base.message.internal.AbstractMessage;
import com.heliosphere.athena.base.message.internal.IMessage;
import com.heliosphere.athena.base.message.internal.IMessageContent;
import com.heliosphere.athena.base.message.internal.protocol.IMessageProtocol;
import com.heliosphere.athena.base.message.internal.protocol.MessageProtocolNature;
import com.heliosphere.athena.base.message.internal.protocol.MessageResponseStatus;

/**
 * Provides a concrete implementation of a {@link IMessage}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class Message extends AbstractMessage
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new message.
	 * <hr>
	 * @param protocol Message protocol type.
	 */
	public Message(final Enum<? extends IMessageProtocol> protocol)
	{
		super(protocol);
	}

	/**
	 * Creates a new message.
	 * <hr>
	 * @param protocol Message protocol type.
	 * @param content Message content.
	 * @param status Response message status.
	 * @param correlationId Message correlation identifier.
	 */
	public Message(final Enum<? extends IMessageProtocol> protocol, final IMessageContent content, final MessageResponseStatus status, final long correlationId)
	{
		super(protocol, content, status, correlationId);
	}

	/**
	 * Creates a new message.
	 * <hr>
	 * @param protocol Message protocol type.
	 * @param original Original message.
	 * @param status Response message status.
	 * @param correlationId Message correlation identifier.
	 */
	public Message(final Enum<? extends IMessageProtocol> protocol, final IMessage original, final MessageResponseStatus status, final long correlationId)
	{
		super(protocol, original, status, correlationId);
	}

	/**
	 * Creates a new message.
	 * <hr>
	 * @param protocol Message protocol.
	 * @return Newly created message.
	 */
	public final static IMessage createMessage(final Enum<? extends IMessageProtocol> protocol)
	{
		return new Message(protocol);
	}

	/**
	 * Creates a new message.
	 * <hr>
	 * @param protocol Message protocol.
	 * @param content Message content.
	 * @return Newly created message.
	 */
	public final static IMessage createMessage(final Enum<? extends IMessageProtocol> protocol, final IMessageContent content)
	{
		return new Message(protocol, content, null, 0l);
	}

	/**
	 * Creates a new message.
	 * <hr>
	 * @param protocol Message protocol.
	 * @param content Message content.
	 * @param status Response message status.
	 * @param correlationId Message correlation identifier.
	 * @return Newly created message.
	 */
	public final static IMessage createMessage(final Enum<? extends IMessageProtocol> protocol, final IMessageContent content, final MessageResponseStatus status, final long correlationId)
	{
		if (((IMessageProtocol) protocol).getNature() == MessageProtocolNature.REPLY)
		{
			return new Message(protocol, content, status, correlationId);
		}

		return new Message(protocol, content, null, correlationId);
	}

	/**
	 * Creates a new message.
	 * <hr>
	 * @param protocol Message protocol.
	 * @param original Original message.
	 * @param status Response message status.
	 * @param correlationId Message correlation identifier.
	 * @return Newly created message.
	 */
	public final static IMessage createMessage(final Enum<? extends IMessageProtocol> protocol, final IMessage original, final MessageResponseStatus status, final long correlationId)
	{
		if (((IMessageProtocol) protocol).getNature() == MessageProtocolNature.REPLY)
		{
			return new Message(protocol, original, status, correlationId);
		}

		return new Message(protocol, original, null, correlationId);
	}
}
