package org.Warnickwar.localchatenhanced.items;

import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import org.Warnickwar.localchatenhanced.LocalchatConfigs;
import org.Warnickwar.localchatenhanced.client.ClientHooks;
import org.Warnickwar.localchatenhanced.utils.IChatToggleObject;
import org.Warnickwar.localchatenhanced.utils.LocalMessageFormat;
import org.Warnickwar.localchatenhanced.utils.tags.RadioTag;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RadioItem extends Item implements IChatToggleObject {

    public RadioItem(Properties p_41383_) {
        super(p_41383_);
    }

    public void setFrequency(ItemStack item, int newFreq) {
        if (newFreq < 1) { newFreq = 1; }
        int freqMax = LocalchatConfigs.RadioMaxFreq.get();
        if (newFreq > freqMax) { newFreq = freqMax; }
        setDefaultNBTData(item);
        item.getOrCreateTag().putInt("frequency", newFreq);
    }

    public int getFrequency(ItemStack item) { return item.getOrCreateTag().getInt("frequency"); }

    public boolean isActive(ItemStack item) { return item.getOrCreateTag().getBoolean("active"); }

    /*
     TODO: Define custom item functionality
     TODO: Add Tooltip over Prefix set to the incoming frequency
    */

    @Override
    public boolean isFoil(@NotNull ItemStack p_41453_) {
        return isActive(p_41453_);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level world, @NotNull Player player, @NotNull InteractionHand hand) {
        if (world.isClientSide) { return InteractionResultHolder.pass(player.getItemInHand(hand)); }
        if (player.isShiftKeyDown()) {
            // change Freq
            DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> () -> ClientHooks.openRadioScreen(player.getItemInHand(hand)));
        } else {
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
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    // LocalChat Functionality

    @Override
    public void toggleState(ItemStack item, boolean state) {
        CompoundTag tag = item.getOrCreateTag();
        if (tag.isEmpty()) { setDefaultNBTData(item); }
        tag.putBoolean("active", state);
    }

    @Override
    public LocalMessageFormat onChatSend(LocalMessageFormat message, ItemStack item) {
        if (!isActive(item)) { return message; }
        message.addTag(new RadioTag(getFrequency(item)));
        message.setLocal(LocalchatConfigs.EchoedRadio.get());
        message.setCancelled(true);
        message.setPrefix(Component.literal(LocalchatConfigs.RadioColor.get()+LocalchatConfigs.RadioPrefix.get()+" "));
        return message;
    }

    @Override
    public LocalMessageFormat onChatReceive(LocalMessageFormat message, ItemStack item) {
        ArrayList<Object> tags = message.getTags();
        if (!tags.contains(RadioTag.class)) { return message; }
        RadioTag info = (RadioTag) tags.get(tags.indexOf(RadioTag.class));
        if (LocalchatConfigs.RadioOnlyActiveFreq.get()) {
            if (isActive(item) && info.frequency == getFrequency(item)) { message.setCancelled(false); }
        } else {
            if (info.frequency == getFrequency(item)) { message.setCancelled(false); }
        }
        return message;
    }

    // Utility/Extraneous

    @Override
    public void appendHoverText(@NotNull ItemStack itemstack, @Nullable Level world, List<Component> components, @NotNull TooltipFlag flag) {
        components.add(Component.literal("Frequency: " + ((getFrequency(itemstack) != 0) ? getFrequency(itemstack) : LocalchatConfigs.RadioDefaultFreq.get())));
        super.appendHoverText(itemstack,world,components,flag);
    }

    public void setDefaultNBTData(ItemStack item) {
        if (!item.getOrCreateTag().contains("frequency")) { item.getOrCreateTag().putInt("frequency", LocalchatConfigs.RadioDefaultFreq.get()); }
        if (!item.getOrCreateTag().contains("active")) { item.getOrCreateTag().putBoolean("active", false); }
    }
}