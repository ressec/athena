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
package com.heliosphere.athena.base.command.internal.type;

import com.heliosphere.athena.base.exception.InvalidArgumentException;
import com.heliosphere.athena.base.resource.bundle.BundleAthenaBase;
import com.heliosphere.athena.base.resource.bundle.ResourceBundleManager;

/**
 * Enumeration defining a set of basic command code types.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum CommandCodeType implements ICommandCodeType
{
	/**
	 * Special default command.
	 */
	DISPLAY_TERMINAL,

	/**
	 * Quit command used to terminate the client application abruptly.
	 */
	QUIT;

	/**
	 * Creates an command code enumerated value from a given string value.
	 * <p>
	 * <b>Example:</b><p> 
	 * <code>CommandCodeType.fromString("Quit");</code>
	 * <hr>
	 * @param value String representing the enumerated value.
	 * @return Command code type.
	 */
	public static Enum<? extends ICommandCodeType> fromString(String value)
	{
		if (value == null || value.trim().length() == 0)
		{
			throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CommandCategoryCannotBeNull));
		}

		for (CommandCodeType element : CommandCodeType.values())
		{
			if (element.name().equalsIgnoreCase(value))
			{
				return element;
			}
		}

		throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CannotCreateEnumerated, CommandCodeType.class.getName(), value));
	}
}
