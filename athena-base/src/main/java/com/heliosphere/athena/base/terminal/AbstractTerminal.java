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

import java.awt.Color;
import java.io.FileInputStream;
import java.util.Properties;

import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

import org.beryx.textio.TextTerminal;
import org.beryx.textio.swing.SwingTextTerminal;

import com.heliosphere.athena.base.file.internal.FileException;
import com.heliosphere.athena.base.file.internal.resource.IResource;
import com.heliosphere.athena.base.file.internal.resource.Resource;

public abstract class AbstractTerminal implements Runnable
{
	/**
	 * Terminal state.
	 */
	protected TerminalStatusType status = TerminalStatusType.UNKNOWN;

	/**
	 * Wait interval.
	 */
	protected static volatile int WAIT_INTERVAL = 100;

	/**
	 * Terminal thread.
	 */
	private volatile Thread thread = null;

	/**
	 * Text IO entry point.
	 */
	protected SwingTextTerminal io = null;

	/**
	 * Creates a new abstract terminal.
	 */
	private AbstractTerminal()
	{
		io = new SwingTextTerminal();
	}

	/**
	 * Creates a new abstract terminal.
	 * <hr>
	 * @param name Terminal's window title.
	 * @param config Terminal configuration file name.
	 * @throws FileException Thrown in case an error occurred while trying to access the file.
	 */
	public AbstractTerminal(String name, String config) throws FileException
	{
		this();

		Properties properties = new Properties();
		IResource resource = new Resource(config);

		try (FileInputStream input = new FileInputStream(resource.getFile()))
		{
			properties.load(input);
			io.initProperties(properties);
			io.getFrame().setTitle(name);
		}
		catch (Exception e)
		{
			throw new FileException(e);
		}
	}

	/**
	 * Returns the terminal's thread status.
	 * <hr>
	 * @return Status.
	 */
	public final TerminalStatusType getStatus()
	{
		return status;
	}

	/**
	 * Starts the terminal.
	 */
	public final void start()
	{
		if (status == TerminalStatusType.UNKNOWN || status == TerminalStatusType.STOPPED)
		{
			thread = new Thread(this);
			status = TerminalStatusType.RUNNING;
			thread.start();
		}
	}

	/**
	 * Stops the terminal.
	 */
	public final void stop()
	{
		if (status == TerminalStatusType.RUNNING)
		{
			status = TerminalStatusType.STOPPED;
		}
	}

	/**
	 * resumes the terminal.
	 */
	public final void resume()
	{
		if (status == TerminalStatusType.PAUSED)
		{
			status = TerminalStatusType.RUNNING;
		}
	}

	/**
	 * Pauses the terminal.
	 */
	public final void pause()
	{
		if (status == TerminalStatusType.RUNNING)
		{
			status = TerminalStatusType.PAUSED;
		}
	}

	/**
	 * Returns the underlying text terminal.
	 * <hr>
	 * @return Text terminal.
	 */
	@SuppressWarnings("rawtypes")
	public final TextTerminal getTerminal()
	{
		return io;
	}

	/**
	 * Sets the terminal title.
	 * <hr>
	 * @param title Title to set.
	 */
	public final void setTitle(final String title)
	{
		io.getFrame().setTitle(title);
	}

	/**
	 * Sets the background color.
	 * <hr>
	 * @param color Background color to set.
	 */
	public final void setBackgroundColor(final Color color)
	{
		io.getFrame().setBackground(color);
	}

	/**
	 * Appends some styled text to the text pane.
	 * <hr>
	 * @param text Text to be written to the console.
	 * @param color Foreground color to use to write the text.
	 */
	@SuppressWarnings({ "nls", "boxing" })
	public final void appendToPane(final String text, final Color color)
	{
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

		//		aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Menlo Regular");
		//		aset = sc.addAttribute(aset, StyleConstants.FontSize, 12);
		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);

		int len = io.getTextPane().getDocument().getLength();
		io.getTextPane().setCaretPosition(len);
		io.getTextPane().setCharacterAttributes(aset, false);
		io.getTextPane().replaceSelection(text);
	}

	/**
	 * Appends some styled text to the text pane.
	 * <hr>
	 * @param text Text to be written to the console.
	 * @param color Foreground color to use to write the text.
	 * @param fontFamily Font family.
	 * @param fontSize Font size.
	 */
	@SuppressWarnings("boxing")
	public final void appendToPane(final String text, final Color color, final String fontFamily, final int fontSize)
	{
		StyleContext sc = StyleContext.getDefaultStyleContext();
		AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, color);

		aset = sc.addAttribute(aset, StyleConstants.FontFamily, fontFamily);
		aset = sc.addAttribute(aset, StyleConstants.FontSize, fontSize);
		aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_LEFT);

		int len = io.getTextPane().getDocument().getLength();
		io.getTextPane().setCaretPosition(len);
		io.getTextPane().setCharacterAttributes(aset, false);
		io.getTextPane().replaceSelection(text);
	}
}
