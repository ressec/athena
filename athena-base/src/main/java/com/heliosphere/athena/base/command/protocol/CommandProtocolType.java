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
package com.heliosphere.athena.base.command.internal.type;

import com.heliosphere.athena.base.exception.InvalidArgumentException;
import com.heliosphere.athena.base.message.internal.IMessageType;
import com.heliosphere.athena.base.resource.bundle.BundleAthenaBase;
import com.heliosphere.athena.base.resource.bundle.ResourceBundleManager;
import com.heliosphere.athena.base.test.message.protocol.FakeMessageProtocol;

import lombok.NonNull;

/**
 * Enumeration defining a set of command types.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum CommandProtocolType implements ICommandProtocolType
{
	/**
	 * Command: {@code AFK}.
	 */
	AFK(CommandCategoryType.NORMAL, CommandGroupType.CHAT, FakeMessageProtocol.STATUS_AFK),

	/**
	 * Command: {@code Server Time}.
	 */
	SERVER_TIME(CommandCategoryType.NORMAL, CommandGroupType.SYSTEM, FakeMessageProtocol.QUERY_SERVER_TIME),

	/**
	 * Command: {@code Client Time}.
	 */
	CLIENT_TIME(CommandCategoryType.NORMAL, CommandGroupType.SYSTEM, FakeMessageProtocol.NONE),

	/**
	 * Help command.
	 */
	HELP(CommandCategoryType.NORMAL, CommandGroupType.SYSTEM, FakeMessageProtocol.NONE),

	/**
	 * Quit command.
	 */
	QUIT(CommandCategoryType.NORMAL, CommandGroupType.SYSTEM, FakeMessageProtocol.NONE);

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
	private CommandProtocolType(final @NonNull Enum<? extends ICommandCategoryType> category, final @NonNull Enum<? extends ICommandGroupType> group, final @NonNull Enum<? extends IMessageType> message)
	{
		this.category = category;
		this.group = group;
		this.message = message;
	}

	@Override
	public final CommandProtocolType fromString(final String value)
	{
		if (value == null || value.trim().length() == 0)
		{
			throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CommandCategoryCannotBeNull));
		}

		for (CommandProtocolType element : CommandProtocolType.values())
		{
			if (element.name().equalsIgnoreCase(value))
			{
				return element;
			}
		}

		throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CannotCreateEnumerated, CommandProtocolType.class.getName(), value));
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
