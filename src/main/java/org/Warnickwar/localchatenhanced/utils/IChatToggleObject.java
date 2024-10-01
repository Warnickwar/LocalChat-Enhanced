package org.Warnickwar.localchatenhanced.utils;

import net.minecraft.world.item.ItemStack;

public interface IChatToggleObject extends IChatModifierObject {

    /**
     * A public function used by IChatToggleObjects to allow for easy state changes of many objects that should not be
     * active at the same time (e.g. Radios and Loudspeakers, two contradicting features)
     * @param item The itemstack to change
     * @param state The new active state
     */
    default void toggleState(ItemStack item, boolean state) { }
}
