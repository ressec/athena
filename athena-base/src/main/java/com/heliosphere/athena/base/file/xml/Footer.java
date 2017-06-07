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
package com.heliosphere.athena.base.file.xml;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the element that is composing the xml file footer part.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class Footer
{
	/**
	 * Date and time the file has been created.
	 */
	@Getter
	@Setter
	private Date generated;

	/**
	 * Date and time the file has been changed.
	 */
	@Getter
	@Setter
	private Date changed;
}
