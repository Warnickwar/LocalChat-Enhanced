package org.Warnickwar.localchatenhanced.utils;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.ServerChatEvent;

import java.util.ArrayList;

public class LocalMessageFormat {

    /*
     * Yes, this is horrendous coding for a format.
     */

    private final Player origin;
    private Component prefix;
    private Component displayName;
    private Component suffix;
    private Component message;
    private final ArrayList<Object> tags = new ArrayList<>();
    private boolean isCancelled;
    private boolean isLocal;

    public LocalMessageFormat(ServerChatEvent event) {
        this.origin = event.getPlayer();
        this.prefix = Component.literal("");
        this.displayName = event.getPlayer().getDisplayName();
        this.suffix = Component.literal("");
        this.message = event.getMessage();
        this.isCancelled = event.isCanceled();
        this.isLocal = true;
    }

    // *technically* this can be optimized, but I'm lazy.
    // Used to generate a new copy of the LocalMessageFormat without the need of an event.
    // May use a tertiary method to manually create new LocalMessageFormats...
    public LocalMessageFormat(LocalMessageFormat copy) {
        this.origin = copy.getOrigin();
        this.prefix = copy.getPrefix();
        this.displayName = copy.getDisplayName();
        this.suffix = copy.getSuffix();
        this.message = copy.getMessage();
        this.isCancelled = copy.isCancelled();
        this.isLocal = true;
    }

    /**
     * Sets a custom prefix in server messages (before the display name)
     * @param prefix The prefix to append BEFORE the display name
     */
    public void setPrefix(Component prefix) {
        this.prefix = prefix;
    }

    /**
     * Sets a custom prefix in server messages (before the display name)
     * @param prefix The prefix to append BEFORE the display name
     */
    public void setPrefix(String prefix) {
        this.prefix = Component.literal(prefix);
    }

    /**
     * Sets a custom display name (instead of the origin's username) in server messages
     * @param displayName The name to append to the message
     */
    public void setDisplayName(Component displayName) {
        this.displayName = displayName;
    }

    /**
     * Sets a custom display name (instead of the origin's username) in server messages
     * @param displayName The name to append to the message
     */
    public void setDisplayName(String displayName) {
        this.displayName = Component.literal(displayName);
    }

    /**
     * Sets a custom suffix in server messages (after the display name, before message)
     * @param suffix The suffix to append AFTER the display name
     */
    public void setSuffix(Component suffix) {
        this.suffix = suffix;
    }

    /**
     * Sets a custom suffix in server messages (after the display name, before message)
     * @param suffix The suffix to append AFTER the display name
     */
    public void setSuffix(String suffix) {
        this.suffix = Component.literal(suffix);
    }

    /**
     * Sets a custom message to send instead of the original
     * @param message The message to be sent
     */
    public void setMessage(Component message) {
        this.message = message;
    }

    /**
     * Sets a custom message to send instead of the original
     * @param message The message to be sent
     */
    public void setMessage(String message) {
        this.message = Component.literal(message);
    }

    /**
     * Sets whether the message is cancelled or not
     * @param state The new state of whether the message reaches the user
     */
    public void setCancelled(boolean state) { this.isCancelled = state; }

    /**
     * Sets whether the message is heard locally
     * @param state The new state of whether the message is heard locally around the origin
     */
    public void setLocal(boolean state) { this.isLocal = state; }

    /**
     * Adds a tag to the list of tags
     * A method of communication between the origin player's ChatModifiers, and the recipient's ChatModifiers
     * @param tag The tag to append on the message
     */
    public void addTag(Object tag) {
        tags.add(tag);
    }

    /**
     * Removes a tag from the format's tags list
     * @param tag The tag to remove
     * @return The removed tag
     */
    public Object removeTag(Object tag) { return tags.remove(tag); }

    /**
     * Removes a tag from the format's tags list
     * @param index The index to remove
     * @return The removed tag
     */
    public Object removeTag(int index) { return tags.remove(index); }

    /**
     * Returns whether a message contains a tag
     * @param tag The tag to compare to
     * @return whether the tag is in the message
     */
    public boolean hasTag(Object tag) {
        return tags.contains(tag);
    }

    /**
     * Gets a tag from the message's tags list
     * @param tag The tag to get
     * @return The requested tag
     */
    public Object getTag(Object tag) {
        return tags.get(tags.indexOf(tag));
    }

    /**
     * Gets a tag from the message's tags list
     * @param index The index of the tag
     * @return The requested tag
     */
    public Object getTag(int index) {
        return tags.get(index);
    }

    /**
     * Returns a new instance of the entire tag list
     * @return the tag list
     */
    public ArrayList<Object> getTags() { return new ArrayList<>(tags); }

    /**
     * Returns the origin player from which the message was sent.
     * Immutable. The origin cannot be changed.
     * @return The player that sent the original message
     */
    public Player getOrigin() {
        return origin;
    }

    /**
     * Gets the message's prefix
     * @return The prefix of the message
     */
    public Component getPrefix() {
        return prefix;
    }

    /**
     * Gets the message's display name
     * @return The display name of the message
     */
    public Component getDisplayName() {
        return displayName;
    }

    /**
     * Gets the message's suffix
     * @return The suffix of the message
     */
    public Component getSuffix() {
        return suffix;
    }

    /**
     * Gets the message content
     * @return The message content
     */
    public Component getMessage() {
        return message;
    }

    /**
     * Whether the message will be received
     * @return If the message will be received by a recipient
     */
    public boolean isCancelled() { return this.isCancelled; }

    /**
     * Whether the message can be heard locally around the origin
     * @return If the message is locally heard
     */
    public boolean isLocal() { return this.isLocal; }

    /**
     * Used to make an identical duplicate of the message
     * @return The copied LocalMessageFormat
     */
    public LocalMessageFormat copy() {
        return new LocalMessageFormat(this);
    }

    /**
     * Used to change the object to a readable string
     * @return The built string
     */
    public String toString() {
        return Component.literal(this.prefix.getString()+"<"+this.displayName.getString()+"> ")
                .append(this.suffix)
                .append(this.message)
                .toString();
    }

    /**
     * Used to change the object to a text component
     * Used when sending the message to players
     * @return The built component
     */
    public Component toComponent() {
        return Component.literal(this.prefix.getString()+"§r<"+this.displayName.getString()+">§r ")
                .append(this.suffix)
                .append(this.message);
    }
}
