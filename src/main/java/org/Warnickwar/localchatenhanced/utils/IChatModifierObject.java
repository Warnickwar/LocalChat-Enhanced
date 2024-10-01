package org.Warnickwar.localchatenhanced.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

// TODO: Add Default JavaDocs
public interface IChatModifierObject {
    default ChatModifierObjectPriority getPriority() { return ChatModifierObjectPriority.DEFAULT; }

    // TODO: Make Javadocs for the default methods

    default LocalMessageFormat onChatSend(LocalMessageFormat message, ItemStack item) { return message; }
    default LocalMessageFormat onChatReceive(LocalMessageFormat message, ItemStack item, Player recipient) { return message; }
}
