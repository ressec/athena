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
import com.heliosphere.athena.base.resource.bundle.BundleAthenaBase;
import com.heliosphere.athena.base.resource.bundle.ResourceBundleManager;

/**
 * Enumeration defining a set of command category types.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@SuppressWarnings("nls")
public enum CommandCategoryType implements ICommandCategoryType
{
	/**
	 * Debug command category type.
	 */

	DEBUG("*"),

	/**
	 * Normal command category type.
	 */
	NORMAL("/"),

	/**
	 * Administration command category type.
	 */
	ADMINISTRATION("/"),

	/**
	 * Super administration command category type.
	 */
	SUPER_ADMINISTRATION("%"),

	/**
	 * System command category type.
	 */
	SYSTEM(".");

	/**
	 * Command category prefix.
	 */
	private final String prefix;

	/**
	 * Creates a new enumerated value based on the command prefix.
	 * <p>
	 * @param prefix Command prefix.
	 */
	private CommandCategoryType(final String prefix)
	{
		this.prefix = prefix;
	}

	@Override
	public final String getPrefix()
	{
		return prefix;
	}

	@Override
	public final CommandCategoryType from(final String value)
	{
		if (value == null || value.trim().length() == 0)
		{
			throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CommandCategoryCannotBeNull));
		}

		for (CommandCategoryType element : CommandCategoryType.values())
		{
			if (element.name().equalsIgnoreCase(value))
			{
				return element;
			}
		}

		throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CannotCreateEnumerated, this.getClass(), prefix));
	}

	@Override
	public final CommandCategoryType fromPrefix(final String prefix)
	{
		if (prefix == null || prefix.trim().length() == 0)
		{
			throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CommandCategoryCannotBeNull));
		}

		for (CommandCategoryType value : CommandCategoryType.values())
		{
			if (value.getPrefix().equalsIgnoreCase(prefix))
			{
				return value;
			}
		}

		throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CannotCreateEnumerated, this.getClass(), prefix));
	}
}
