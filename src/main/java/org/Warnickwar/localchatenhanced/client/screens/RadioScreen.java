package org.Warnickwar.localchatenhanced.client.screens;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.Warnickwar.localchatenhanced.Localchatenhanced;
import org.Warnickwar.localchatenhanced.items.RadioItem;
import org.slf4j.Logger;

public class RadioScreen extends Screen {

    private static final Component TITLE = Component.translatable("gui." + Localchatenhanced.MODID + ".radio_screen");
    private static final Logger LOGGER = LogUtils.getLogger();

    private final ItemStack item;
    private final int imageWidth,imageHeight;
    private int leftPos,topPos;

    private EditBox nameInput;

    public RadioScreen(ItemStack item) {
        super(TITLE);

        this.item = item;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();

        if (this.minecraft == null) { return; }
        Level level = this.minecraft.level;
        if (level == null) { return; }

        if (!(this.item.getItem() instanceof RadioItem)) {
            LOGGER.error("Item {} is not of type RadioItem!", item.getItem());
            return;
        }

        // TODO: I hate GUIs.
        //this.nameInput = addRenderableWidget();
    }
}