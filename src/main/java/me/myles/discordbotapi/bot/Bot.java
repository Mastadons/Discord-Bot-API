package me.myles.discordbotapi.bot;

import javax.annotation.Nonnull;
import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

/**
 * This class is used to define and manage discord bots.
 * 
 * @author Myles Deslippe
 */
public class Bot {

	/**
	 * The Bot's Discord API instance.
	 */
	private JDA discordAPI;

	/**
	 * The Bot's token.
	 */
	private final String token;

	/**
	 * The Bot's command prefix.
	 */
	private String prefix;

	/**
	 * The Bot's internal event listener.
	 */
	private final InternalEventListener internalEventListener;

	/**
	 * The Bot's internal command listener.
	 */
	private final InternalCommandListener internalCommandListener;

	/**
	 * The Bot's event manager.
	 */
	private final EventManager eventManager;

	/**
	 * The Bot's command manager.
	 */
	private final CommandManager commandManager;

	/**
	 * Create a new Bot.
	 * 
	 * <p>
	 * This constructor will only instantiate the bot, to start the bot you must call
	 * {@link #start() start()}
	 * </p>
	 * 
	 * @param token  The Bot's private token.
	 * @param prefix The Bot's command prefix.
	 */
	public Bot(@Nonnull final String token, @Nonnull final String prefix) {
		this.token = token;
		this.prefix = prefix;
		this.internalEventListener = new InternalEventListener(this);
		this.internalCommandListener = new InternalCommandListener(this);
		this.eventManager = new EventManager();
		this.commandManager = new CommandManager(this);
	}

	/**
	 * Start the Bot.
	 * 
	 * @throws LoginException If there was an issue logging into the bot.
	 */
	public void start() throws LoginException {

		JDABuilder builder = JDABuilder.createDefault(token);
		builder.addEventListeners(internalEventListener);
		this.getEventManager().registerListener(internalCommandListener);
		this.discordAPI = builder.build();

	}

	/**
	 * Stop the Bot.
	 */
	public void stop() {
		this.getDiscordAPI().shutdown();
	}

	/**
	 * Get the Bot's event manager.
	 * 
	 * @return The Bot's event manager.
	 */
	public EventManager getEventManager() {
		return this.eventManager;
	}

	/**
	 * Get the Bot's command manager.
	 * 
	 * @return The Bot's command manager.
	 */
	public CommandManager getCommandManager() {
		return this.commandManager;
	}

	/**
	 * Get the Bot's Discord API instance.
	 * 
	 * @return The Bot's Discord API instance.
	 */
	public JDA getDiscordAPI() {
		return this.discordAPI;
	}

	/**
	 * Get the Bot's command prefix.
	 * 
	 * @return The Bot's command prefix.
	 */
	public String getCommandPrefix() {
		return this.prefix;
	}

	/**
	 * Change the Bot's command prefix.
	 * 
	 * @param prefix The new command prefix.
	 */
	public void setCommandPrefix(String prefix) {
		this.prefix = prefix;
	}

}
