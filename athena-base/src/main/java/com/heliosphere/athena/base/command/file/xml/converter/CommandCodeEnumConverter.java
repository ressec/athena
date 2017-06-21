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

import com.heliosphere.athena.base.command.internal.type.CommandCodeType;
import com.heliosphere.athena.base.command.internal.type.ICommandCodeType;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;

/**
 * Provides an {@code XStream} enumeration converter for {@link CommandCodeType}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public final class CommandCodeEnumConverter extends AbstractSingleValueConverter
{
	@Override
	public boolean canConvert(Class clazz)
	{
		return clazz.equals(Enum.class);
	}

	@Override
	public Object fromString(String value)
	{
		try
		{
			return ((ICommandCodeType) CommandCodeType.class.newInstance()).fromString(value);
		}
		catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}