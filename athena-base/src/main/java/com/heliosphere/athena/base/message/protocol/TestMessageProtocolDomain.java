/*
 * Copyright(c) 2010-2013 Heliosphere Ltd.
 * ---------------------------------------------------------------------------
 * This file is part of the Drake project which is licensed under the Apache
 * license version 2 and use is subject to license terms. You should have
 * received a copy of the license with the project artifact binaries and/or
 * sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package com.heliosphere.athena.base.message.protocol;

import com.heliosphere.athena.base.exception.InvalidArgumentException;
import com.heliosphere.athena.base.message.internal.protocol.IMessageProtocolDomain;
import com.heliosphere.athena.base.resource.bundle.BundleAthenaBase;
import com.heliosphere.athena.base.resource.bundle.ResourceBundleManager;

/**
 * Enumeration defining a set of {@code test} message protocol domains.
 * <p>
 * <b>Note:</b><br>
 * You should not use this protocol directly (except for testing purpose) but you should define yours according to your needs.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum TestMessageProtocolDomain implements IMessageProtocolDomain
{
	/**
	 * Lobby message domain.
	 */
	LOBBY,

	/**
	 * Room message domain.
	 */
	ROOM,

	/**
	 * User message domain.
	 */
	USER,

	/**
	 * Conversation message domain.
	 */
	CONVERSATION,

	/**
	 * Discussion message domain.
	 */
	DISCUSSION;

	/**
	 * Creates a message protocol domain enumerated value from a given string value.
	 * <p>
	 * <b>Example:</b><p> 
	 * <code>TestMessageProtocolDomain.fromString("Room");</code>
	 * <hr>
	 * @param value String representing the enumerated value.
	 * @return Message protocol domain.
	 */
	@SuppressWarnings("nls")
	@Override
	public Enum<? extends IMessageProtocolDomain> fromString(String value)
	{
		if (value == null || value.trim().length() == 0)
		{
			throw new InvalidArgumentException("Message protocol domain cannot be null or empty!");
		}

		for (TestMessageProtocolDomain element : TestMessageProtocolDomain.values())
		{
			if (element.name().equalsIgnoreCase(value))
			{
				return element;
			}
		}

		throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CannotCreateEnumerated, TestMessageProtocolDomain.class.getName(), value));
	}
}
