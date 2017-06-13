/*
 * Copyright(c) 2016 - Heliosphere Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Heliosphere's project which is licensed under the 
 * Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package com.heliosphere.athena.base.command.internal;

import java.util.ArrayList;
import java.util.List;

import com.heliosphere.athena.base.command.file.xml.converter.CommandCategoryEnumConverter;
import com.heliosphere.athena.base.command.file.xml.converter.CommandCodeEnumConverter;
import com.heliosphere.athena.base.command.file.xml.converter.CommandGroupEnumConverter;
import com.heliosphere.athena.base.command.internal.type.ICommandCategoryType;
import com.heliosphere.athena.base.command.internal.type.ICommandCodeType;
import com.heliosphere.athena.base.command.internal.type.ICommandGroupType;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Represents a command metadata (definition).
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class CommandMetadata implements ICommandMetadata
{
	/**
	 * Command name.
	 */
	@Getter
	@Setter
	private String name;

	/**
	 * Command description.
	 */
	@Getter
	@Setter
	private String description;

	/**
	 * Command category.
	 */
	@Getter
	@Setter
	@XStreamConverter(CommandCategoryEnumConverter.class)
	private Enum<? extends ICommandCategoryType> category;

	/**
	 * Command group.
	 */
	@Getter
	@Setter
	@XStreamConverter(CommandGroupEnumConverter.class)
	private Enum<? extends ICommandGroupType> group;

	/**
	 * Command code.
	 */
	@Getter
	@Setter
	@XStreamConverter(CommandCodeEnumConverter.class)
	private Enum<? extends ICommandCodeType> code;

	/**
	 * Command syntax.
	 */
	@Getter
	@Setter
	private String syntax;

	/**
	 * Collection of command aliases.
	 */
	@Getter
	@XStreamImplicit(itemFieldName = "alias")
	private List<String> aliases;

	/**
	 * Collection of command parameters.
	 */
	@Getter
	//@XStreamImplicit(itemFieldName = "parameter")
	private List<ICommandParameterMetadata> parameters;

	/**
	 * Creates a new command metadata (definition).
	 * <hr>
	 * @param category Command category type.
	 * @param group Command group type.
	 * @param code Command code type.
	 * @param name Command name.
	 */
	public CommandMetadata(final @NonNull Enum<? extends ICommandCategoryType> category, final @NonNull Enum<? extends ICommandGroupType> group, final @NonNull Enum<? extends ICommandCodeType> code, final @NonNull String name)
	{
		this.category = category;
		this.group = group;
		this.code = code;
		this.name = name;
	}

	@Override
	public final void addParameter(final @NonNull ICommandParameterMetadata parameter)
	{
		if (parameters == null)
		{
			parameters = new ArrayList<>();
		}

		parameters.add(parameter);
	}

	@Override
	public final ICommandParameterMetadata getByName(final @NonNull String name)
	{
		for (ICommandParameterMetadata parameter : getParameters())
		{
			if (parameter.getName().equals(name))
			{
				return parameter;
			}
		}

		return null;
	}

	@Override
	public final ICommandParameterMetadata getByTag(final @NonNull String tag)
	{
		for (ICommandParameterMetadata parameter : getParameters())
		{
			if (parameter.getTag().equals(tag))
			{
				return parameter;
			}
		}

		return null;
	}
}
