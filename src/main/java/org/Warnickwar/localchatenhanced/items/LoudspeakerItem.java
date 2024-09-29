package org.Warnickwar.localchatenhanced.items;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.Warnickwar.localchatenhanced.LocalchatConfigs;
import org.Warnickwar.localchatenhanced.utils.IChatToggleObject;
import org.Warnickwar.localchatenhanced.utils.LocalMessageFormat;
import org.jetbrains.annotations.NotNull;

public class LoudspeakerItem extends Item implements IChatToggleObject {

    public LoudspeakerItem(Properties p_41383_) {
        super(p_41383_);
    }

    public boolean isActive(ItemStack item) { return item.getOrCreateTag().getBoolean("active"); }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player player, @NotNull InteractionHand hand) {
        if (!isActive(player.getItemInHand(hand))) {
            NonNullList<ItemStack> items = player.getInventory().items;
            items.forEach((Itemstack) -> {
                if (Itemstack.getItem() instanceof IChatToggleObject) {
                    toggleState(Itemstack, false);
                }
            });
            toggleState(player.getItemInHand(hand), true);
        } else {
            toggleState(player.getItemInHand(hand), false);
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }
    // LocalChat functionality

    @Override
    public void toggleState(ItemStack item, boolean state) {
        CompoundTag tag = item.getOrCreateTag();
        if (tag.isEmpty()) { setDefaultNBTData(item); }
        tag.putBoolean("active", state);
    }

    @Override
    public LocalMessageFormat onChatSend(LocalMessageFormat message, ItemStack item) {
        if (isActive(item)) {
            message.setPrefix(LocalchatConfigs.LoudspeakerColor.get() + LocalchatConfigs.LoudspeakerPrefix.get() + " ");
            message.setCancelled(false);
            message.setLocal(LocalchatConfigs.EchoedLoudspeaker.get());
        }
        return message;
    }

    // Utils

    public void setDefaultNBTData(ItemStack item) {
        if (!item.getOrCreateTag().contains("active")) { item.getOrCreateTag().putBoolean("active", false); }
    }
}
