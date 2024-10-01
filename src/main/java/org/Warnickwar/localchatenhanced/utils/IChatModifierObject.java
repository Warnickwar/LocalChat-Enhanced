package org.Warnickwar.localchatenhanced.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

@SuppressWarnings("unused")
public interface IChatModifierObject {
    /**
     * Returns the priority of the object. Used in organizing the queue system.
     * It is useful for making certain objects happen before or after others.
     * @return The ChatModifierObjectPriority associated with the item
     */
    default ChatModifierObjectPriority getPriority() { return ChatModifierObjectPriority.DEFAULT; }

    // TODO: Make Javadocs for the default methods


    default LocalMessageFormat onChatSend(LocalMessageFormat message, ItemStack item) { return message; }
    default LocalMessageFormat onChatReceive(LocalMessageFormat message, ItemStack item, Player recipient) { return message; }
}
