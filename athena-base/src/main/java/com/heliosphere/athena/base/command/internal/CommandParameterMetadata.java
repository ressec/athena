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

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

/**
 * Represents a command parameter metadata (definition).
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class CommandParameterMetadata implements ICommandParameterMetadata
{
	/**
	 * Command parameter name.
	 */
	@Getter
	@Setter
	private String name;

	/**
	 * Command parameter description.
	 */
	@Getter
	@Setter
	private String description;

	/**
	 * Command parameter tag.
	 */
	@Getter
	@Setter
	private String tag;

	/**
	 * Command parameter regular expression.
	 */
	@Getter
	@Setter
	@XStreamAlias("regexp")
	private String regExp;

	/**
	 * Command parameter class type.
	 */
	@Getter
	@Setter
	@XStreamAlias("type")
	private Class<?> classType;

	/**
	 * Collection of examples.
	 */
	@Getter
	@XStreamImplicit(itemFieldName = "example")
	private List<String> examples;

	/**
	 * Creates a new command parameter metadata (definition).
	 * <hr>
	 * @param name Command parameter name.
	 * @param description Command parameter description.
	 * @param tag Command parameter tag.
	 */
	public CommandParameterMetadata(final @NonNull String name, final @NonNull String description, final @NonNull String tag)
	{
		this.name = name;
		this.description = description;
		this.tag = tag;
	}

	@Override
	public final void addExample(final @NonNull String example)
	{
		if (examples == null)
		{
			examples = new ArrayList<>();
		}

		examples.add(example);
	}
}
