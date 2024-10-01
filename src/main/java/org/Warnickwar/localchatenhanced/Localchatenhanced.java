package org.Warnickwar.localchatenhanced;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.Warnickwar.localchatenhanced.items.ItemRegistry;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Localchatenhanced.MODID)
public class Localchatenhanced {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "localchatenhanced";

    public Localchatenhanced() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);

        //noinspection InstantiationOfUtilityClass
        new ItemRegistry(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, LocalchatConfigs.Spec, "LocalChat-common.toml");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }
}
