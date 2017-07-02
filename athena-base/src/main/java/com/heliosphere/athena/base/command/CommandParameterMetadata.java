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
package com.heliosphere.athena.base.command;

import java.util.ArrayList;
import java.util.List;

import com.heliosphere.athena.base.command.file.xml.converter.ParameterEnumConverter;
import com.heliosphere.athena.base.command.internal.ICommandParameterMetadata;
import com.heliosphere.athena.base.command.internal.exception.CommandInitializationException;
import com.heliosphere.athena.base.command.internal.protocol.ICommandProtocolType;
import com.heliosphere.athena.base.command.internal.protocol.IParameterType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

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
	 * Full command protocol name which is an enumeration extending the {@link ICommandProtocolType} 
	 * followed by the enumerated value of the command to associate with the parameter.
	 * <p>
	 * It can be {@code null} if the command protocol has been defined at the command level.
	 */
	@Getter
	@Setter
	@XStreamAlias("protocol")
	private String fullProtocolName;

	/**
	 * Command protocol type.
	 */
	@Setter
	@XStreamOmitField
	private Enum<? extends ICommandProtocolType> protocolType;

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
	 * Command parameter type.
	 */
	@Getter
	@Setter
	@XStreamAlias("type")
	@XStreamConverter(ParameterEnumConverter.class)
	private Enum<? extends IParameterType> type;

	/**
	 * Collection of examples.
	 */
	@Getter
	//@XStreamImplicit(itemFieldName = "example")
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

		try
		{
			initialize();
		}
		catch (CommandInitializationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public final void initialize() throws CommandInitializationException
	{
		setProtocol(fullProtocolName);
	}

	/**
	 * Sets the command protocol.
	 * <hr>
	 * @param fullProtocolName Full protocol name.
	 * @throws CommandInitializationException Thrown in case an error occurred while initializing the command.
	 */
	@SuppressWarnings({ "unchecked", "nls" })
	private final void setProtocol(String fullProtocolName) throws CommandInitializationException
	{
		Class<Enum<?>> enumClass;
		String protocolClassName = null;
		String value = null;

		if (fullProtocolName != null && !fullProtocolName.isEmpty())
		{
			int index = fullProtocolName.lastIndexOf('.');
			if (index != -1)
			{
				protocolClassName = fullProtocolName.substring(0, index);
				value = fullProtocolName.substring(index + 1, fullProtocolName.length());

				try
				{
					enumClass = (Class<Enum<?>>) Class.forName(protocolClassName);
					protocolType = ((ICommandProtocolType) (Enum<?>) enumClass.getEnumConstants()[0]).fromString(value);
				}
				catch (Exception e)
				{
					throw new CommandInitializationException(String.format("Unable to initialize command protocol [name=%1s, reason=%2s]", getName(), e.getMessage()));
				}
			}
		}
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

	@Override
	public final Enum<? extends ICommandProtocolType> getProtocolType()
	{
		return protocolType;
	}
}
