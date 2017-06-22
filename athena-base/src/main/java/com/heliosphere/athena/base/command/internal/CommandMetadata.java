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

import com.heliosphere.athena.base.command.internal.type.ICommandCategoryType;
import com.heliosphere.athena.base.command.internal.type.ICommandCodeType;
import com.heliosphere.athena.base.command.internal.type.ICommandGroupType;
import com.heliosphere.athena.base.message.internal.IMessageType;
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
	 * Command category (string representation).
	 */
	@Getter
	@Setter
	private String category;

	/**
	 * Command group type (string representation).
	 */
	@Getter
	@Setter
	private String group;

	/**
	 * Command code type (string representation).
	 */
	@Getter
	@Setter
	private String code;

	/**
	 * Message type (string representation).
	 */
	@Getter
	@Setter
	private String message;

	/**
	 * Command category.
	 */
	@Getter
	@Setter
	//@XStreamConverter(CommandCategoryEnumConverter.class)
	@XStreamOmitField
	private Enum<? extends ICommandCategoryType> categoryType;

	/**
	 * Command group.
	 */
	@Getter
	@Setter
	//@XStreamConverter(CommandGroupEnumConverter.class)
	@XStreamOmitField
	private Enum<? extends ICommandGroupType> groupType;

	/**
	 * Command code.
	 */
	@Getter
	@Setter
	//@XStreamConverter(CommandCodeEnumConverter.class)
	@XStreamOmitField
	private Enum<? extends ICommandCodeType> codeType;

	/**
	 * Message type associated to the command.
	 */
	@Getter
	@Setter
	@XStreamOmitField
	private Enum<? extends IMessageType> messageType;

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
	 * @param category Command category.
	 * @param group Command group.
	 * @param code Command code.
	 * @param message Message.
	 * @param name Command name.
	 */
	public CommandMetadata(final @NonNull String category, final @NonNull String group, final @NonNull String code, final @NonNull String message, final @NonNull String name)
	{
		this.category = category;
		this.group = group;
		this.code = code;
		this.message = message;
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

	@SuppressWarnings({ "unchecked", "nls" })
	@Override
	public final void setProtocolCategoryClassName(String protocolClassName) throws CommandInitializationException
	{
		Class<Enum<?>> enumClass;
		
		try
		{
			enumClass = (Class<Enum<?>>) Class.forName(protocolClassName);
			categoryType = ((ICommandCategoryType) (Enum<?>) enumClass.getEnumConstants()[0]).fromString(category);
		}
		catch (Exception e)
		{
			throw new CommandInitializationException(String.format("Unable to initialize command [name=%1s, reason=%2s]", getName(), e.getMessage()));
		}
	}

	@SuppressWarnings({ "unchecked", "nls" })
	@Override
	public final void setProtocolGroupClassName(String protocolClassName) throws CommandInitializationException
	{
		Class<Enum<?>> enumClass;
		
		try
		{
			enumClass = (Class<Enum<?>>) Class.forName(protocolClassName);
			groupType = ((ICommandGroupType) (Enum<?>) enumClass.getEnumConstants()[0]).fromString(group);
		}
		catch (Exception e)
		{
			throw new CommandInitializationException(String.format("Unable to initialize command [name=%1s, reason=%2s]", getName(), e.getMessage()));
		}
	}

	@SuppressWarnings({ "unchecked", "nls" })
	@Override
	public final void setProtocolCodeClassName(String protocolClassName) throws CommandInitializationException
	{
		Class<Enum<?>> enumClass;
		
		try
		{
			enumClass = (Class<Enum<?>>) Class.forName(protocolClassName);
			codeType = ((ICommandCodeType) (Enum<?>) enumClass.getEnumConstants()[0]).fromString(code);
		}
		catch (Exception e)
		{
			throw new CommandInitializationException(String.format("Unable to initialize command [name=%1s, reason=%2s]", getName(), e.getMessage()));
		}
	}

	@SuppressWarnings({ "unchecked", "nls" })
	@Override
	public final void setProtocolMessageClassName(String protocolClassName) throws CommandInitializationException
	{
		Class<Enum<?>> enumClass;
		
		try
		{
			enumClass = (Class<Enum<?>>) Class.forName(protocolClassName);
			messageType = ((IMessageType) (Enum<?>) enumClass.getEnumConstants()[0]).fromString(message);
		}
		catch (Exception e)
		{
			throw new CommandInitializationException(String.format("Unable to initialize command [name=%1s, reason=%2s]", getName(), e.getMessage()));
		}
	}
}
