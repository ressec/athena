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

/**
 * Provides a basic behavior for a command parameter metadata that is containing the definition of a command parameter.
 * <hr>
 * @author <a href="mailto:christophe.resse@hotmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICommandParameterMetadata
{
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
	 * Returns the command parameter class type.
	 * <p>
	 * @return Command parameter class type.
	 */
	Class<?> getClassType();

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
}
