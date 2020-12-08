package me.myles.discordbotapi.bot;

import java.util.Arrays;

import me.myles.discordbotapi.event.EventHandler;
import me.myles.discordbotapi.event.Listener;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

/**
 * The Bot's internal command listener.
 * 
 * @author Myles Deslippe
 */
public class InternalCommandListener implements Listener {

	/**
	 * The Bot the internal command listener is bound to.
	 */
	private final Bot bot;

	/**
	 * Create a new internal command listener.
	 * 
	 * @param bot The Bot to bind the internal command listener to.
	 */
	protected InternalCommandListener(Bot bot) {
		this.bot = bot;
	}

	/**
	 * Listen for message events, if they match any of the registered commands,
	 * dispatch the command.
	 * 
	 * @param event The message event.
	 */
	@EventHandler
	public void onMessageEvent(MessageReceivedEvent event) {

		String[] rawArgs = event.getMessage().getContentRaw().split(" ");

		if (rawArgs[0].startsWith(bot.getCommandPrefix())) {

			String[] args = Arrays.copyOfRange(rawArgs, 1, rawArgs.length);

			bot.getCommandManager().dispatchCommand(rawArgs[0], event.getGuild(), event.getAuthor(), event.getChannel(),
					args);
		}
	}

}
