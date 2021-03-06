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
package com.heliosphere.athena.base.message.internal.protocol;

/**
 * Enumeration of the several possible message response types.
 * <p>
 * This is used when using message with enumeration protocols (by opposition to message protocols) and is associated
 * when the message category is of type: {@link MessageCategoryProtocol#REPLY}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum MessageResponseType
{
	/**
	 * Initial message request is confirmed.
	 */
	CONFIRMED,

	/**
	 * Initial message request is rejected.
	 */
	REJECTED;
}
