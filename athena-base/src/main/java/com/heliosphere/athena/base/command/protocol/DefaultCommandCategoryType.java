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
import com.heliosphere.athena.base.exception.InvalidArgumentException;
import com.heliosphere.athena.base.resource.bundle.BundleAthenaBase;
import com.heliosphere.athena.base.resource.bundle.ResourceBundleManager;

/**
 * Enumeration defining a {@code default} set of command category types.
 * <p>
 * <b>Note:</b><br>
 * You can use this protocol directly or you can define your(s) according to your needs.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
@SuppressWarnings("nls")
public enum DefaultCommandCategoryType implements ICommandCategoryType
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
	private DefaultCommandCategoryType(final String prefix)
	{
		this.prefix = prefix;
	}

	@Override
	public final String getPrefix()
	{
		return prefix;
	}

	/**
	 * Creates a command category enumerated value from a given string value.
	 * <p>
	 * <b>Example:</b><p> 
	 * <code>CommandCategoryType.fromString("Administration");</code>
	 * <hr>
	 * @param value String representing the enumerated value.
	 * @return Command category.
	 */
	@Override
	public final DefaultCommandCategoryType fromString(final String value)
	{
		if (value == null || value.trim().length() == 0)
		{
			throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CommandCategoryCannotBeNull));
		}

		for (DefaultCommandCategoryType element : DefaultCommandCategoryType.values())
		{
			if (element.name().equalsIgnoreCase(value))
			{
				return element;
			}
		}

		throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CannotCreateEnumerated, DefaultCommandCategoryType.class.getName(), value));
	}

	/**
	 * Creates a command category enumerated value from a given command prefix.
	 * <p>
	 * <b>Example:</b><p> 
	 * <code>CommandCategoryType.fromPrefix("*");</code>
	 * <hr>
	 * @param prefix String representing the command prefix.
	 * @return Command category.
	 */
	public static final DefaultCommandCategoryType fromPrefix(final String prefix)
	{
		if (prefix == null || prefix.trim().length() == 0)
		{
			throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CommandCategoryCannotBeNull));
		}

		for (DefaultCommandCategoryType value : DefaultCommandCategoryType.values())
		{
			if (value.getPrefix().equalsIgnoreCase(prefix))
			{
				return value;
			}
		}

		throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CannotCreateEnumerated, DefaultCommandCategoryType.class.getName(), prefix));
	}
}
