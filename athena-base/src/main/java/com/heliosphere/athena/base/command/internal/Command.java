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
package com.heliosphere.athena.base.command.internal;

import java.util.ArrayList;
import java.util.List;

import lombok.NonNull;

public class Command implements ICommand
{
	/**
	 * Original text of the command (as entered on a command line or similar).
	 */
	private String text;

	/**
	 * Command definition (metadata).
	 */
	private ICommandMetadata metadata = null;

	/**
	 * Command parameters.
	 */
	private List<ICommandParameter> parameters = null;

	/**
	 * Creates a new command given its text form.
	 * <hr>
	 * @param text Original text of the command.
	 */
	public Command(final @NonNull String text)
	{
		this.text = text;
	}

	@Override
	public final String getText()
	{
		return text;
	}

	@Override
	public final List<ICommandParameter> getParameters()
	{
		return parameters;
	}

	@Override
	public final ICommandMetadata getMetadata()
	{
		return metadata;
	}

	@Override
	public final void setMetadata(ICommandMetadata metadata)
	{
		this.metadata = metadata;
	}

	@Override
	public final void addParameter(ICommandParameter parameter)
	{
		if (parameters == null)
		{
			parameters = new ArrayList<>();
		}

		parameters.add(parameter);
	}
}
