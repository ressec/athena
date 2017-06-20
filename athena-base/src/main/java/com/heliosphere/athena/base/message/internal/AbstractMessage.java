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

import com.heliosphere.athena.base.message.internal.type.MessageCategoryType;
import com.heliosphere.athena.base.message.internal.type.MessageResponseType;

/**
 * Provides an abstract message implementation.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public abstract class AbstractMessage implements IMessage
{
	/**
	 * Message type.
	 */
	private Enum<? extends IMessageType> type;

	/**
	 * Message category type.
	 */
	private MessageCategoryType category;

	/**
	 * Message response type.
	 */
	private MessageResponseType response;

	/**
	 * Message content.
	 */
	private IMessageContent content;

	/**
	 * Creates a new abstract message.
	 * <hr>
	 * @param type Message type.
	 * @param category Message category.
	 * @param content Message content.
	 */
	public AbstractMessage(Enum<? extends IMessageType> type, MessageCategoryType category, IMessageContent content)
	{
		this.type = type;
		this.category = category;
		this.content = content;
	}

	/**
	 * Creates a new abstract message.
	 * <hr>
	 * @param type Message type.
	 * @param category Message category.
	 * @param response Message response type.
	 * @param content Message content.
	 */
	public AbstractMessage(Enum<? extends IMessageType> type, MessageCategoryType category, MessageResponseType response, IMessageContent content)
	{
		this.type = type;
		this.category = category;
		this.response = response;
		this.content = content;
	}

	@Override
	public final Enum<? extends IMessageType> getType()
	{
		return type;
	}

	@Override
	public final MessageCategoryType getCategoryType()
	{
		return category;
	}

	@Override
	public final MessageResponseType getResponseType()
	{
		return response;
	}

	@Override
	public final IMessageContent getContent()
	{
		return content;
	}
}
