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
package com.heliosphere.athena.base.file.internal.xml;

import com.heliosphere.athena.base.file.internal.IStructuredFile;
import com.thoughtworks.xstream.XStream;

/**
 * Defines the behavior of a {@code XML} file having a header, a content and a footer part.
 * <hr>
 * @author  <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param 	<H> Header type.
 * @param 	<C> Content type.
 * @param 	<F> Footer type.
 */
public interface IXmlFile<H, C, F> extends IStructuredFile<H, C, F>
{
	/**
	 * Sets (append) aliases to the XML engine.
	 */
	void setAliases();

	/**
	 * Returns the {@code XML} engine used for serialization/deserialization.
	 * <hr>
	 * @return {@code XML} engine used.
	 */
	XStream getEngine();
}
