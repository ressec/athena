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
	MESSAGE_CONVERSATION_INITIATE(
			MessageProtocolNature.REQUEST,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.CONVERSATION),

	/**
	 * Message protocol to inform a client the conversation with the server has been initiated or not.
	 */
	MESSAGE_CONVERSATION_INITIATED(
			MessageProtocolNature.REPLY,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.CONVERSATION),

	/**
	 * Message protocol to request the creation of a lobby.
	 */
	MESSAGE_LOBBY_CREATE(
			MessageProtocolNature.REQUEST,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to inform a lobby has been created or not.
	 */
	MESSAGE_LOBBY_CREATED(
			MessageProtocolNature.REPLY,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to request the deletion of a lobby.
	 */
	MESSAGE_LOBBY_DELETE(
			MessageProtocolNature.REQUEST,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to inform of the deletion of a lobby or not.
	 */
	MESSAGE_LOBBY_DELETED(
			MessageProtocolNature.REPLY,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to request to join a lobby.
	 */
	MESSAGE_LOBBY_JOIN(
			MessageProtocolNature.REQUEST,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to inform a user has joined a lobby or not.
	 */
	MESSAGE_LOBBY_JOINED(
			MessageProtocolNature.REPLY,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to request to leave a lobby.
	 */
	MESSAGE_LOBBY_LEAVE(
			MessageProtocolNature.REQUEST,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to inform a user has left a lobby or not.
	 */
	MESSAGE_LOBBY_LEFT(
			MessageProtocolNature.REPLY,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to request the list of available lobbies.
	 */
	MESSAGE_LOBBY_LIST(
			MessageProtocolNature.REQUEST,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.LOBBY),

	/**
	 * Message protocol to inform of the available lobbies or not.
	 */
	MESSAGE_LOBBY_LISTED(
			MessageProtocolNature.REPLY,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.LOBBY),

	
	/**
	 * Message protocol to request the creation of a room.
	 */
	MESSAGE_ROOM_CREATE(
			MessageProtocolNature.REQUEST,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.ROOM),

	/**
	 * Message protocol to inform a room has been created or not.
	 */
	MESSAGE_ROOM_CREATED(
			MessageProtocolNature.REPLY,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.ROOM),

	/**
	 * Message protocol to request the deletion of a room.
	 */
	MESSAGE_ROOM_DELETE(
			MessageProtocolNature.REQUEST,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.ROOM),

	/**
	 * Message protocol to inform of the deletion of a room or not.
	 */
	MESSAGE_ROOM_DELETED(
			MessageProtocolNature.REPLY,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.ROOM),

	/**
	 * Message protocol to request to join a room.
	 */
	MESSAGE_ROOM_JOIN(
			MessageProtocolNature.REQUEST,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.ROOM),

	/**
	 * Message protocol to inform a user has joined a room or not.
	 */
	MESSAGE_ROOM_JOINED(
			MessageProtocolNature.REPLY,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.ROOM),

	/**
	 * Message protocol to request to leave a room.
	 */
	MESSAGE_ROOM_LEAVE(
			MessageProtocolNature.REQUEST,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.ROOM),

	/**
	 * Message protocol to inform a user has left a room or not.
	 */
	MESSAGE_ROOM_LEFT(
			MessageProtocolNature.REPLY,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.ROOM),

	/**
	 * Message protocol to request the list of available rooms.
	 */
	MESSAGE_ROOM_LIST(
			MessageProtocolNature.REQUEST,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.ROOM),

	/**
	 * Message protocol to inform of the available rooms or not.
	 */
	MESSAGE_ROOM_LISTED(
			MessageProtocolNature.REPLY,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.ROOM),
	
	/**
	 * Message protocol to request the registration of a user.
	 */
	MESSAGE_USER_REGISTER(
			MessageProtocolNature.REQUEST,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.USER),

	/**
	 * Message protocol to inform of the registration of a user or not.
	 */
	MESSAGE_USER_REGISTERED(
			MessageProtocolNature.REPLY,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.USER),

	/**
	 * Message protocol to request a user un-registration.
	 */
	MESSAGE_USER_UNREGISTER(
			MessageProtocolNature.REQUEST,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.USER),

	/**
	 * Message protocol to inform of the un-registration of a user or not.
	 */
	MESSAGE_USER_UNREGISTERED(
			MessageProtocolNature.REPLY,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.USER),

	/**
	 * Message protocol to request the list of available users.
	 */
	MESSAGE_USER_LIST(
			MessageProtocolNature.REQUEST,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.USER),

	/**
	 * Message protocol to inform of the available users or not.
	 */
	MESSAGE_USER_LISTED(
			MessageProtocolNature.REPLY,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.USER),

	/**
	 * Message protocol to notify a user is saying something to other users in the same room.
	 */
	MESSAGE_DISCUSSION_SAY(
			MessageProtocolNature.NOTIFICATION,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.DISCUSSION),

	/**
	 * Message protocol to notify a user said something to all users of the same room.
	 */
	MESSAGE_DISCUSSION_SAID(
			MessageProtocolNature.NOTIFICATION,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.DISCUSSION),

	/**
	 * Message protocol to notify a user is whispering something to another player in the same room.
	 */
	MESSAGE_DISCUSSION_WHISPER(
			MessageProtocolNature.NOTIFICATION,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.DISCUSSION),

	/**
	 * Message protocol to notify a user whispered something to another player in the same room.
	 */
	MESSAGE_DISCUSSION_WHISPERED(
			MessageProtocolNature.NOTIFICATION,
			MessageProtocolCategory.APPLICATION,
			TestMessageProtocolGroup.CHAT,
			TestMessageProtocolDomain.DISCUSSION);

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
