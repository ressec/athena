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
package com.heliosphere.athena.base.command.protocol;

import com.heliosphere.athena.base.command.internal.protocol.IParameterType;
import com.heliosphere.athena.base.exception.InvalidArgumentException;
import com.heliosphere.athena.base.resource.bundle.BundleAthenaBase;
import com.heliosphere.athena.base.resource.bundle.ResourceBundleManager;

import lombok.NonNull;

/**
 * Enumeration defining a set of parameter types.
 * <p>
 * Developer can also add its own type or create a new custom enumeration implementing the {@link IParameterType} interface.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum ParameterType implements IParameterType
{
	/**
	 * A parameter type accepting {@code string} value.
	 */
	STRING(String.class.getName()),

	/**
	 * A parameter type accepting {@code integer} value.
	 */
	INTEGER(Integer.class.getName()),

	/**
	 * A parameter type accepting {@code long} value.
	 */
	LONG(Long.class.getName()),

	/**
	 * A parameter type accepting {@code boolean} value.
	 */
	BOOLEAN(Boolean.class.getName());

	/**
	 * Parameter type.
	 */
	private final String type;

	/**
	 * Creates a new enumerated value based on the parameter type.
	 * <p>
	 * @param type Parameter type.
	 */
	private ParameterType(final @NonNull String type)
	{
		this.type = type;
	}

	@Override
	public final String getType()
	{
		return type;
	}

	/**
	 * Creates a parameter type enumerated value from a given string value.
	 * <p>
	 * <b>Example:</b><p> 
	 * <code>ParameterType.fromString("Integer");</code>
	 * <hr>
	 * @param value String representing the enumerated value.
	 * @return Parameter type.
	 */
	public static Enum<? extends IParameterType> fromString(String value)
	{
		if (value == null || value.trim().length() == 0)
		{
			throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CommandCategoryCannotBeNull));
		}

		for (ParameterType element : ParameterType.values())
		{
			if (element.name().equalsIgnoreCase(value))
			{
				return element;
			}
		}

		throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CannotCreateEnumerated, ParameterType.class.getName(), value));
	}

	/**
	 * Creates a parameter type enumerated value from a given type value.
	 * <p>
	 * <b>Example:</b><p> 
	 * <code>ParameterType.fromType(String.class.getName());</code>
	 * <hr>
	 * @param value String representing the type value.
	 * @return Parameter type.
	 */
	public static Enum<? extends IParameterType> fromType(String value)
	{
		if (value == null || value.trim().length() == 0)
		{
			throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CommandCategoryCannotBeNull));
		}

		for (ParameterType element : ParameterType.values())
		{
			if (element.getType().equals(value))
			{
				return element;
			}
		}

		throw new InvalidArgumentException(ResourceBundleManager.getMessage(BundleAthenaBase.CannotCreateEnumerated, ParameterType.class.getName(), value));
	}
}
