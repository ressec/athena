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
package com.heliosphere.athena.base.message.internal.protocol;

import com.heliosphere.athena.base.exception.InvalidArgumentException;
import com.heliosphere.athena.base.resource.bundle.BundleAthenaBase;
import com.heliosphere.athena.base.resource.bundle.ResourceBundleManager;

/**
 * Enumeration defining a set of {@code default} message protocol category.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum MessageProtocolCategory implements IMessageProtocolCategory
{
	/**
	 * System category.
	 */
	SYSTEM,

	/**
	 * Security category.
	 */
	SECURITY,

	/**
	 * Authentication category.
	 */
	AUTHENTICATION,

	/**
	 * Application category.
	 */
	APPLICATION,

	/**
	 * Batch category.
	 */
	BATCH,

	/**
	 * Audit category.
	 */
	AUDIT,

	/**
	 * Info category.
	 */
	INFO;

	/**
	 * Creates a message protocol category enumerated value from a given string value.
	 * <p>
	 * <b>Example:</b><p> 
	 * <code>MessageProtocolCategory.fromString("Batch");</code>
	 * <hr>
	 * @param value String representing the enumerated value.
	 * @return Message protocol category.
	 */
	@SuppressWarnings("nls")
	@Override
	public Enum<? extends IMessageProtocolCategory> fromString(String value)
	{
		if (value == null || value.trim().length() == 0)
		{
			throw new InvalidArgumentException("Message protocol category cannot be null or empty!");
		}

		for (MessageProtocolCategory element : MessageProtocolCategory.values())
		{
			if (element.name().equalsIgnoreCase(value))
			{
				return element;
			}
		}

		throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CannotCreateEnumerated, MessageProtocolCategory.class.getName(), value));
	}
}
