package org.Warnickwar.localchatenhanced;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.Warnickwar.localchatenhanced.items.ModItemProperties;

@Mod.EventBusSubscriber(value= Dist.CLIENT, modid = Localchatenhanced.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientModEvents {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        new ModItemProperties();
    }
}
