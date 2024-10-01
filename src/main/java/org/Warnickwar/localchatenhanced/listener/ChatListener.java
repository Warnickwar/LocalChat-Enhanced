package org.Warnickwar.localchatenhanced.listener;

import com.mojang.logging.LogUtils;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.Warnickwar.localchatenhanced.LocalchatConfigs;
import org.Warnickwar.localchatenhanced.utils.IChatModifierObject;
import org.Warnickwar.localchatenhanced.utils.LocalMessageFormat;
import org.Warnickwar.localchatenhanced.utils.LocalPriorityComparator;
import org.slf4j.Logger;

import java.util.Objects;
import java.util.concurrent.PriorityBlockingQueue;

import static org.Warnickwar.localchatenhanced.Localchatenhanced.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class ChatListener {

    private static final Logger LOGGER = LogUtils.getLogger();

    private static PriorityBlockingQueue<ItemStack> getChatModifiers(Player player) {
        PriorityBlockingQueue<ItemStack> queue = new PriorityBlockingQueue<>(50, new LocalPriorityComparator());
        player.getInventory().items.forEach((itemStack) -> {
            if (itemStack.getItem() instanceof IChatModifierObject) {
                queue.add(itemStack);
            }
        });
        return queue;
    }

    // TODO: Hijack /msg and /tell to prevent their usage... somehow.
    //  That's going to be hard.

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onServerChatMessage(ServerChatEvent.Submitted event) {
        event.setCanceled(true); // Immediately cancel to prevent usual chat messages

        MinecraftServer server = event.getPlayer().getServer();
        LocalMessageFormat originalMessage = new LocalMessageFormat(event); // Converts to LocalMessageFormat

        // Used to log the message server-side. Administrative purposes.
        if (LocalchatConfigs.LogMessages.get()) { LOGGER.info("Local Message: <{}> {}",
                event.getPlayer().getDisplayName().getString(), event.getMessage().getString()); }

        PriorityBlockingQueue<ItemStack> sendQueue = getChatModifiers(event.getPlayer());
        originalMessage.setPrefix(LocalchatConfigs.LocalColor.get() + LocalchatConfigs.LocalPrefix.get() + "§r ");
        sendQueue.forEach(item -> ((IChatModifierObject) item.getItem()).onChatSend(originalMessage,item));

        event.getPlayer().sendSystemMessage(originalMessage.toComponent());
        // Handles every possible player recipient, with their corresponding Chat Modifiers
        for (Player player : Objects.requireNonNull(server).getPlayerList().getPlayers()) {
            // Stops re-sending message to origin player early
            if (player.is(event.getPlayer())) {  continue; }

            // Creates the queue for the recipients
            PriorityBlockingQueue<ItemStack> receiveQueue = getChatModifiers(player);
            LocalMessageFormat finalMessage = originalMessage.copy();
            // Executes the queue
            receiveQueue.forEach(item -> ((IChatModifierObject) item.getItem()).onChatReceive(finalMessage, item, player));

            // Handles extraneous sending (Locally + Globally)
            if (!finalMessage.isCancelled()) { player.sendSystemMessage(finalMessage.toComponent()); }
            if (finalMessage.isLocal() && finalMessage.getOrigin().distanceTo(player) < LocalchatConfigs.TextDistance.get()) {
                finalMessage.setPrefix(LocalchatConfigs.LocalColor.get() + LocalchatConfigs.LocalPrefix.get() + "§r ");
                player.sendSystemMessage(finalMessage.toComponent());
            }
        }
    }
}
