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

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the element that is composing the xml file header part.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class Header
{
	@Getter
	@Setter
	private String company;

	@Getter
	@Setter
	private String version;

	@Getter
	@Setter
	private String author;

	@Getter
	@Setter
	private String description;
}
