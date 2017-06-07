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
 * Defines the behavior of a {@code File}.
 *
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public interface IFile extends Serializable
{
	/**
	 * Returns the resource representing the file.
	 * <hr>
	 * @return {@link IResource}.
	 */
	IResource getResource();
}
