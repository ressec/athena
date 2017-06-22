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

import java.util.ArrayList;
import java.util.List;

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
	 * Parameter values.
	 */
	private List<Object> values;

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
	 * @param values List of parameter's values.
	 */
	public CommandParameter(final @NonNull String text, final @NonNull ICommandParameterMetadata metadata, final @NonNull List<String> values)
	{
		this.text = text;
		this.metadata = metadata;

		this.values = new ArrayList<>();
		for (String value : values)
		{
			switch ((DefaultParameterType) metadata.getType())
			{
				case INTEGER:
					this.values.add(Integer.valueOf(value));
					break;

				case BOOLEAN:
					this.values.add(Boolean.valueOf(value));
					break;

				case LONG:
					this.values.add(Long.valueOf(value));
					break;

				case STRING:
					this.values.add(value);
					break;

				default:
					// Default is considered as a 'string'.
					this.values.add(value);
					break;
			}
		}
	}

	@Override
	public final String getText()
	{
		return text;
	}

	@Override
	public final List<Object> getValues()
	{
		return values;
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
