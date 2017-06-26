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
package com.heliosphere.athena.base.command;

import com.heliosphere.athena.base.command.internal.ICommandParameter;
import com.heliosphere.athena.base.command.internal.ICommandParameterMetadata;
import com.heliosphere.athena.base.command.protocol.DefaultParameterType;

import lombok.NonNull;

/**
 * Represents a command parameter.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class CommandParameter implements ICommandParameter
{
	/**
	 * Original parameter text.
	 */
	private String text;

	/**
	 * Parameter value.
	 */
	private Object value;

	/**
	 * Parameter metadata.
	 */
	private ICommandParameterMetadata metadata;

	/**
	 * Creates a new command parameter given some values.
	 * <hr>
	 * @param text Text representing the parameter as entered on a command-line or a terminal.
	 * @param metadata Command parameter metadata (definition).
	 */
	public CommandParameter(final @NonNull String text, final @NonNull ICommandParameterMetadata metadata)
	{
		this.text = text;
		this.metadata = metadata;
	}

	/**
	 * Creates a new command parameter given some values.
	 * <hr>
	 * @param text Text representing the parameter as entered on a command-line or a terminal.
	 * @param metadata Command parameter metadata (definition).
	 * @param value Parameter's value (can be {@code null} in case only the parameter tag is defined).
	 */
	public CommandParameter(final @NonNull String text, final @NonNull ICommandParameterMetadata metadata, final String value)
	{
		this.text = text;
		this.metadata = metadata;

		switch ((DefaultParameterType) metadata.getType())
		{
			case INTEGER:
				this.value = Integer.valueOf(value);
				break;

			case BOOLEAN:
				this.value = Boolean.valueOf(value);
				break;

			case LONG:
				this.value = Long.valueOf(value);
				break;

			case STRING:
				this.value = value;
				break;

			default:
				// Default is considered as a 'string'.
				this.value = value;
				break;
		}
	}

	@Override
	public final String getText()
	{
		return text;
	}

	@Override
	public final Object getValue()
	{
		return value;
	}

	@Override
	public final ICommandParameterMetadata getMetadata()
	{
		return metadata;
	}

	@Override
	public final void setMetadata(ICommandParameterMetadata metadata)
	{
		this.metadata = metadata;
	}
}
