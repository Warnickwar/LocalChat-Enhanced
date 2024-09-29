package org.Warnickwar.localchatenhanced.utils;

import net.minecraft.world.item.ItemStack;

public interface IChatToggleObject extends IChatModifierObject {

    default public void toggleState(ItemStack item, boolean state) { }
}
