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

import com.heliosphere.athena.base.command.internal.exception.CommandInitializationException;
import com.heliosphere.athena.base.command.internal.protocol.ICommandProtocolType;

/**
 * Provides a basic behavior for a command metadata that is containing the definition of a command.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICommandMetadata
{
	/**
	 * Returns the command protocol type.
	 * <p>
	 * @return Command protocol type.
	 */
	Enum<? extends ICommandProtocolType> getProtocolType();

	/**
	 * Checks if the command definition is of the given command protocol?
	 * <hr>
	 * @param protocol Command protocol type.
	 * @return {@code True} if the command definition is of the given protocol, {@code false} otherwise.
	 */
	boolean isOf(Enum<? extends ICommandProtocolType> protocol);

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
	ICommandParameterMetadata getParameterByName(String name);

	/**
	 * Finds a parameter by its tag.
	 * <p>
	 * @param tag Parameter tag.
	 * @return Command parameter or {@code null} if no parameter with the given tag has been found.
	 */
	ICommandParameterMetadata getParameterByTag(String tag);

	/**
	 * Initializes the command definition.
	 * <hr>
	 * @throws CommandInitializationException Thrown in case an error occurred while initializing the command definition.
	 */
	void initialize() throws CommandInitializationException;
}
