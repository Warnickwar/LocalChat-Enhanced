package org.Warnickwar.localchatenhanced.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.Warnickwar.localchatenhanced.utils.IChatModifierObject;
import org.Warnickwar.localchatenhanced.utils.LocalMessageFormat;
import org.jetbrains.annotations.NotNull;

public class ProxyItem extends Item implements IChatModifierObject {

    public ProxyItem(Properties p_41383_) {
        super(p_41383_);
    }

    public boolean isActive(ItemStack item) {
        return item.getOrCreateTag().getBoolean("active");
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, Player player, @NotNull InteractionHand hand) {
        CompoundTag tag = player.getItemInHand(hand).getOrCreateTag();
        if (tag.isEmpty()) { setDefaultNBTData(player.getItemInHand(hand)); }
        if (!tag.getBoolean("active")) {
            for (ItemStack item : player.getInventory().items) {
                if (item.getItem() instanceof ProxyItem) {
                    if (item.getOrCreateTag().isEmpty()) { setDefaultNBTData(item); }
                    assert item.getTag() != null;
                    item.getTag().putBoolean("active", false);
                }
            }
        }
        tag.putBoolean("active", !tag.getBoolean("active"));
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    // Localchat Functionality

    @Override
    public LocalMessageFormat onChatSend(LocalMessageFormat message, ItemStack item) {
        if (item.getOrCreateTag().isEmpty()) { setDefaultNBTData(item); }
        if (isActive(item)) {
            message.setDisplayName(item.getOrCreateTag().getString("fakename"));
        }
        return message;
    }

    // Utils

    public static void setDefaultNBTData(ItemStack item) {
        if (!item.getOrCreateTag().contains("active")) { item.getOrCreateTag().putBoolean("active", false); }
        if (!item.getOrCreateTag().contains("fakename")) { item.getOrCreateTag().putString("fakename", "FakeUsername!"); }
    }
}
