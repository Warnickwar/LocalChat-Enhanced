package org.Warnickwar.localchatenhanced.items;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.jetbrains.annotations.NotNull;

public class LocalChatCreativeTab {
    public static final CreativeModeTab TAB = new CreativeModeTab("LocalChatEnhanced") {

        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Items.ACACIA_SIGN);
        }
    };
}
