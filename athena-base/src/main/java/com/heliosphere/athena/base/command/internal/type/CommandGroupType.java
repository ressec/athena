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
 * Enumeration defining a set of command group types.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum CommandGroupType implements ICommandGroupType
{
	/**
	 * Chat command group type.
	 */
	CHAT,

	/**
	 * Pvp command group type.
	 */
	PVP,

	/**
	 * Character command group type.
	 */
	CHARACTER,

	/**
	 * Emote command group type.
	 */
	EMOTE,

	/**
	 * Guild command group type.
	 */
	GUILD,

	/**
	 * Combat command group type.
	 */
	COMBAT,

	/**
	 * Party command group type.
	 */
	PARTY,

	/**
	 * Raid command group type.
	 */
	RAID,

	/**
	 * Pet command group type.
	 */
	PET,

	/**
	 * System command group type.
	 */
	SYSTEM,

	/**
	 * Targeting command group type.
	 */
	TARGETING;

	@Override
	public Enum<? extends ICommandGroupType> from(String value)
	{
		if (value == null || value.trim().length() == 0)
		{
			throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CommandCategoryCannotBeNull));
		}

		for (CommandGroupType element : CommandGroupType.values())
		{
			if (element.name().equalsIgnoreCase(value))
			{
				return element;
			}
		}

		throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CannotCreateEnumerated, this.getClass(), value));
	}
}
