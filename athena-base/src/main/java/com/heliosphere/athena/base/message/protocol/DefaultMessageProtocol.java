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
package com.heliosphere.athena.base.message.protocol;

import java.time.Duration;

import com.heliosphere.athena.base.message.internal.IMessageProtocol;

public class DefaultMessageProtocol implements IMessageProtocol
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Message used to submit a command.
	 * <hr>
	 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse - Heliosphere</a>
	 * @version 1.0.0
	 */
	public final static class SubmitCommand
	{
		/**
		 * Text representing the command.
		 */
		private final String text;

		/**
		 * Duration time to wait before executing the given command.
		 */
		private final Duration duration;

		/**
		 * Creates a new {@code submit command} message.
		 * <hr>
		 * @param text Text representing the command to submit.
		 * @param duration Duration to wait before executing the command.
		 */
		public SubmitCommand(String text, Duration duration)
		{
			this.text = text;
			this.duration = duration;
		}

		/**
		 * Returns the textual representation of the command.
		 * <hr>
		 * @return Text.
		 */
		public final String getText()
		{
			return text;
		}

		/**
		 * Returns the duration to wait before executing the command.
		 * <hr>
		 * @return Duration.
		 */
		public final Duration getDuration()
		{
			return duration;
		}
	}
}
