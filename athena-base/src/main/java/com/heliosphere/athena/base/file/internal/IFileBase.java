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

import com.heliosphere.athena.base.file.internal.resource.IResource;

/**
 * Interface defining the behavior of a very basic {@code file}.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public interface IFileBase extends Serializable
{
	/**
	 * Returns the underlying resource representing the physical file.
	 * <hr>
	 * @return File resource.
	 */
	IResource getResource();

	/**
	 * Loads the file.
	 * <hr>
	 * @throws FileException Thrown to indicate an error occurred while trying to load the file.
	 */
	void load() throws FileException;

	/**
	 * Saves the file.
	 * <hr>
	 * @throws FileException Thrown to indicate an error occurred while trying to save the file.
	 */
	void save() throws FileException;
}
