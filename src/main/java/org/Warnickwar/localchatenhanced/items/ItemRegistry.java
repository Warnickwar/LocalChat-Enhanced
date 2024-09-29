package org.Warnickwar.localchatenhanced.items;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.Warnickwar.localchatenhanced.Localchatenhanced;

public class ItemRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Localchatenhanced.MODID); // ItemRegistry

    public static final RegistryObject<Item> RADIO = ITEMS.register("radio", () -> new RadioItem(setCreativeTab().stacksTo(1).setNoRepair()));
    public static final RegistryObject<Item> LOUDSPEAKER = ITEMS.register("loudspeaker", () -> new LoudspeakerItem(setCreativeTab().stacksTo(1).setNoRepair()));
    public static final RegistryObject<Item> PROXY = ITEMS.register("proxy", () -> new ProxyItem(setCreativeTab().stacksTo(1).setNoRepair()));

    public ItemRegistry(IEventBus eventBus) { ITEMS.register(eventBus); }

    private static Item.Properties setCreativeTab() {
        return new Item.Properties().tab(LocalChatCreativeTab.TAB);
    }
}
