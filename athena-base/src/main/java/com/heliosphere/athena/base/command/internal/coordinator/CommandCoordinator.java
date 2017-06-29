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
package com.heliosphere.athena.base.command.internal.coordinator;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import com.heliosphere.athena.base.command.internal.ICommand;
import com.heliosphere.athena.base.command.internal.exception.CommandException;
import com.heliosphere.athena.base.command.internal.exception.CommandNotFoundException;
import com.heliosphere.athena.base.command.internal.interpreter.ICommandInterpreter;
import com.heliosphere.athena.base.command.internal.processor.ExecutableCommand;
import com.heliosphere.athena.base.command.internal.protocol.ICommandProtocolType;
import com.heliosphere.athena.base.terminal.CommandTerminal;

/**
 * An actor responsible to coordinate commands for a {@link ChatTerminal}.
 * <hr>
 * @author <a href="mailto:christophe.resse@gmail.com">Christophe Resse - Heliosphere</a>
 * @version 1.0.0
 */
public class CommandCoordinator implements ICommandCoordinator
{
	//	/**
	//	 * Akka logging adapter.
	//	 */
	//	private final LoggingAdapter LOG = Logging.getLogger(getContext().getSystem(), this);

	/**
	 * Pre-defined command processors.
	 */
	private Map<Enum<? extends ICommandProtocolType>, ExecutableCommand> executables = new HashMap<>();

	/**
	 * Command terminal.
	 */
	private CommandTerminal terminal;

	//	/**
	//	 * Static creation pattern for a {@link CommandCoordinatorActor}.
	//	 * <hr>
	//	 * @param terminal Command terminal.
	//	 * @return {@link Props}.
	//	 */
	//	public static Props create(final CommandTerminal terminal)
	//	{
	//		return Props.create(CommandCoordinatorActor.class, terminal);
	//	}

	/**
	 * Create a new command coordinator.
	 * <hr>
	 * @param terminal Command terminal.
	 */
	public CommandCoordinator(final CommandTerminal terminal)
	{
		this.terminal = terminal;
	}

	@Override
	public final ICommandInterpreter getCommandInterpreter()
	{
		return terminal.getInterpreter();
	}

	//	@SuppressWarnings("nls")
	//	@Override
	//	public void postStop() throws Exception
	//	{
	//		LOG.info(getSelf() + " is terminated!");
	//
	//		super.postStop();
	//	}

	@SuppressWarnings("nls")
	@Override
	public final void registerExecutable(final Enum<? extends ICommandProtocolType> type, final ExecutableCommand executable)
	{
		if (executables.get(type) != null)
		{
			terminal.appendToPane("A command definition is already registered for: ", Color.ORANGE);
			terminal.appendToPane(type + "\n", Color.ORANGE);
		}
		if (executable == null)
		{
			terminal.appendToPane("Executable command cannot be null!\n", Color.ORANGE);
		}

		executables.put(type, executable);
	}

	//	@Override
	//	public Receive createReceive()
	//	{
	//		return receiveBuilder()
	//				.match(ICommand.class, command -> handleAndDispatchCommand(command))
	//				.match(CommandCoordinatorProtocol.RegisterCommandProcessor.class, message -> handleRegisterCommandProcessor(message))
	//				.match(CommandCoordinatorProtocol.ExecuteCommand.class, command -> handleExecuteCommand(command))
	//				//.matchEquals("stopIt", p -> handleStop())
	//				//				.matchAny(command -> handleUnknownCommand(command))
	//				.matchAny(o -> LOG.info("received unknown object!"))
	//				.build();
	//	}

	/**
	 * Handles unknown command.
	 * <hr>
	 * @param command Command received.
	 */
	@SuppressWarnings("nls")
	private final void handleUnknownCommand(Object command)
	{
		terminal.appendToPane("Received an unknown command: " + command, Color.YELLOW);
	}

	//	/**
	//	 * Executes a command.
	//	 * <hr>
	//	 * @param command Command to execute.
	//	 */
	//	@SuppressWarnings("nls")
	//	protected void executeCommand(final ICommand command)
	//	{
	//		// Check the command type.
	//		if (command.getMetadata().getProtocolCategory() == DefaultCommandCategoryType.NORMAL)
	//		{
	//			try
	//			{
	//				executeNormalCommand(command);
	//			}
	//			catch (CommandException exception)
	//			{
	//				terminal.appendToPane(String.format("[ERROR] %1s\n", exception.getMessage()), Color.ORANGE);
	//			}
	//		}
	//		else
	//		{
	//			terminal.appendToPane("Cannot process command with category: ", Color.YELLOW);
	//			terminal.appendToPane(command.getMetadata().getProtocolCategory() + " ", Color.ORANGE);
	//			terminal.appendToPane(", expected a 'normal' command category type!", Color.YELLOW);
	//		}
	//	}

	//	/**
	//	 * Converts a {@link ICommand} into an {@link IMessage}.
	//	 * <hr>
	//	 * @param command Command to convert.
	//	 * @return Message.
	//	 * @throws CommandException Thrown in case an error occurred while converting the command.
	//	 */
	//	@SuppressWarnings("nls")
	//	private final IMessage convert(final ICommand command) throws CommandException
	//	{
	//		IMessage message = null;
	//		//		ChatMessageData data = new ChatMessageData();
	//		//
	//		//		switch ((DefaultCommandCategoryType) command.getMetadata().getProtocolCategory())
	//		//		{
	//		//			case NORMAL:
	//		//				switch ((DefaultCommandCodeType) command.getMetadata().getProtocolGroup())
	//		//				{
	//		//					case REGISTER_USER:
	//		//						data.setUserName((String) command.getParameter("username").getValues().get(0));
	//		//						message = Message.createRequest(command.getMetadata().getMessageType(), data);
	//		//						break;
	//		//
	//		//					case QUIT:
	//		//					case AFK:
	//		//					case DISPLAY_TERMINAL:
	//		//					case WHO:
	//		//
	//		//					default:
	//		//						break;
	//		//				}
	//		//				break;
	//		//
	//		//			case ADMINISTRATION:
	//		//			case DEBUG:
	//		//			case SUPER_ADMINISTRATION:
	//		//			case SYSTEM:
	//		//
	//		//			default:
	//		//				break;
	//		//		}
	//
	//		return message;
	//	}

	//	/**
	//	 * Execute {@code normal} commands.
	//	 * <hr>
	//	 * @param command Command to process.
	//	 * @throws CommandException Thrown in case an error occurred while converting the command.
	//	 */
	//	@SuppressWarnings("nls")
	//	protected void executeNormalCommand(final ICommand command) throws CommandException
	//	{
	//		switch ((DefaultCommandGroupType) command.getMetadata().getProtocolGroup())
	//		{
	//			case CHAT:
	//				handleCommandChat(command);
	//				break;
	//
	//			case SYSTEM:
	//				handleCommandSystem(command);
	//				break;
	//
	//			default:
	//				terminal.appendToPane("Unknown command type: " + command.getMetadata().getProtocolCategory(), Color.YELLOW);
	//				break;
	//		}
	//	}

	//	/**
	//	 * Handles chat commands.
	//	 * <hr>
	//	 * @param command Command to process.
	//	 * @throws CommandException Thrown in case an error occurred while converting the command.
	//	 */
	//	protected void handleCommandChat(final @NonNull ICommand command) throws CommandException
	//	{
	//		//		switch ((DefaultCommandCodeType) command.getMetadata().getCodeType())
	//		//		{
	//		//			case REGISTER_USER:
	//		//				getSender().tell(convert(command), getSelf());
	//		//				break;
	//		//
	//		//			case QUIT:
	//		//			case AFK:
	//		//			case DISPLAY_TERMINAL:
	//		//			case WHO:
	//		//
	//		//			default:
	//		//				break;
	//		//		}
	//	}

	//	/**
	//	 * Handles system commands.
	//	 * <hr>
	//	 * @param command Command to process.
	//	 * @throws CommandException Thrown in case an error occurred while converting the command.
	//	 */
	//	protected void handleCommandSystem(final @NonNull ICommand command) throws CommandException
	//	{
	//		//		switch ((DefaultCommandCodeType) command.getMetadata().getCodeType())
	//		//		{
	//		//			case HELP:
	//		//				executeCommandProcessor(command);
	//		//				break;
	//		//
	//		//			case QUIT:
	//		//			case AFK:
	//		//			case DISPLAY_TERMINAL:
	//		//			case WHO:
	//		//
	//		//			default:
	//		//				break;
	//		//		}
	//	}

	//	@SuppressWarnings("unchecked")
	//	protected void executeCommandProcessor(final ICommand command) throws CommandException
	//	{
	//		ICommandProcessor processor = processors.get(command.getMetadata().getProtocolGroup());
	//		if (processor != null)
	//		{
	//			List<String> result = (List<String>) processor.execute(command);
	//			result.add("\r\n");
	//
	//			// Send lines to the terminal.
	//			getContext().getParent().tell(new TerminalProtocol.DisplayOnTerminal(result), getSelf());
	//		}
	//	}

	//	/**
	//	 * Handles system commands.
	//	 * <hr>
	//	 * @param command Command to process.
	//	 */
	//	@SuppressWarnings("nls")
	//	protected void handleCommandSystem(final @NonNull ICommand command)
	//	{
	//		ICommandResponse response = null;
	//
	//		if (command.getMetadata().getGroupType() == DefaultCommandGroupType.SYSTEM)
	//		{
	//			switch ((DefaultCommandCodeType) command.getMetadata().getCodeType())
	//			{
	//				case QUIT:
	//					response = new CommandResponse(command, CommandStatusType.PROCESSED);
	//
	//					// TODO Do necessary cleanup before shutting down the whole system.
	//
	//					response.setOrder(DefaultCommandCodeType.QUIT);
	//					break;
	//
	//				default:
	//					break;
	//			}
	//		}
	//		else
	//		{
	//			response = new CommandResponse(command, CommandStatusType.FAILED);
	//			response.addException(new CommandException("Command is not a normal system command!"));
	//		}
	//
	//		if (response != null)
	//		{
	//			getSender().tell(response, getSelf());
	//		}
	//	}

	//	/**
	//	 * Handles debug commands.
	//	 * <hr>
	//	 * @param command Command to process.
	//	 */
	//	protected void handleCommandDebug(final @NonNull ICommand command)
	//	{
	//		// Empty, must be overridden by subclasses.
	//	}
	//
	//	/**
	//	 * Handles super administration commands.
	//	 * <hr>
	//	 * @param command Command to process.
	//	 */
	//	protected void handleCommandSuperAdministration(final @NonNull ICommand command)
	//	{
	//		// Empty, must be overridden by subclasses.
	//	}
	//
	//	/**
	//	 * Handles system commands.
	//	 * <hr>
	//	 * @param command Command to process.
	//	 */
	//	protected void handleCommandSystem(final @NonNull ICommand command)
	//	{
	//		// Empty, must be overridden by subclasses.
	//	}
	//
	//	/**
	//	 * Handles a command response.
	//	 * <hr>
	//	 * @param response Command response to handle.
	//	 */
	//	protected void handleResponse(final @NonNull ICommandResponse response)
	//	{
	//		// TODO Implement the method's core.
	//	}
	//
	//	/**
	//	 * Handles unknown commands.
	//	 * <hr>
	//	 * @param command Command to process.
	//	 */
	//	protected void handleCommandUnknown(final @NonNull ICommand command)
	//	{
	//		// Empty, must be overridden by subclasses.
	//	}

	@Override
	@SuppressWarnings("nls")
	public final void execute(final ICommand command) throws CommandNotFoundException
	{
		// Do we have a command processor for this command type?
		ExecutableCommand processor = executables.get(command.getMetadata().getProtocolType());
		if (processor != null)
		{
			try
			{
				// Execute the command through the command processor.
				processor.execute(terminal, command);
			}
			catch (CommandException e)
			{
				terminal.appendToPane(String.format("[ERROR] %1s\n", e.getMessage()), Color.ORANGE);
			}
		}
	}

	//	/**
	//	 * Handles message for registering a command processor.
	//	 * <hr>
	//	 * @param message Message to process.
	//	 */
	//	protected void handleRegisterCommandProcessor(final @NonNull CommandCoordinatorProtocol.RegisterCommandProcessor message)
	//	{
	//		Constructor<?> ctor;
	//
	//		if (executables.get(message.getType()) != null)
	//		{
	//			// TODO throw exception this command type is already handled!
	//		}
	//
	//		// Create an instance of the processor class.
	//		try
	//		{
	//			ctor = message.getCommandProcessorClass().getConstructor(Enum.class, List.class);
	//			ExecutableCommand processor = (ExecutableCommand) ctor.newInstance(new Object[] { message.getType(), terminal.getInterpreter().getCommandDefinitions() });
	//			executables.put(message.getType(), processor);
	//		}
	//		catch (NoSuchMethodException e)
	//		{
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		catch (SecurityException e)
	//		{
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		catch (InstantiationException e)
	//		{
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		catch (IllegalAccessException e)
	//		{
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		catch (IllegalArgumentException e)
	//		{
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//		catch (InvocationTargetException e)
	//		{
	//			// TODO Auto-generated catch block
	//			e.printStackTrace();
	//		}
	//	}
}