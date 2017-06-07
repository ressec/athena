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
package com.heliosphere.athena.base.file.internal.xml;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TimeZone;

import com.heliosphere.athena.base.file.internal.AbstractStructuredFile;
import com.heliosphere.athena.base.file.internal.FileException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.XStreamException;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import com.thoughtworks.xstream.converters.basic.DateConverter;

import lombok.Getter;

/**
 * Provides an abstract implementation of a XML file.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Resse Christophe - Heliosphere</a>
 * @version 1.0.0
 * @param <H> Record type of the header.
 * @param <C> Record type of the content.
 * @param <F> Record type of the footer.
 */
public abstract class AbstractXmlFile<H, C, F> extends AbstractStructuredFile<H, C, F> implements IXmlFile<H, C, F>
{
	/**
	 * Default serialization identifier.
	 */
	@XStreamOmitField
	private static final long serialVersionUID = 1L;

	/**
	 * XStream date converter.
	 */
	@SuppressWarnings("nls")
	@XStreamOmitField
	private static final DateConverter dateConverter = new DateConverter("yyyy-MM-dd HH:mm:ss", new String[] {}, TimeZone.getDefault());

	/**
	 * XML engine to use.
	 */
	@XStreamOmitField
	@Getter
	private XStream engine;

	/**
	 * Initializes the {@code XML} engine.
	 */
	private void initialize()
	{
		engine = new XStream();
		engine.registerConverter(dateConverter);
		engine.autodetectAnnotations(true);
	}

	/**
	 * Creates a new abstract XML file.
	 * <hr>
	 * @param pathname XML file path name.
	 */
	public AbstractXmlFile(String pathname)
	{
		super(pathname);

		initialize();
	}

	@Override
	public void setAliases()
	{
		// Empty.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void load() throws FileException
	{
		setAliases();

		try
		{
			IXmlFile<H, C, F> holder = (IXmlFile<H, C, F>) engine.fromXML(getResource().getFile());
			setHeader(holder.getHeader());
			setFooter(holder.getFooter());
			setContent(holder.getContent());
		}
		catch (XStreamException e)
		{
			throw new FileException(e);
		}
	}

	@Override
	public void save() throws FileException
	{
		setAliases();

		if (getResource() != null)
		{
			try (FileOutputStream output = new FileOutputStream(getResource().getFile().getAbsolutePath()))
			{
				engine.toXML(this, output);
			}
			catch (XStreamException | IOException e)
			{
				throw new FileException(e);
			}
		}
	}
}
