package org.Warnickwar.localchatenhanced.items;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import org.Warnickwar.localchatenhanced.Localchatenhanced;

public class ModItemProperties {
    public ModItemProperties() {
        radioProps(ItemRegistry.RADIO.get());
    }

    private static void radioProps(Item item) {
        ItemProperties.register(item, new ResourceLocation(Localchatenhanced.MODID, "active"), (itemStack, clientLevel, entity, id) -> {
            if (itemStack.getItem() instanceof RadioItem && ((RadioItem) itemStack.getItem()).isActive(itemStack)) {
                return 1.0f;
            } else {
                return 0.0f;
            }
        });
    }

}
