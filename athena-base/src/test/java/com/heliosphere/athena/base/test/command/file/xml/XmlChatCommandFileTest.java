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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import com.heliosphere.athena.base.command.protocol.DefaultCommandProtocol;

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
	private XmlCommandFile chatCommandfile = new XmlCommandFile("/config/command/chat-commands-v2.xml");

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
	 * Test an XML command file can be loaded.
	 */
	@Test
	public final void testLoadXmlCommandFile()
	{
		try
		{
			Assert.assertTrue(chatCommandfile != null);
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
	}

	/**
	 * Test the header of a XML command file.
	 */
	@SuppressWarnings("nls")
	@Test
	public final void testHeader()
	{
		try
		{
			String author = chatCommandfile.getHeader().getAuthor();
			String company = chatCommandfile.getHeader().getCompany();
			String version = chatCommandfile.getHeader().getVersion();

			Assert.assertTrue(author.equals("Resse Christophe"));
			Assert.assertTrue(company.equals("Heliosphere Ltd."));
			Assert.assertTrue(version.equals("1.0"));
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
	}

	/**
	 * Test the footer of a XML command file.
	 */
	@SuppressWarnings("nls")
	@Test
	public final void testFooter()
	{
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		try
		{
			Date changed = chatCommandfile.getFooter().getChanged();
			Date generated = chatCommandfile.getFooter().getGenerated();

			Assert.assertTrue(sdf.format(changed).equals("2017-06-06 23:15:17"));
			Assert.assertTrue(sdf.format(generated).equals("2017-06-03 12:48:03"));
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
	}

	/**
	 * Test we can retrieve a command by its name.
	 */
	@SuppressWarnings("nls")
	@Test
	public final void testFindCommandByName()
	{
		ICommandMetadata quit = chatCommandfile.getByName("quit");
		Assert.assertTrue(quit.getName().equals("quit"));
	}

	/**
	 * Test we can retrieve a command parameter.
	 */
	@SuppressWarnings("nls")
	@Test
	public final void testGetCommandParameter()
	{
		ICommandMetadata help = chatCommandfile.getByName("help");
		ICommandParameterMetadata category = help.getParameterByName("category");
		Assert.assertTrue(category != null && category.getTag().equals("-cat"));
	}

	/**
	 * Test we can retrieve a command by one of its alias.
	 */
	@SuppressWarnings("nls")
	@Test
	public final void testFindCommandByAlias()
	{
		ICommandMetadata help = chatCommandfile.findByAlias("h").get(0);
		Assert.assertTrue(help.getName().equals("help"));
	}

	/**
	 * Test the {@link CommandInterpreter} interprets correctly several forms of 
	 * the {@link DefaultCommandProtocol#HELP} command.
	 */
	@SuppressWarnings("nls")
	@Test
	public final void testInterpretHelpCommand()
	{
		try
		{
			CommandInterpreter interpreter = new CommandInterpreter();
			interpreter.registerCommands(chatCommandfile.getContent());

			ICommand command = interpreter.interpret("    /     help      -cat         ");
			Assert.assertTrue(command != null);

			command = interpreter.interpret("    /     help          ");
			Assert.assertTrue(command != null);

			command = interpreter.interpret("    /     help      -name=help    ");
			Assert.assertTrue(command != null);
		}
		catch (CommandException e)
		{
			fail(e.getMessage());
		}
	}

	//	@SuppressWarnings("nls")
	//	@Test
	//	public final void interpretWhoCommand()
	//	{
	//		try
	//		{
	//			CommandInterpreter interpreter = new CommandInterpreter();
	//			interpreter.registerCommands(chatCommandfile.getContent());
	//
	//			ICommand command = interpreter.interpret("    /     who      -n      Candy-sAlbukerque -l 14-18             ");
	//			Assert.assertTrue(command != null);
	//		}
	//		catch (CommandException e)
	//		{
	//			fail(e.getMessage());
	//		}
	//	}

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
