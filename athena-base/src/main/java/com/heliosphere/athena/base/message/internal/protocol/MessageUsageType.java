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
 * Enumeration of the several possible message usage types.
 * <p>
 * The most common one will always be {@link MessageUsageType#NONE} but this enumeration 
 * exist to avoid having to create two many message protocols.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum MessageUsageType
{
	/**
	 * No restriction on the usage of the message type ; meaning it can be used on any side of an application.
	 */
	NONE,

	/**
	 * Internal usage only ; meaning that a client's component cannot make use of this type of message.
	 */
	SERVER_ONLY,

	/**
	 * Client side usage only ; meaning that a server's component cannot make use of this type of message.
	 */
	CLIENT_ONLY;
}
