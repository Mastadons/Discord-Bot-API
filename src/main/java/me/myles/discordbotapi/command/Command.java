package me.myles.discordbotapi.command;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;

/**
 * Extend this class to create Commands.
 * 
 * <p>
 * Before the command can be used it must be registered with the Bot.
 * </p>
 * 
 * @author Myles Deslippe
 */
public abstract class Command {

	/**
	 * The command's name.
	 */
	public final String name;

	/**
	 * The command's permission.
	 */
	public final Permission permission;

	/**
	 * The commands's aliases.
	 */
	public final String[] aliases;

	/**
	 * Create a new Command.
	 * 
	 * @param name       The command's name.
	 * @param permission The command's permission.
	 * @param aliases    The command's aliases.
	 */
	public Command(@Nonnull final String name, @Nullable Permission permission, @Nullable String[] aliases) {
		this.name = name;

		if (permission == null)
			this.permission = Permission.MESSAGE_WRITE;
		else
			this.permission = permission;

		if (aliases == null)
			this.aliases = new String[0];
		else
			this.aliases = aliases;
	}

	/**
	 * This method will be called when the command is executed.
	 * 
	 * @param guild    The guild the command was executed in.
	 * @param executor The user that executed the command.
	 * @param channel  The channel the command was executed in.
	 * @param args     The arugments passed in with the command.
	 */
	public abstract void onCommand(@Nonnull final Guild guild, @Nonnull final User executor,
			@Nonnull final MessageChannel channel, @Nonnull final String[] args);

	/**
	 * Get the command's name.
	 * 
	 * @return The command's name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Get the command's required permission.
	 * 
	 * @return The command's required permission.
	 */
	public Permission getPermission() {
		return this.permission;
	}

	/**
	 * Get the command's aliases.
	 * 
	 * @return The command's aliases.
	 */
	public String[] getAliases() {
		return this.aliases;
	}

}
