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
package com.heliosphere.athena.base.test.message.protocol;

import com.heliosphere.athena.base.exception.InvalidArgumentException;
import com.heliosphere.athena.base.message.internal.IMessageContent;
import com.heliosphere.athena.base.message.internal.IMessageType;
import com.heliosphere.athena.base.message.internal.type.MessageUsageType;
import com.heliosphere.athena.base.resource.bundle.BundleAthenaBase;
import com.heliosphere.athena.base.resource.bundle.ResourceBundleManager;

/**
 * Enumeration defining a fake message protocol for testing purpose.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum FakeMessageProtocol implements IMessageType
{
	/**
	 * Register a user.
	 */
	REGISTER_USER(MessageUsageType.NONE, FakeMessageData.class),

	/**
	 * Query for people.
	 */
	QUERY_WHO(MessageUsageType.NONE, FakeMessageData.class),

	/**
	 * Status: AFK (Away From Keyboard).
	 */
	STATUS_AFK(MessageUsageType.NONE, null);

	/**
	 * Message usage type.
	 */
	private final MessageUsageType usage;

	/**
	 * Content data type class.
	 */
	private final Class<? extends IMessageContent> contentClass;

	/**
	 * Creates a new enumerated value given a message data content class.
	 * <p>
	 * @param usage Message usage type.
	 * @param contentClass Message type content class.
	 */
	private FakeMessageProtocol(final MessageUsageType usage, final Class<? extends IMessageContent> contentClass)
	{
		this.usage = usage;
		this.contentClass = contentClass;
	}

	@Override
	public final MessageUsageType getUsageType()
	{
		return usage;
	}

	@Override
	public final Class<? extends IMessageContent> getContentClass()
	{
		return contentClass;
	}

	/**
	 * Creates a message type from a given string representation.
	 * <p>
	 * <b>Example:</b><p> 
	 * <code>MessageType.fromString("REGISTER_USER");</code>
	 * <hr>
	 * @param value String representing the enumerated value.
	 * @return Message type.
	 */
	@Override
	public Enum<? extends IMessageType> fromString(String value)
	{
		if (value == null || value.trim().length() == 0)
		{
			throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CommandCategoryCannotBeNull));
		}

		for (FakeMessageProtocol element : FakeMessageProtocol.values())
		{
			if (element.name().equalsIgnoreCase(value))
			{
				return element;
			}
		}

		throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CannotCreateEnumerated, FakeMessageProtocol.class.getName(), value));
	}
}
