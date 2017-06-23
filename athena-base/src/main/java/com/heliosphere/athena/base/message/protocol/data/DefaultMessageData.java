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
package com.heliosphere.athena.base.message.protocol.data;

import com.heliosphere.athena.base.message.internal.IMessageContent;

import lombok.Getter;
import lombok.Setter;

/**
 * Default message data for testing purpose only.
 * <p>
 * <b>Note:</b><br>
 * You should not use this protocol data but define your(s) according to your needs.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public final class DefaultMessageData implements IMessageContent
{
	/**
	 * Default serialization identifier. 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private String data;
}
