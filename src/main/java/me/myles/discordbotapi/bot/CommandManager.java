package me.myles.discordbotapi.bot;

import java.util.ArrayList;

import javax.annotation.Nonnull;

import me.myles.discordbotapi.command.Command;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

/**
 * This class is used to manage Bot's commands.
 * 
 * @author Myles Deslippe
 */
public class CommandManager {

	/**
	 * The registered commands.
	 */
	private final ArrayList<Command> commands = new ArrayList<Command>();

	/**
	 * The Bot the command manager is bound to.
	 */
	private final Bot bot;

	/**
	 * Create a new Command Manager.
	 * 
	 * @param bot The Bot to bind the command manager to.
	 */
	protected CommandManager(Bot bot) {
		this.bot = bot;
	}

	/**
	 * Register a command with the Bot.
	 * 
	 * @param command The command to register.
	 */
	public void registerCommand(Command command) {
		this.commands.add(command);
	}

	/**
	 * Unregister a command from the Bot.
	 * 
	 * @param command The command to unregister.
	 */
	public void unregisterCommand(Command command) {
		if (this.containsCommand(command))
			this.commands.remove(command);
	}

	/**
	 * Check if a command is registered with the bot.
	 * 
	 * @param command The command to check for.
	 * 
	 * @return If the command is registered with the bot.
	 */
	public boolean containsCommand(Command command) {
		return this.commands.contains(command);
	}

	/**
	 * Get a list of all the registered commands.
	 * 
	 * <p>
	 * Note: This is a clone of the original list, modifications made to the
	 * elements of the list will affect the origional elements.
	 * </p>
	 * 
	 * @return The list of registered commands.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Command> getCommands() {
		return (ArrayList<Command>) commands.clone();
	}

	/**
	 * Dispatch a command.
	 * 
	 * @param command  The command name.
	 * @param guild    The guild the command was executed in.
	 * @param executor The user that executed the command.
	 * @param channel  The channel the command was executed in.
	 * @param args     Arguments passed in with the command.
	 */
	public void dispatchCommand(String command, @Nonnull final Guild guild, @Nonnull final User executor,
			@Nonnull final MessageChannel channel, @Nonnull final String[] args) {

		for (Command index : commands) {

			// Check if the command being executed matches the index
			if (command.equals(bot.getCommandPrefix() + index.getName())) {
				index.onCommand(guild, executor, channel, args);
				continue;
			}

			// Check if the command being executed matches any of the index's aliases
			for (int i = 0; i < index.getAliases().length; i++) {
				if (command.equals(bot.getCommandPrefix() + index.getAliases()[i])) {
					index.onCommand(guild, executor, channel, args);
					break;
				}
			}

		}
	}

}
