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
package com.heliosphere.athena.base.command.internal;

import java.util.List;

import com.heliosphere.athena.base.command.internal.type.ICommandCategoryType;
import com.heliosphere.athena.base.command.internal.type.ICommandCodeType;
import com.heliosphere.athena.base.command.internal.type.ICommandGroupType;
import com.heliosphere.athena.base.message.internal.IMessageType;

/**
 * Provides a basic behavior for a command metadata that is containing the definition of a command.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICommandMetadata
{
	/**
	 * Returns the command category type.
	 * <p>
	 * @return Command category type.
	 */
	Enum<? extends ICommandCategoryType> getCategoryType();

	/**
	 * Sets the command category protocol class name.
	 * <hr>
	 * @param protocol Protocol class name (enumeration class name).
	 * @throws CommandInitializationException Thrown to indicate an error occurred while initializing the command.
	 */
	void setProtocolCategoryClassName(String protocol) throws CommandInitializationException;

	/**
	 * Returns the command group type.
	 * <p>
	 * @return Command group type.
	 */
	Enum<? extends ICommandGroupType> getGroupType();

	/**
	 * Sets the command group protocol class name.
	 * <hr>
	 * @param protocol Protocol class name (enumeration class name).
	 * @throws CommandInitializationException Thrown to indicate an error occurred while initializing the command.
	 */
	void setProtocolGroupClassName(String protocol) throws CommandInitializationException;

	/**
	 * Returns the command code type.
	 * <p>
	 * @return Command code type.
	 */
	Enum<? extends ICommandCodeType> getCodeType();

	/**
	 * Sets the command code protocol class name.
	 * <hr>
	 * @param protocol Protocol class name (enumeration class name).
	 * @throws CommandInitializationException Thrown to indicate an error occurred while initializing the command.
	 */
	void setProtocolCodeClassName(String protocol) throws CommandInitializationException;

	/**
	 * Returns the message type.
	 * <p>
	 * @return Message type.
	 */
	Enum<? extends IMessageType> getMessageType();

	/**
	 * Sets the message protocol class name.
	 * <hr>
	 * @param protocol Protocol class name (enumeration class name).
	 * @throws CommandInitializationException Thrown to indicate an error occurred while initializing the command.
	 */
	void setProtocolMessageClassName(String protocol) throws CommandInitializationException;

	/**
	 * Returns the command name.
	 * <p>
	 * @return Command name.
	 */
	String getName();

	/**
	 * Returns the command description.
	 * <p>
	 * @return Command description.
	 */
	String getDescription();

	/**
	 * Returns the command syntax.
	 * <p>
	 * @return Command syntax.
	 */
	String getSyntax();

	/**
	 * Returns the command aliases.
	 * <p>
	 * @return Command aliases.
	 */
	List<String> getAliases();

	/**
	 * Returns the command parameters.
	 * <p>
	 * @return Command parameters.
	 */
	List<ICommandParameterMetadata> getParameters();

	/**
	 * Adds a parameter metadata (definition).
	 * <p>
	 * @param parameter Parameter definition to add.
	 */
	void addParameter(ICommandParameterMetadata parameter);

	/**
	 * Finds a parameter by its name.
	 * <p>
	 * @param name Parameter name.
	 * @return Command parameter or {@code null} if no parameter with the given name has been found.
	 */
	ICommandParameterMetadata getByName(String name);

	/**
	 * Finds a parameter by its tag.
	 * <p>
	 * @param tag Parameter tag.
	 * @return Command parameter or {@code null} if no parameter with the given tag has been found.
	 */
	ICommandParameterMetadata getByTag(String tag);
}
