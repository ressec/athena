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
package com.heliosphere.athena.base.message.protocol;

import com.heliosphere.athena.base.message.internal.protocol.IMessageProtocol;
import com.heliosphere.athena.base.message.internal.protocol.IMessageProtocolDomain;
import com.heliosphere.athena.base.message.internal.protocol.IMessageProtocolGroup;
import com.heliosphere.athena.base.message.internal.protocol.MessageProtocolCategory;
import com.heliosphere.athena.base.message.internal.protocol.MessageProtocolNature;

public enum TestMessageProtocol implements IMessageProtocol
{
	/**
	 * Message protocol to request the creation of a lobby.
	 */
	LOBBY_CREATE(MessageProtocolNature.REQUEST, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to inform a lobby has been created or not.
	 */
	LOBBY_CREATED(MessageProtocolNature.REPLY, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to request the deletion of a lobby.
	 */
	LOBBY_DELETE(MessageProtocolNature.REQUEST, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to inform of the deletion of a lobby or not.
	 */
	LOBBY_DELETED(MessageProtocolNature.REPLY, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to request to join a lobby.
	 */
	LOBBY_JOIN(MessageProtocolNature.REQUEST, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to inform a user has joined a lobby or not.
	 */
	LOBBY_JOINED(MessageProtocolNature.REPLY, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to request to leave a lobby.
	 */
	LOBBY_LEAVE(MessageProtocolNature.REQUEST, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to inform a user has left a lobby or not.
	 */
	LOBBY_LEFT(MessageProtocolNature.REPLY, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to request the list of available lobbies.
	 */
	LOBBY_LIST(MessageProtocolNature.REQUEST, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to inform of the available lobbies or not.
	 */
	LOBBY_LISTED(MessageProtocolNature.REPLY, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to request the registration of a user.
	 */
	USER_REGISTER(MessageProtocolNature.REQUEST, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.USER),

	/**
	 * Message protocol to inform of the registration of a user or not.
	 */
	USER_REGISTERED(MessageProtocolNature.REPLY, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.USER),

	/**
	 * Message protocol to request a user un-registration.
	 */
	USER_UNREGISTER(MessageProtocolNature.REQUEST, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.USER),

	/**
	 * Message protocol to inform of the un-registration of a user or not.
	 */
	USER_UNREGISTERED(MessageProtocolNature.REPLY, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.USER),

	/**
	 * Message protocol to notify a user is saying something to other users in the same room.
	 */
	MESSAGE_SAY(MessageProtocolNature.NOTIFICATION, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.MESSAGE),

	/**
	 * Message protocol to notify a user said something to all users of the same room.
	 */
	MESSAGE_SAID(MessageProtocolNature.NOTIFICATION, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.MESSAGE),

	/**
	 * Message protocol to notify a user is whispering something to another player in the same room.
	 */
	MESSAGE_WHISPER(MessageProtocolNature.NOTIFICATION, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.MESSAGE),

	/**
	 * Message protocol to notify a user whispered something to another player in the same room.
	 */
	MESSAGE_WHISPERED(MessageProtocolNature.NOTIFICATION, MessageProtocolCategory.APPLICATION, TestMessageProtocolGroup.CHAT, TestMessageProtocolDomain.MESSAGE);

	/**
	 * Message protocol nature.
	 */
	private MessageProtocolNature nature;

	/**
	 * Message protocol category.
	 */
	private MessageProtocolCategory category;

	/**
	 * Message protocol group.
	 */
	private Enum<? extends IMessageProtocolGroup> group;

	/**
	 * Message protocol domain.
	 */
	private Enum<? extends IMessageProtocolDomain> domain;

	/**
	 * Creates a new test message protocol enumerated value based on given values.
	 * <hr>
	 * @param nature Message protocol nature.
	 * @param category Message protocol category.
	 * @param group Message protocol group.
	 * @param domain Message protocol domain.
	 */
	private TestMessageProtocol(final MessageProtocolNature nature, final MessageProtocolCategory category, final Enum<? extends IMessageProtocolGroup> group, final Enum<? extends IMessageProtocolDomain> domain)
	{
		this.nature = nature;
		this.category = category;
		this.group = group;
		this.domain = domain;
	}

	@Override
	public final MessageProtocolNature getNature()
	{
		return nature;
	}

	@Override
	public MessageProtocolCategory getCategory()
	{
		return category;
	}

	@Override
	public final Enum<? extends IMessageProtocolGroup> getGroup()
	{
		return group;
	}

	@Override
	public final Enum<? extends IMessageProtocolDomain> getDomain()
	{
		return domain;
	}

	@Override
	public Enum<? extends IMessageProtocol> fromString(String value)
	{
		// TODO Auto-generated method stub
		return null;
	}
}
