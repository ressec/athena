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

import com.heliosphere.athena.base.file.internal.resource.IResource;
import com.heliosphere.athena.base.file.internal.resource.Resource;
import com.heliosphere.athena.base.resource.ResourceException;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * Provides an abstract implementation of a file.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class AbstractFile implements IFile
{
	/**
	 * Default serialization identifier.
	 */
	@XStreamOmitField
	private static final long serialVersionUID = 1L;

	/**
	 * Resource representing this file.
	 */
	@XStreamOmitField
	private IResource resource;

	/**
	 * Creates a new abstract file.
	 * <hr>
	 * @param pathname File pathname.
	 * @throws ResourceException Thrown to indicate an error occurred when trying to access a resource.
	 */
	public AbstractFile(String pathname) throws ResourceException
	{
		resource = new Resource(pathname);
	}

	/**
	 * Default constructor.
	 */
	protected AbstractFile()
	{
	}

	@Override
	public final IResource getResource()
	{
		return resource;
	}
}
