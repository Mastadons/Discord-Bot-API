package me.myles.discordbotapi.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The EventHandler annotation.
 * 
 * <p>
 * Put this annotation above methods that will handle events.
 * </p>
 * 
 * <p>
 * Note: The class must implement
 * {@link me.myles.discordbotapi.event.Listener Listener} and must be
 * registered with the bot for the event to be handled.
 * </p>
 * 
 * @author Myles Deslippe
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {

}
