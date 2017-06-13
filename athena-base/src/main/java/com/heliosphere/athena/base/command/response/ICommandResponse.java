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

import java.util.List;

import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.command.internal.type.ICommandCodeType;
import com.heliosphere.athena.base.message.internal.IMessage;

/**
 * Provides a basic behavior for a command's response.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse</a>
 * @version 1.0.0
 */
public interface ICommandResponse extends IMessage
{
	/**
	 * Adds a message to the response.
	 * <hr>
	 * @param message Message to add.
	 */
	void addMessage(String message);

	/**
	 * Adds an exception to the response.
	 * <hr>
	 * @param exception Exception to add.
	 */
	void addException(Exception exception);

	/**
	 * Sets the order to take in response of the processed command.
	 * <hr>
	 * @param order Order code representing the action to execute.
	 */
	void setOrder(Enum<? extends ICommandCodeType> order);

	/**
	 * Returns the order to take in response of the processed command.
	 * <hr>
	 * @return Order code representing the action to execute.
	 */
	Enum<? extends ICommandCodeType> getOrder();

	/**
	 * Returns the referenced command.
	 * <hr>
	 * @return Referenced command.
	 */
	ICommand getCommand();

	/**
	 * Returns the list of messages.
	 * <hr>
	 * @return List of messages contained in the response.
	 */
	List<String> getMessages();

	/**
	 * Returns the list of exceptions.
	 * <hr>
	 * @return List of exceptions contained in the response.
	 */
	List<Exception> getExceptions();

	/**
	 * Returns the status of the response.
	 * <hr>
	 * @return Status.
	 */
	CommandStatusType getStatus();
}
