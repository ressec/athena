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

import java.util.List;

/**
 * Interface defining the behavior of a structured file.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param <H> Type of record representing the file header.
 * @param <C> Type of record representing the the content.
 * @param <F> Type of record representing the file footer.
 */
public interface IStructuredFile<H, C, F> extends IFileBase
{
	/**
	 * Returns the file header.
	 * <hr>
	 * @return File header.
	 */
	H getHeader();

	/**
	 * Sets the file header.
	 * <hr>
	 * @param header File header to set.
	 */
	void setHeader(final H header);

	/**
	 * Returns the file content list of elements.
	 * <hr>
	 * @return File content.
	 */
	List<C> getContent();

	/**
	 * Sets the file content list of elements.
	 * <hr>
	 * @param content File content to set.
	 */
	void setContent(List<C> content);

	/**
	 * Returns the file footer.
	 * <hr>
	 * @return File footer.
	 */
	F getFooter();

	/**
	 * Sets the file footer.
	 * <hr>
	 * @param footer File footer to set.
	 */
	void setFooter(final F footer);
}
