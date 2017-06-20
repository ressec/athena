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
package com.heliosphere.athena.base.message.internal.type;

/**
 * Enumeration of the several possible message category types.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public enum MessageCategoryType
{
	/**
	 * Message is a request ; meaning that it will be associated with a reply message.
	 */
	REQUEST,

	/**
	 * Message is a reply ; meaning that its associated to a previously sent request message.
	 */
	REPLY,

	/**
	 * Message is a notification.
	 */
	NOTIFICATION;
}
