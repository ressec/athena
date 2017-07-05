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
import com.heliosphere.athena.base.message.internal.protocol.IMessageProtocolGroup;
import com.heliosphere.athena.base.resource.bundle.BundleAthenaBase;
import com.heliosphere.athena.base.resource.bundle.ResourceBundleManager;

/**
 * Enumeration defining a set of {@code test} message protocol groups.
 * <p>
 * <b>Note:</b><br>
 * You should not use this protocol directly (except for testing purpose) but you should define yours according to your needs.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum TestMessageProtocolGroup implements IMessageProtocolGroup
{
	/**
	 * Chat message group.
	 */
	CHAT,

	/**
	 * Message message group.
	 */
	MESSAGE;

	/**
	 * Creates a message protocol group enumerated value from a given string value.
	 * <p>
	 * <b>Example:</b><p> 
	 * <code>TestMessageProtocolGroupType.fromString("Chat");</code>
	 * <hr>
	 * @param value String representing the enumerated value.
	 * @return Message protocol group.
	 */
	@SuppressWarnings("nls")
	@Override
	public Enum<? extends IMessageProtocolGroup> fromString(String value)
	{
		if (value == null || value.trim().length() == 0)
		{
			throw new InvalidArgumentException("Message protocol group cannot be null or empty!");
		}

		for (TestMessageProtocolGroup element : TestMessageProtocolGroup.values())
		{
			if (element.name().equalsIgnoreCase(value))
			{
				return element;
			}
		}

		throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CannotCreateEnumerated, TestMessageProtocolGroup.class.getName(), value));
	}
}
