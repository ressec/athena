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
package com.heliosphere.athena.base.terminal;

import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.command.internal.type.CommandCategoryType;
import com.heliosphere.athena.base.command.response.CommandResponse;
import com.heliosphere.athena.base.command.response.CommandStatusType;
import com.heliosphere.athena.base.file.internal.FileException;

import lombok.NonNull;

public class Terminal extends AbstractTerminal
{
	/**
	 * Creates a new basic terminal.
	 */
	public Terminal()
	{
		super();
	}

	/**
	 * Creates a new basic terminal given the path name of an XMl file containing commands to register.
	 * <hr>
	 * @param pathname XML path name containing the commands to register. 
	 * @throws FileException In case an error occurred while trying to access the file.
	 */
	public Terminal(final @NonNull String pathname) throws FileException
	{
		super(pathname);
	}

	@SuppressWarnings("nls")
	@Override
	public final CommandResponse process(final @NonNull ICommand command)
	{
		CommandResponse response = new CommandResponse(command, CommandStatusType.PROCESSED);

		switch ((CommandCategoryType) command.getMetadata().getCategory())
		{
			case NORMAL:
				if (command.getMetadata().getName().equals("afk"))
				{
					response.addMessage("You are now away from the keyboard");
				}
				else
					if (command.getMetadata().getName().equals("who"))
					{
						response.addMessage("We are querying the server for your request...");
					}
				break;

			case ADMINISTRATION:
			case DEBUG:
			case SUPER_ADMINISTRATION:
			case SYSTEM:

			default:
				break;
		}

		return response;
	}

	@Override
	public final void process(CommandResponse response)
	{
		switch (response.getStatus())
		{
			case UNPROCESSED:
				getTerminal().println("The command: " + response.getCommand().getMetadata().getName() + " has not been processed!");
				break;

			case PROCESSED:
				if (response.getMessages() != null)
				{
					for (String message : response.getMessages())
					{
						getTerminal().println(message);
					}
				}
				else
				{
					getTerminal().println("> command: " + response.getCommand().getMetadata().getName() + " has been processed sucessfully.");
				}
				break;

			case FAILED:
				if (response.getMessages() != null)
				{
					for (String message : response.getMessages())
					{
						getTerminal().println(message);
					}
				}

				if (response.getExceptions() != null)
				{
					for (Exception exception : response.getExceptions())
					{
						getTerminal().println(exception.getMessage());
					}
				}
				else
				{
					getTerminal().println("The command: " + response.getCommand().getMetadata().getName() + " has failed!");
				}
				break;

			default:
				getTerminal().println("Unknown command: " + response.getCommand().getMetadata().getName());
				break;
		}
	}
}
