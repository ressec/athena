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

import com.heliosphere.athena.base.resource.ResourceException;

/**
 * Provides a concrete implementation of a File.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class File extends AbstractFile
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a new file.
	 * <hr>
	 * @param pathname File pathname.
	 * @throws ResourceException Thrown to indicate an error occurred when trying to access a resource.
	 */
	public File(String pathname) throws ResourceException
	{
		super(pathname);
	}
}
