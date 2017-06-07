/*
 * Copyright(c) 2017 - Heliosphere Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Heliosphere's project which is licensed under the 
 * Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package com.heliosphere.athena.base.command.converter;

import com.heliosphere.athena.base.command.internal.type.CommandCategoryType;
import com.heliosphere.athena.base.command.internal.type.CommandGroupType;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * Provides a {@code XStream} converter for the {@link CommandGroupType} enumeration.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class CommandCategoryEnumConverter extends AbstractSingleValueConverter
{
	@Override
	public boolean canConvert(Class clazz)
	{
		return clazz.equals(Enum.class);
	}

	@Override
	public Object fromString(String value)
	{
		return CommandCategoryType.NORMAL.from(value);
	}
}