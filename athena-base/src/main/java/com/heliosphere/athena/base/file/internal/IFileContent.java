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
package com.heliosphere.athena.base.file.internal;

import java.io.Serializable;
import java.util.List;

/**
 * Interface defining the behavior of the content of a structured file.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param 	<C> Type of record constituting the content of the file.
 */
public interface IFileContent<C> extends Serializable
{
	/**
	 * Returns a list of records constituting the content of the file.
	 * <hr>
	 * @return List of records constituting the file content.
	 */
	List<C> get();

	/**
	 * Sets the records constituting the content of the file.
	 * <hr>
	 * @param content List of records constituting the file content.
	 */
	void set(List<C> content);

	/**
	 * Adds a record to the file content.
	 * <hr>
	 * @param record Record to add.
	 */
	void add(C record);

	/**
	 * Removes a record from the of the file.
	 * <hr>
	 * @param record Record to remove.
	 */
	void remove(C record);

	/**
	 * Returns the number of records constituting the content of the file.
	 * <hr>
	 * @return Number of records.
	 */
	int size();
}