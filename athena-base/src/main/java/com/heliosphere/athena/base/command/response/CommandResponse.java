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
package com.heliosphere.athena.base.command.response;

import java.util.ArrayList;
import java.util.List;

import com.heliosphere.athena.base.command.internal.ICommand;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

public final class CommandResponse
{
	/**
	 * Command status type.
	 */
	@Getter
	@Setter
	private CommandStatusType status;

	/**
	 * Message resulting of the process of the command to be written to the console or terminal.
	 */
	@Getter
	private List<String> messages;

	/**
	 * List of exceptions raised during the processing of the command.
	 */
	@Getter
	private List<Exception> exceptions;

	/**
	 * Referenced command.
	 */
	@Getter
	private ICommand command;

	/**
	 * Creates a new command response given some values.
	 * <hr>
	 * @param command Referenced command.
	 * @param status Command processing status.
	 */
	public CommandResponse(final @NonNull ICommand command, final @NonNull CommandStatusType status)
	{
		this.command = command;
		this.status = status;
	}

	/**
	 * Adds a message to display on the console.
	 * <hr>
	 * @param message Message to add.
	 */
	public final void addMessage(final @NonNull String message)
	{
		if (messages == null)
		{
			messages = new ArrayList<>();
		}

		messages.add(message);
	}

	/**
	 * Adds an exception.
	 * <hr>
	 * @param exception Exception to add.
	 */
	public final void addException(final @NonNull Exception exception)
	{
		if (exceptions == null)
		{
			exceptions = new ArrayList<>();
		}

		exceptions.add(exception);
	}
}
