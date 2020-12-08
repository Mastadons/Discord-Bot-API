package me.myles.discordbotapi.bot;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.hooks.EventListener;

/**
 * The Bot's internal event listener.
 * 
 * @author Myles Deslippe
 */
public class InternalEventListener implements EventListener {

	/**
	 * The Bot the internal event listener is bound to.
	 */
	private final Bot bot;

	/**
	 * Create a new Internal Event Listener.
	 * 
	 * @param bot The Bot to bind the internal event listener to.
	 */
	protected InternalEventListener(Bot bot) {
		this.bot = bot;
	}

	/**
	 * Dispatch inbound events.
	 */
	public void onEvent(GenericEvent event) {
		bot.getEventManager().dispatchEvent(event);
	}

}
