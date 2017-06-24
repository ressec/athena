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
package com.heliosphere.athena.base.test.command.file.xml;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.heliosphere.athena.base.command.file.xml.XmlCommandFile;
import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.command.internal.ICommandMetadata;
import com.heliosphere.athena.base.command.internal.ICommandParameterMetadata;
import com.heliosphere.athena.base.command.internal.exception.CommandException;
import com.heliosphere.athena.base.command.interpreter.CommandInterpreter;
import com.heliosphere.athena.base.command.protocol.DefaultCommandCategoryType;
import com.heliosphere.athena.base.command.protocol.DefaultCommandGroupType;

/**
 * Test class for the {@link XmlCommandFile} class.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class XmlChatCommandFileTest
{
	/**
	 * XML file containing the chat command definitions.
	 */
	@SuppressWarnings("nls")
	private XmlCommandFile chatCommandfile = new XmlCommandFile("/config/command/chat-commands.xml");

	/**
	 * Initialization of the test cases.
	 * <p>
	 * @throws Exception In case an error occurs during the initialization.
	 */
	@BeforeClass
	public static final void setUpBeforeClass() throws Exception
	{
		// Empty
	}

	/**
	 * Finalization of the test cases.
	 * <p>
	 * @throws Exception In case an error occurs during the finalization.
	 */
	@AfterClass
	public static final void tearDownAfterClass() throws Exception
	{
		// Empty
	}

	/**
	 * Sets up the fixture.
	 * <p>
	 * @throws Exception In case an error occurs during the setup phase.
	 */
	@Before
	public final void setUp() throws Exception
	{
		chatCommandfile.load();
	}

	/**
	 * Tears down the fixture.
	 * <p>
	 * @throws Exception In case an error occurs during the tear down phase.
	 */
	@After
	public final void tearDown() throws Exception
	{
		// Empty
	}

	/**
	 * Creates a resource based on a file with an absolute path name.
	 */
	@SuppressWarnings("nls")
	@Test
	public final void loadXmlFile()
	{
		try
		{
			Assert.assertTrue(chatCommandfile != null);
			Assert.assertTrue(chatCommandfile.getHeader().getAuthor().equals("Resse Christophe"));

			ICommandMetadata afkCommandDefinition = chatCommandfile.getContent().get(0);
			Assert.assertTrue(afkCommandDefinition.getName().equals("help"));

			ICommandMetadata whoCommandDefinition = chatCommandfile.getByName("who");
			ICommandParameterMetadata parameterDefinition = whoCommandDefinition.getParameters().get(0);
			Assert.assertTrue(parameterDefinition.getName().equals("name"));

			List<ICommandMetadata> normal = chatCommandfile.findByCategory(DefaultCommandCategoryType.NORMAL);
			List<ICommandMetadata> chat = chatCommandfile.findByGroup(DefaultCommandGroupType.CHAT);
			Assert.assertTrue(normal.size() != 0);
			Assert.assertTrue(chat.size() != 0);

			ICommandMetadata who = chatCommandfile.getByName("who");
			Assert.assertTrue(who != null);

			List<ICommandMetadata> commands = chatCommandfile.findByAlias("people");
			Assert.assertTrue(commands != null && commands.size() == 1);

			ICommandParameterMetadata parameter = who.getByName("server");
			Assert.assertTrue(parameter != null);
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
	}

	/**
	 */
	@SuppressWarnings("nls")
	@Test
	public final void interpretWhoCommand()
	{
		try
		{
			CommandInterpreter interpreter = new CommandInterpreter();
			interpreter.registerCommands(chatCommandfile.getContent());

			ICommand command = interpreter.interpret("    /     who      -n      Candy-sAlbukerque -l 14-18             ");
			Assert.assertTrue(command != null);
		}
		catch (CommandException e)
		{
			fail(e.getMessage());
		}
	}

	/**
	 * Test an invalid command interpretation.
	 * <hr>
	 * @throws CommandException Thrown in case an error occurred while interpreting or processing a command.
	 */
	@SuppressWarnings("nls")
	@Test(expected = CommandException.class)
	public final void interpretInvalidCommand() throws CommandException
	{
		CommandInterpreter interpreter = new CommandInterpreter();
		interpreter.registerCommands(chatCommandfile.getContent());
		interpreter.interpret("    /     clip    clatis    ");
	}
}
