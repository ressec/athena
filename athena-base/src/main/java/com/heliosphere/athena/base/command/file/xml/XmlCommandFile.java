/*
 * Copyright(c) 2016 - Heliosphere Corp.
 * ---------------------------------------------------------------------------
 * This file is part of the Heliosphere's project which is licensed under the 
 * Apache license version 2 and use is subject to license terms.
 * You should have received a copy of the license with the project's artifact
 * binaries and/or sources.
 * 
 * License can be consulted at http://www.apache.org/licenses/LICENSE-2.0
 * ---------------------------------------------------------------------------
 */
package com.heliosphere.athena.base.command.file.xml;

import java.util.ArrayList;
import java.util.List;

import com.heliosphere.athena.base.command.CommandMetadata;
import com.heliosphere.athena.base.command.CommandParameterMetadata;
import com.heliosphere.athena.base.command.internal.ICommandMetadata;
import com.heliosphere.athena.base.command.internal.ICommandParameterMetadata;
import com.heliosphere.athena.base.command.internal.exception.CommandInitializationException;
import com.heliosphere.athena.base.command.internal.protocol.ICommandCategoryType;
import com.heliosphere.athena.base.command.internal.protocol.ICommandGroupType;
import com.heliosphere.athena.base.file.internal.AbstractStructuredFile;
import com.heliosphere.athena.base.file.internal.FileException;
import com.heliosphere.athena.base.file.xml.Footer;
import com.heliosphere.athena.base.file.xml.Header;
import com.heliosphere.athena.base.file.xml.XmlFile;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;

/**
 * Represents a {@code XML} file containing commands' definitions.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
@Log4j
@XStreamAlias("file")
public final class XmlCommandFile extends XmlFile<Header, ICommandMetadata, Footer>
{
	/**
	 * Default serialization identifier.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Root XML node name.
	 */
	@SuppressWarnings("nls")
	private static final String FILE_ROOT_TAG = "chat-command-file";

	/**
	 * Creates a new XML file.
	 * <hr>
	 * @param pathname XML file path name.
	 */
	public XmlCommandFile(final @NonNull String pathname)
	{
		super(pathname);
	}

	@SuppressWarnings("nls")
	@Override
	public final void setAliases()
	{
		super.setAliases();

		// Aliases the main file tag.
		getEngine().alias(FILE_ROOT_TAG, this.getClass());

		// Aliases the 'content' list as 'parameters'.
		getEngine().aliasAttribute(AbstractStructuredFile.class, "content", "commands");

		// Aliases the header tag.
		//getEngine().alias("header", XmlCommandHeader.class);

		// Aliases the 'commands' tag.
		getEngine().alias("command", ICommandMetadata.class, CommandMetadata.class);
		getEngine().alias("parameter", ICommandParameterMetadata.class, CommandParameterMetadata.class);
		getEngine().alias("alias", String.class, String.class);
	}

	@Override
	public void load() throws FileException
	{
		super.load();

		for (ICommandMetadata command : getContent())
		{
			try
			{
				command.initialize();
//				command.setProtocolCategoryClassName(getHeader().getProtocolCommandCategory());
//				command.setProtocolGroupClassName(getHeader().getProtocolCommandGroup());
//				command.setProtocolCodeClassName(getHeader().getProtocolCommandCode());
//				command.setProtocolMessageClassName(getHeader().getProtocolMessage());
			}
			catch (CommandInitializationException e)
			{
				throw new FileException(e);
			}
		}
	}

	/**
	 * Finds commands matching the given command category.
	 * <hr>
	 * @param category Command category.
	 * @return List of commands.
	 */
	public final List<ICommandMetadata> findByCategory(final @NonNull Enum<? extends ICommandCategoryType> category)
	{
		List<ICommandMetadata> result = new ArrayList<>();

		for (ICommandMetadata command : getContent())
		{
			if (command.getProtocolCategory() == category)
			{
				result.add(command);
			}
		}

		return result;
	}

	/**
	 * Finds commands matching the given command group.
	 * @param group Command group.
	 * @return List of commands.
	 */
	public final List<ICommandMetadata> findByGroup(final @NonNull Enum<? extends ICommandGroupType> group)
	{
		List<ICommandMetadata> result = new ArrayList<>();

		for (ICommandMetadata command : getContent())
		{
			if (command.getProtocolGroup() == group)
			{
				result.add(command);
			}
		}

		return result;
	}

	/**
	 * Finds a command by its name.
	 * @param name Command name.
	 * @return Command if found, otherwise {@code null} is returned.
	 */
	public final ICommandMetadata getByName(final @NonNull String name)
	{
		for (ICommandMetadata command : getContent())
		{
			if (command.getName().equals(name))
			{
				return command;
			}
		}

		return null;
	}

	/**
	 * Finds a command by one of its alias. Be careful that more than one command could match the given alias.
	 * @param alias Command alias.
	 * @return List of commands matching the given alias.
	 */
	public final List<ICommandMetadata> findByAlias(final @NonNull String alias)
	{
		List<ICommandMetadata> result = new ArrayList<>();

		for (ICommandMetadata command : getContent())
		{
			for (String element : command.getAliases())
			{
				if (element.equals(alias))
				{
					result.add(command);
				}
			}
		}

		return result;
	}
}
