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

/**
 * Interface defining the behavior of the footer of a structured file.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param 	<F> Type of the record constituting the footer of the file.
 */
public interface IFileFooter<F> extends Serializable
{
	/**
	 * Returns the footer of the file.
	 * <hr>
	 * @return File footer.
	 */
	F get();

	/**
	 * Sets the footer of the file.
	 * <hr>
	 * @param footer File footer.
	 */
	void set(F footer);
}