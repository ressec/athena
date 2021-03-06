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
import com.heliosphere.athena.base.command.internal.protocol.IParameterType;

/**
 * Provides a basic behavior for a command parameter metadata that is containing the definition of a command parameter.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICommandParameterMetadata
{
	/**
	 * Returns the command protocol type.
	 * <p>
	 * @return Command protocol type.
	 */
	Enum<? extends ICommandProtocolType> getProtocolType();

	/**
	 * Returns the command parameter name.
	 * <p>
	 * @return Command parameter name.
	 */
	String getName();

	/**
	 * Returns the command parameter description.
	 * <p>
	 * @return Command parameter description.
	 */
	String getDescription();

	/**
	 * Returns the command parameter tag.
	 * <p>
	 * @return Command parameter tag.
	 */
	String getTag();

	/**
	 * Returns the command parameter regular expression.
	 * <p>
	 * @return Command parameter regular expression.
	 */
	String getRegExp();

	/**
	 * Returns the command parameter type.
	 * <p>
	 * @return Command parameter type.
	 */
	Enum<? extends IParameterType> getType();

	/**
	 * Returns the command parameter examples.
	 * <p>
	 * @return Command parameter examples.
	 */
	List<String> getExamples();

	/**
	 * Adds an example to the list of parameter examples.
	 * <hr>
	 * @param example Parameter example.
	 */
	void addExample(String example);

	/**
	 * Initializes the command definition.
	 * <hr>
	 * @throws CommandInitializationException Thrown in case an error occurred while initializing the command definition.
	 */
	void initialize() throws CommandInitializationException;
}
