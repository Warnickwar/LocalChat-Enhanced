package org.Warnickwar.localchatenhanced.client;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.ItemStack;
import org.Warnickwar.localchatenhanced.client.screens.RadioScreen;

public class ClientHooks {

    public static void openRadioScreen(ItemStack item) {
        Minecraft.getInstance().setScreen(new RadioScreen(item));
    }
}
