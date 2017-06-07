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
import com.heliosphere.athena.base.command.internal.ICommandMetadata;
import com.heliosphere.athena.base.command.internal.ICommandParameterMetadata;
import com.heliosphere.athena.base.command.internal.type.CommandCategoryType;
import com.heliosphere.athena.base.command.internal.type.CommandGroupType;

/**
 * Test class for the {@link XmlCommandFile} class.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 */
public class XmlChatCommandFileTest
{
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
		// Empty
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
	@SuppressWarnings({ "static-method", "nls" })
	@Test
	public final void loadXmlFile()
	{
		try
		{
			XmlCommandFile file = new XmlCommandFile("/config/command/chat-commands.xml");

			file.load();

			Assert.assertTrue(file != null);

			Assert.assertTrue(file.getHeader().getAuthor().equals("Resse Christophe"));

			ICommandMetadata afkCommandDefinition = file.getContent().get(0);
			Assert.assertTrue(afkCommandDefinition.getName().equals("afk"));

			ICommandMetadata whoCommandDefinition = file.getContent().get(1);
			ICommandParameterMetadata parameterDefinition = whoCommandDefinition.getParameters().get(0);
			Assert.assertTrue(parameterDefinition.getName().equals("name"));

			List<ICommandMetadata> normal = file.findByCategory(CommandCategoryType.NORMAL);
			List<ICommandMetadata> chat = file.findByGroup(CommandGroupType.CHAT);
			Assert.assertTrue(normal.size() != 0);
			Assert.assertTrue(chat.size() != 0);

			ICommandMetadata who = file.getByName("who");
			Assert.assertTrue(who != null);

			List<ICommandMetadata> commands = file.findByAlias("people");
			Assert.assertTrue(commands != null && commands.size() == 1);

			ICommandParameterMetadata parameter = who.getByName("server");
			Assert.assertTrue(parameter != null);
		}
		catch (Exception e)
		{
			fail(e.getMessage());
		}
	}
}
