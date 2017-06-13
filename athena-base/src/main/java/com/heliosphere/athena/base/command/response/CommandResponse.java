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
import com.heliosphere.athena.base.command.internal.type.CommandCodeType;
import com.heliosphere.athena.base.command.internal.type.ICommandCodeType;

import lombok.NonNull;

public final class CommandResponse implements ICommandResponse
{
	/**
	 * Command status type.
	 */
	private CommandStatusType status = CommandStatusType.UNPROCESSED;

	/**
	 * Message resulting of the process of the command to be written to the console or terminal.
	 */
	private List<String> messages;

	/**
	 * List of exceptions raised during the processing of the command.
	 */
	private List<Exception> exceptions;

	/**
	 * Referenced command.
	 */
	private ICommand command;

	/**
	 * Order to execute in response to the command processing.
	 */
	private Enum<? extends ICommandCodeType> order;

	/**
	 * Creates a new command response given some values.
	 * <hr>
	 * @param command Referenced command.
	 * @param status Command processing status.
	 */
	public CommandResponse(final @NonNull ICommand command, final @NonNull CommandStatusType status)
	{
		this.command = command;
		this.order = CommandCodeType.DISPLAY_TERMINAL;
		this.status = status;
	}

	/**
	 * Creates a new command response given some values.
	 * <hr>
	 * @param command Referenced command.
	 * @param order Order type.
	 * @param status Command processing status.
	 */
	public CommandResponse(final @NonNull ICommand command, final @NonNull Enum<? extends ICommandCodeType> order, final @NonNull CommandStatusType status)
	{
		this.command = command;
		this.order = order;
		this.status = status;
	}

	@Override
	public final void addMessage(final @NonNull String message)
	{
		if (messages == null)
		{
			messages = new ArrayList<>();
		}

		messages.add(message);
	}

	@Override
	public final void addException(final @NonNull Exception exception)
	{
		if (exceptions == null)
		{
			exceptions = new ArrayList<>();
		}

		exceptions.add(exception);
	}

	@Override
	public final List<String> getMessages()
	{
		return messages;
	}

	@Override
	public final List<Exception> getExceptions()
	{
		return exceptions;
	}

	@Override
	public final void setOrder(Enum<? extends ICommandCodeType> order)
	{
		this.order = order;
	}

	@Override
	public final Enum<? extends ICommandCodeType> getOrder()
	{
		return order;
	}

	@Override
	public final ICommand getCommand()
	{
		return command;
	}

	@Override
	public final CommandStatusType getStatus()
	{
		return status;
	}
}
