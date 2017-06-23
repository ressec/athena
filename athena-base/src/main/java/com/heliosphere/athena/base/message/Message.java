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
import com.heliosphere.athena.base.message.internal.protocol.IMessageType;
import com.heliosphere.athena.base.message.internal.protocol.MessageCategoryType;
import com.heliosphere.athena.base.message.internal.protocol.MessageResponseType;

/**
 * Provides a concrete message implementation.
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
	 * @param type Message type.
	 * @param category Message category.
	 * @param content Message content.
	 */
	public Message(Enum<? extends IMessageType> type, MessageCategoryType category, IMessageContent content)
	{
		super(type, category, content);
	}

	/**
	 * Creates a new message.
	 * <hr>
	 * @param type Message type.
	 * @param category Message category.
	 * @param response Message response type.
	 * @param content Message content.
	 */
	public Message(Enum<? extends IMessageType> type, MessageCategoryType category, MessageResponseType response, IMessageContent content)
	{
		super(type, category, response, content);
	}

	/**
	 * Creates a new notification message.
	 * <hr>
	 * @param type Message type.
	 * @param content Message content.
	 * @return Newly created message.
	 */
	public final static IMessage createNotification(final Enum<? extends IMessageType> type, final IMessageContent content)
	{
		return new Message(type, MessageCategoryType.NOTIFICATION, content);
	}

	/**
	 * Creates a new request message.
	 * <hr>
	 * @param type Message type.
	 * @param content Message content.
	 * @return Newly created message.
	 */
	public final static IMessage createRequest(final Enum<? extends IMessageType> type, final IMessageContent content)
	{
		return new Message(type, MessageCategoryType.REQUEST, content);
	}

	/**
	 * Creates a new confirmed reply message.
	 * <hr>
	 * @param type Message type.
	 * @param content Message content.
	 * @return Newly created message.
	 */
	public final static IMessage createReplyConfirmed(final Enum<? extends IMessageType> type, final IMessageContent content)
	{
		return new Message(type, MessageCategoryType.REPLY, MessageResponseType.CONFIRMED, content);
	}

	/**
	 * Creates a confirmed reply message given a request message.
	 * <hr>
	 * @param original Original request message.
	 * @return Newly created message.
	 */
	public final static IMessage createReplyConfirmed(final IMessage original)
	{
		return new Message(original.getType(), MessageCategoryType.REPLY, MessageResponseType.CONFIRMED, original.getContent());
	}

	/**
	 * Creates a confirmed reply message given a request message.
	 * <hr>
	 * @param original Original request message.
	 * @param content Message content.
	 * @return Newly created message.
	 */
	public final static IMessage createReplyConfirmed(final IMessage original, final IMessageContent content)
	{
		return new Message(original.getType(), MessageCategoryType.REPLY, MessageResponseType.CONFIRMED, content);
	}

	/**
	 * Creates a new rejected reply message.
	 * <hr>
	 * @param type Message type.
	 * @param content Message content.
	 * @return Newly created message.
	 */
	public final static IMessage createReplyRejected(final Enum<? extends IMessageType> type, final IMessageContent content)
	{
		return new Message(type, MessageCategoryType.REPLY, MessageResponseType.REJECTED, content);
	}
}
