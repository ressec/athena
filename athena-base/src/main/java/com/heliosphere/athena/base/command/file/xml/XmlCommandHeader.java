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
package com.heliosphere.athena.base.command.file.xml.command;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.Getter;
import lombok.Setter;

/**
 * Represents the element that is composing the xml file header part for a {@link XmlCommandFile}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class XmlCommandHeader
{
	/**
	 * Company.
	 */
	@Getter
	@Setter
	private String company;

	/**
	 * File version.
	 */
	@Getter
	@Setter
	private String version;

	/**
	 * File author.
	 */
	@Getter
	@Setter
	private String author;

	/**
	 * File description.
	 */
	@Getter
	@Setter
	private String description;
	
	/**
	 * Protocol class name for the command category.
	 */
	@Getter
	@Setter
	@XStreamAlias("protocol-command-category")
	private String protocolCommandCategory;
	
	/**
	 * Protocol class name for the command group.
	 */
	@Getter
	@Setter
	@XStreamAlias("protocol-command-group")
	private String protocolCommandGroup;

	/**
	 * Protocol class name for the command code.
	 */
	@Getter
	@Setter
	@XStreamAlias("protocol-command-code")
	private String protocolCommandCode;
	
	/**
	 * Protocol class name for the message.
	 */
	@Getter
	@Setter
	@XStreamAlias("protocol-message")
	private String protocolMessage;	
}
