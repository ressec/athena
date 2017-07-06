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

import com.heliosphere.athena.base.message.internal.exception.MessageException;
import com.heliosphere.athena.base.message.internal.protocol.IMessageProtocol;
import com.heliosphere.athena.base.message.internal.protocol.IMessageProtocolCategory;
import com.heliosphere.athena.base.message.internal.protocol.IMessageProtocolDomain;
import com.heliosphere.athena.base.message.internal.protocol.IMessageProtocolGroup;
import com.heliosphere.athena.base.message.internal.protocol.MessageResponseStatus;

/**
 * Provides an abstract implementation for a {@link IMessage}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@SuppressWarnings("serial")
public abstract class AbstractMessage implements IMessage
{
	/**
	 * Message protocol type.
	 */
	private Enum<? extends IMessageProtocol> protocol;

	/**
	 * Message correlation identifier.
	 */
	private long correlationId = 0l;

	/**
	 * Message response status.
	 */
	private MessageResponseStatus response;

	/**
	 * Message content.
	 */
	private Object content;

	/**
	 * Creates a new message.
	 * <hr>
	 * @param protocol Message protocol.
	 */
	public AbstractMessage(Enum<? extends IMessageProtocol> protocol)
	{
		this.protocol = protocol;
	}

	/**
	 * Creates a new message.
	 * <hr>
	 * @param protocol Message protocol.
	 * @param content Message content.
	 */
	public AbstractMessage(final Enum<? extends IMessageProtocol> protocol, final IMessageContent content)
	{
		this.protocol = protocol;
		this.content = content;
	}

	/**
	 * Creates a new message.
	 * <hr>
	 * @param protocol Message protocol.
	 * @param content Message content.
	 * @param correlationId Message correlation identifier.
	 */
	public AbstractMessage(final Enum<? extends IMessageProtocol> protocol, final IMessageContent content, final long correlationId)
	{
		this.protocol = protocol;
		this.correlationId = correlationId;
		this.content = content;
	}

	/**
	 * Creates a new message.
	 * <hr>
	 * @param protocol Message protocol.
	 * @param content Message content.
	 * @param status Message response status.
	 * @param correlationId Message correlation identifier.
	 */
	public AbstractMessage(final Enum<? extends IMessageProtocol> protocol, final IMessageContent content, final MessageResponseStatus status, final long correlationId)
	{
		this.protocol = protocol;
		this.response = status;
		this.content = content;
		this.correlationId = correlationId;
	}

	/**
	 * Creates a new message.
	 * <hr>
	 * @param protocol Message protocol.
	 * @param original Original message.
	 * @param status Message response status.
	 * @param correlationId Message correlation identifier.
	 */
	public AbstractMessage(final Enum<? extends IMessageProtocol> protocol, final IMessage original, final MessageResponseStatus status, final long correlationId)
	{
		this.protocol = protocol;
		this.response = status;
		this.content = original.getContent();
		this.correlationId = correlationId;
	}

	@Override
	@SuppressWarnings("nls")
	public final void validate() throws MessageException
	{
		//		// Validate message content.
		//		Class<? extends IMessageContent> contentClass = ((IMessageType) type).getContentClass();
		//		if (content != null)
		//		{
		//			if (!contentClass.isInstance(content))
		//			{
		//				throw new MessageContentException("Expected message content class should be: " + contentClass.getName() + " but is: " + content.getClass().getName());
		//			}
		//		}
	}

	@Override
	public final Enum<? extends IMessageProtocol> getProtocol()
	{
		return protocol;
	}

	@Override
	public final Enum<? extends IMessageProtocolCategory> getCategory()
	{
		return ((IMessageProtocol) protocol).getCategory();
	}

	@Override
	public final Enum<? extends IMessageProtocolGroup> getGroup()
	{
		return ((IMessageProtocol) protocol).getGroup();
	}

	@Override
	public final Enum<? extends IMessageProtocolDomain> getDomain()
	{
		return ((IMessageProtocol) protocol).getDomain();
	}

	@Override
	public final MessageResponseStatus getResponseStatus()
	{
		return response;
	}

	@Override
	public final Object getContent()
	{
		return content;
	}

	@Override
	public final long getCorrelationId()
	{
		return correlationId;
	}
}
