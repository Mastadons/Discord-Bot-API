package me.myles.discordbotapi.bot;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.annotation.Nonnull;

import me.myles.discordbotapi.event.EventHandler;
import me.myles.discordbotapi.event.Listener;
import me.myles.discordbotapi.exception.InvalidEventHandlerException;
import net.dv8tion.jda.api.events.GenericEvent;

/**
 * This class is used to manage the Bot's events.
 * 
 * @author Myles Deslippe
 */
public class EventManager {

	/**
	 * The list of registered listeners.
	 */
	private final ArrayList<Listener> listeners;

	/**
	 * Create a new Event Manager.
	 */
	protected EventManager() {
		listeners = new ArrayList<Listener>();
	}

	/**
	 * Register a listener with the Bot.
	 * 
	 * @param listener The listener to register.
	 */
	public void registerListener(@Nonnull final Listener listener) {
		this.listeners.add(listener);
	}

	/**
	 * Unregister a listener with the Bot.
	 * 
	 * @param listener The listener to unregister.
	 */
	public void unregisterListener(@Nonnull final Listener listener) {
		if (this.containsListener(listener))
			this.listeners.remove(listener);
	}

	/**
	 * Check if a listener is registered with the Bot.
	 * 
	 * @param listener The listener to check.
	 * 
	 * @return If the listener is registered with the Bot.
	 */
	public boolean containsListener(@Nonnull final Listener listener) {
		return this.listeners.contains(listener);
	}

	/**
	 * Get a list of all the registered listeners.
	 * 
	 * <p>
	 * Note: This is a clone of the original list, modifications made to the
	 * elements of the list will affect the origional elements.
	 * </p>
	 * 
	 * @return The list of registered listeners.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Listener> getListeners() {
		return (ArrayList<Listener>) this.listeners.clone();
	}

	/**
	 * Dispatch an event.
	 * 
	 * @param event The event to dispatch.
	 */
	public void dispatchEvent(@Nonnull final GenericEvent event) {
		for (Listener index : listeners)
			this.executeEvent(index.getClass(), index, event);
	}

	/**
	 * Execute an event.
	 * 
	 * @param clazz    The listener class.
	 * @param executor The class executing the event.
	 * @param args     The event arguments.
	 */
	private void executeEvent(@Nonnull final Class<?> clazz, @Nonnull final Object executor,
			@Nonnull final Object... args) {

		for (Method index : clazz.getDeclaredMethods()) {

			if (index.getDeclaredAnnotation(EventHandler.class) != null) {

				if (index.getParameterCount() == 0)
					throw new InvalidEventHandlerException("Error: No paramater was specified!");

				String paramaterType = index.getParameterTypes()[0].getTypeName();
				String argumentType = args[0].getClass().getTypeName();
				String genericType = GenericEvent.class.getTypeName();

				if (paramaterType.equals(argumentType) || paramaterType.equals(genericType)) {

					try {
						index.invoke(executor, args);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
