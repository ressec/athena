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
package com.heliosphere.athena.base.command.file.xml.converter;

import com.heliosphere.athena.base.command.internal.type.ParameterType;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * Provides a {@code XStream} converter for the {@link ParameterType} enumeration.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public class ParameterEnumConverter extends AbstractSingleValueConverter
{
	@Override
	public boolean canConvert(Class clazz)
	{
		return clazz.equals(Enum.class);
	}

	@Override
	public Object fromString(String value)
	{
		return ParameterType.values()[0].from(value);
	}
}