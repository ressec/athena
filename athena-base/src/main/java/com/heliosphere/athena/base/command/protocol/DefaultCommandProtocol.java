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
import com.heliosphere.athena.base.command.internal.protocol.ICommandDomainType;
import com.heliosphere.athena.base.command.internal.protocol.ICommandGroupType;
import com.heliosphere.athena.base.command.internal.protocol.ICommandProtocolType;
import com.heliosphere.athena.base.exception.InvalidArgumentException;
import com.heliosphere.athena.base.resource.bundle.BundleAthenaBase;
import com.heliosphere.athena.base.resource.bundle.ResourceBundleManager;

import lombok.NonNull;

/**
 * Enumeration defining a {@code default} command protocol.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum DefaultCommandProtocol implements ICommandProtocolType
{
	/**
	 * Command: {@code AFK}.
	 */
	AFK(DefaultCommandCategoryType.NORMAL, DefaultCommandGroupType.CHAT, DefaultCommandDomainType.APPLICATION),

	/**
	 * Help command.
	 */
	HELP(DefaultCommandCategoryType.NORMAL, DefaultCommandGroupType.SYSTEM, DefaultCommandDomainType.APPLICATION),

	/**
	 * Quit command.
	 */
	QUIT(DefaultCommandCategoryType.NORMAL, DefaultCommandGroupType.SYSTEM, DefaultCommandDomainType.APPLICATION);

	/**
	 * Command category type.
	 */
	private final Enum<? extends ICommandCategoryType> category;

	/**
	 * Command group type.
	 */
	private final Enum<? extends ICommandGroupType> group;

	/**
	 * Command domain type.
	 */
	private final Enum<? extends ICommandDomainType> domain;

	/**
	 * Creates a new enumerated value given some values.
	 * <p>
	 * @param category Command category type.
	 * @param group Command group type.
	 * @param domain Command domain type.
	 */
	private DefaultCommandProtocol(final @NonNull Enum<? extends ICommandCategoryType> category, final @NonNull Enum<? extends ICommandGroupType> group, final @NonNull Enum<? extends ICommandDomainType> domain)
	{
		this.category = category;
		this.group = group;
		this.domain = domain;
	}

	@SuppressWarnings("nls")
	@Override
	public final DefaultCommandProtocol fromString(final String value)
	{
		if (value == null || value.trim().length() == 0)
		{
			throw new InvalidArgumentException("Default command type string representation cannot be null or empty!");
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
	public final Enum<? extends ICommandCategoryType> getCategory()
	{
		return category;
	}

	@Override
	public final Enum<? extends ICommandGroupType> getGroup()
	{
		return group;
	}

	@Override
	public final Enum<? extends ICommandDomainType> getDomain()
	{
		return domain;
	}
}
