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

import com.heliosphere.athena.base.command.internal.ICommandMetadata;
import com.heliosphere.athena.base.command.internal.ICommandParameterMetadata;
import com.heliosphere.athena.base.command.internal.exception.CommandInitializationException;
import com.heliosphere.athena.base.command.internal.protocol.ICommandProtocolType;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

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
	 * Full command protocol name which is an enumeration extending the {@link ICommandProtocolType} 
	 * followed by the enumerated value of the command.
	 * <p>
	 * It can be {@code null} if the command protocols are defined at the parameter level.
	 */
	@Getter
	@Setter
	@XStreamAlias("protocol")
	private String fullProtocolName;

	/**
	 * Command processor class name.
	 */
	@Getter
	@Setter
	@XStreamAlias("processor")
	private String processorClassName;

	/**
	 * Command protocol type.
	 */
	@XStreamOmitField
	private Enum<? extends ICommandProtocolType> protocolType;

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
	 * Command syntax.
	 */
	@Getter
	@Setter
	private String syntax;

	/**
	 * Collection of command aliases.
	 */
	@Getter
	private List<String> aliases;

	/**
	 * Collection of command parameters.
	 */
	@Getter
	private List<ICommandParameterMetadata> parameters;

	/**
	 * Creates a new command metadata (definition).
	 * <hr>
	 * @param protocol Command full protocol type name.
	 * @param name Command name.
	 */
	public CommandMetadata(final @NonNull String protocol, final @NonNull String name)
	{
		this.fullProtocolName = protocol;
		this.name = name;

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

		// Initializes the parameters.
		for (ICommandParameterMetadata parameter : getParameters())
		{
			parameter.initialize();
		}
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
	public final ICommandParameterMetadata getParameterByName(final @NonNull String name)
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
	public final ICommandParameterMetadata getParameterByTag(final @NonNull String tag)
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
	public final Enum<? extends ICommandProtocolType> getProtocolType()
	{
		return protocolType;
	}

	@Override
	public final boolean isOf(Enum<? extends ICommandProtocolType> protocol)
	{
		// Is the command protocol defined at the command level?
		if (getProtocolType() != null)
		{
			return getProtocolType() == protocol;
		}

		// Command protocol is defined at parameter level.
		for (ICommandParameterMetadata parameter : getParameters())
		{
			if (parameter.getProtocolType() == protocol)
			{
				return true;
			}
		}

		return false;
	}
}
