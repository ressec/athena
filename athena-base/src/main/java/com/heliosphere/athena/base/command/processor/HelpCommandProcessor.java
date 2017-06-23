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
package com.heliosphere.athena.base.command.processor;

import java.util.ArrayList;
import java.util.List;

import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.command.internal.ICommandMetadata;
import com.heliosphere.athena.base.command.internal.ICommandParameter;
import com.heliosphere.athena.base.command.internal.exception.CommandException;
import com.heliosphere.athena.base.command.internal.processor.AbstractCommandProcessor;
import com.heliosphere.athena.base.command.internal.protocol.ICommandCodeType;

public final class HelpCommandProcessor extends AbstractCommandProcessor
{
	/**
	 * Command code type.
	 */
	private Enum<? extends ICommandCodeType> type;

	/**
	 * Command definitions.
	 */
	private List<ICommandMetadata> definitions = null;

	/**
	 * Creates a new command processor for a given command code type.
	 * <hr>
	 * @param type Command code type.
	 * @param definitions List of command definitions.
	 */
	public HelpCommandProcessor(Enum<? extends ICommandCodeType> type, List<ICommandMetadata> definitions)
	{
		this.type = type;
		this.definitions = definitions;
	}

	@Override
	public final Object execute(final ICommand command) throws CommandException
	{
		if (command != null)
		{
			if (command.getParameters() == null)
			{
				return extractAllCommands(command);
			}

			for (ICommandParameter parameter : command.getParameters())
			{
				if (parameter.getMetadata().getName().equals("category"))
				{
					return extractCommandCategories(command);
				}
				if (parameter.getMetadata().getName().equals("set"))
				{
					return extractCommandsForCategory(command);
				}
				if (parameter.getMetadata().getName().equals("command"))
				{
					return extractCommand(command);
				}
			}
		}

		return "Unable to process the command!";
	}

	@SuppressWarnings("nls")
	private final List<String> extractAllCommands(final ICommand command)
	{
		List<String> results = new ArrayList<>();

		for (ICommandMetadata metadata : definitions)
		{
			results.add(String.format("|   %1s - %2s", metadata.getName(), metadata.getDescription()));
		}

		return results;
	}

	private final List<String> extractCommandCategories(final ICommand command)
	{
		List<String> results = new ArrayList<>();

		Enum<?>[] enums = command.getMetadata().getCategoryType().getDeclaringClass().getEnumConstants();
		for (int i = 0; i < enums.length; i++)
		{
			results.add(enums[i].name());
		}

		return results;
	}

	private final List<String> extractCommandsForCategory(final ICommand command)
	{
		List<String> results = new ArrayList<>();

		return results;
	}

	private final List<String> extractCommand(final ICommand command)
	{
		List<String> results = new ArrayList<>();

		return results;
	}
}
