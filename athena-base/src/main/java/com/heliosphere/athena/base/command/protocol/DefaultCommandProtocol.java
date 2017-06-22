/*
 * Copyright(c) 2010-2013 Heliosphere Ltd.
 * ---------------------------------------------------------------------------
 * This file is part of the Drake project which is licensed under the Apache
 * license version 2 and use is subject to license terms. You should have
 * received a copy of the license with the project artefact binaries and/or
 * sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package com.heliosphere.athena.base.command.protocol;

import com.heliosphere.athena.base.command.internal.protocol.ICommandCategoryType;
import com.heliosphere.athena.base.command.internal.protocol.ICommandGroupType;
import com.heliosphere.athena.base.command.internal.protocol.ICommandProtocolType;
import com.heliosphere.athena.base.exception.InvalidArgumentException;
import com.heliosphere.athena.base.message.internal.protocol.IMessageType;
import com.heliosphere.athena.base.message.protocol.DefaultMessageType;
import com.heliosphere.athena.base.resource.bundle.BundleAthenaBase;
import com.heliosphere.athena.base.resource.bundle.ResourceBundleManager;

import lombok.NonNull;

/**
 * Enumeration defining a set of {@code default} command types.
 * <p>
 * <b>Note:</b><br>
 * You should not use this protocol directly (except for testing purpose) but you should define your(s) according to your needs.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum DefaultCommandProtocol implements ICommandProtocolType
{
	/**
	 * Command: {@code AFK}.
	 */
	AFK(DefaultCommandCategoryType.NORMAL, DefaultCommandGroupType.CHAT, DefaultMessageType.STATUS_AFK),

	/**
	 * Command: {@code Server Time}.
	 */
	SERVER_TIME(DefaultCommandCategoryType.NORMAL, DefaultCommandGroupType.SYSTEM, DefaultMessageType.QUERY_SERVER_TIME),

	/**
	 * Command: {@code Client Time}.
	 */
	CLIENT_TIME(DefaultCommandCategoryType.NORMAL, DefaultCommandGroupType.SYSTEM, DefaultMessageType.NONE),

	/**
	 * Help command.
	 */
	HELP(DefaultCommandCategoryType.NORMAL, DefaultCommandGroupType.SYSTEM, DefaultMessageType.NONE),

	/**
	 * Quit command.
	 */
	QUIT(DefaultCommandCategoryType.NORMAL, DefaultCommandGroupType.SYSTEM, DefaultMessageType.NONE);

	/**
	 * Command category type.
	 */
	private final Enum<? extends ICommandCategoryType> category;

	/**
	 * Command group type.
	 */
	private final Enum<? extends ICommandGroupType> group;

	/**
	 * Message type.
	 */
	private final Enum<? extends IMessageType> message;

	/**
	 * Creates a new enumerated value given some values.
	 * <p>
	 * @param category Command category type.
	 * @param group Command group type.
	 * @param message Message type.
	 */
	private DefaultCommandProtocol(final @NonNull Enum<? extends ICommandCategoryType> category, final @NonNull Enum<? extends ICommandGroupType> group, final @NonNull Enum<? extends IMessageType> message)
	{
		this.category = category;
		this.group = group;
		this.message = message;
	}

	@Override
	public final DefaultCommandProtocol fromString(final String value)
	{
		if (value == null || value.trim().length() == 0)
		{
			throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CommandCategoryCannotBeNull));
		}

		for (DefaultCommandProtocol element : DefaultCommandProtocol.values())
		{
			if (element.name().equalsIgnoreCase(value))
			{
				return element;
			}
		}

		throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CannotCreateEnumerated, DefaultCommandProtocol.class.getName(), value));
	}

	@Override
	public final Enum<? extends ICommandCategoryType> getCategoryType()
	{
		return category;
	}

	@Override
	public final Enum<? extends ICommandGroupType> getGroupType()
	{
		return group;
	}

	@Override
	public final Enum<? extends IMessageType> getMessageType()
	{
		return message;
	}
}
