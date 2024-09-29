package org.Warnickwar.localchatenhanced;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import static org.Warnickwar.localchatenhanced.Localchatenhanced.MODID;

@Mod.EventBusSubscriber(modid = MODID)
public class LocalchatConfigs {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec Spec;
    public static final ForgeConfigSpec.ConfigValue<Boolean> OpAffected;
    public static final ForgeConfigSpec.ConfigValue<Boolean> LogMessages;
    public static final ForgeConfigSpec.ConfigValue<Double> TextDistance;
    public static final ForgeConfigSpec.ConfigValue<Boolean> YellingEnabled;
    public static final ForgeConfigSpec.ConfigValue<Double> YellingDistance;
    public static final ForgeConfigSpec.ConfigValue<Boolean> EchoedRadio;
    public static final ForgeConfigSpec.ConfigValue<Boolean> EchoedLoudspeaker;
    public static final ForgeConfigSpec.ConfigValue<String> LoudspeakerColor;
    public static final ForgeConfigSpec.ConfigValue<String> RadioColor;
    public static final ForgeConfigSpec.ConfigValue<String> LocalColor;
    public static final ForgeConfigSpec.ConfigValue<String> YellingColor;
    public static final ForgeConfigSpec.ConfigValue<String> OperatorColor;
    public static final ForgeConfigSpec.ConfigValue<String> LocalPrefix;
    public static final ForgeConfigSpec.ConfigValue<String> YellingPrefix;
    public static final ForgeConfigSpec.ConfigValue<String> OpPrefix;
    public static final ForgeConfigSpec.ConfigValue<String> RadioPrefix;
    public static final ForgeConfigSpec.ConfigValue<String> LoudspeakerPrefix;
    public static final ForgeConfigSpec.ConfigValue<Boolean> RadioOnlyActiveFreq;
    public static final ForgeConfigSpec.ConfigValue<Boolean> RadioOnlyActiveOffhand;
    public static final ForgeConfigSpec.ConfigValue<Integer> RadioMaxFreq;
    public static final ForgeConfigSpec.ConfigValue<Integer> RadioDefaultFreq;

    static {
        BUILDER.push("LocalChat Configurations");

        BUILDER.push("Functionality");

        OpAffected = BUILDER.comment("Are Operators affected by localized chat?")
                .define("Operators Affected?:", false);

        LogMessages = BUILDER.comment("\nShould all messages be logged into the console log?")
                .define("Log Messages?: ", true);

        TextDistance = BUILDER.comment("\nHow far Players' chat messages go by default")
                .defineInRange("Chat Distance:",32.0,10.0,128.0);

        YellingEnabled = BUILDER.comment("\nWill typing only in capital letters increase the range of messages?")
                .define("Is Yelling Enabled?:",true);

        YellingDistance = BUILDER.comment("\nHow much should messages be \"audible\" when yelling? (Must have yelling enabled!)")
                .defineInRange("Yelling Distance:",64.0, 10.0,256.0);

        EchoedRadio = BUILDER.comment("\nCan Radio messages also be heard locally around the player?",
                        "Set to false to prevent Radio messages from being sent in a local area")
                .define("Echo Radio messages: ", false);

        EchoedLoudspeaker = BUILDER.comment("\nCan Loudspeaker messages also be heard locally around the player?",
                        "Set to false to prevent Loudspeaker messages from being sent in a local area")
                .define("Echo Loudspeaker messages: ", false);

        RadioDefaultFreq = BUILDER.comment("\nWhat's the default frequency of new Radios?")
                .defineInRange("Radio - Default frequency: ", 1, 1, 10000);

        RadioMaxFreq = BUILDER.comment("\nWhat's the max number of frequencies for Radios?")
                .defineInRange("Radio - Maximum frequency available: ", 1000,1,10000);

        RadioOnlyActiveFreq = BUILDER.comment("\nCan messages through Radios only be heard by the Active frequency?")
                .define("Radio - Recieve only active frequency: ", false);

        RadioOnlyActiveOffhand = BUILDER.comment("\nCan messages only be sent through Radios when held?")
                .define("Radio - Send through currently held Radios: ", false);

        BUILDER.pop();
        BUILDER.push("Cosmetics");

        LocalColor = BUILDER.comment("What should the color of Local messages be? (Use MC's formatting codes!)")
                .comment("A list of codes can be found here: https://minecraft.wiki/w/Formatting_codes#Formatting_codes")
                .define("Local Color:","§b");

        YellingColor = BUILDER.comment("\nWhat should the color of Yell messages be? (Use MC's formatting codes!)")
                .comment("A list of codes can be found here: https://minecraft.wiki/w/Formatting_codes#Formatting_codes")
                .define("Yelling Color:","§c");

        OperatorColor = BUILDER.comment("\nWhat should the color of Operator messages be? (Use MC's formatting codes!)")
                .comment("A list of codes can be found here: https://minecraft.wiki/w/Formatting_codes#Formatting_codes")
                .define("Operator Color:","§6");

        RadioColor = BUILDER.comment("\nWhat should the color of Radio messages be? (Use MC's formatting codes!)")
                .comment("A list of codes can be found here: https://minecraft.wiki/w/Formatting_codes#Formatting_codes")
                .define("Radio Color:","§9");

        LoudspeakerColor = BUILDER.comment("\nWhat should the color of Loudspeaker messages be? (Use MC's formatting codes!)")
                .comment("A list of codes can be found here: https://minecraft.wiki/w/Formatting_codes#Formatting_codes")
                .define("Loudspeaker Color:", "§4");

        LocalPrefix = BUILDER.comment("\nThe prefix used for LOCAL messages")
                .define("Local Prefix:", "[LOCAL]");

        YellingPrefix = BUILDER.comment("\nThe prefix used for YELLING messages")
                .define("Yelling Prefix:", "[SHOUT]");

        OpPrefix = BUILDER.comment("\nThe prefix used for OPERATOR messages")
                .define("Operator Prefix:", "[OPERATOR]");

        RadioPrefix = BUILDER.comment("\nThe prefix used for RADIO messages")
                .define("Radio Prefix:", "[RADIO]");

        LoudspeakerPrefix = BUILDER.comment("\nThe prefix used for LOUDSPEAKER messages")
                .define("Loudspeaker Prefix:", "[GLOBAL]");

        BUILDER.pop();

        Spec=BUILDER.build();
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {
        LogUtils.getLogger().debug("Loaded LocalChat's config file {}", configEvent.getConfig().getFileName());
    }

    // TODO: Look later
    @SubscribeEvent
    public static void onChange(final ModConfigEvent.Reloading configEvent) {
        LogUtils.getLogger().debug("Reloaded Localchat's config file {} upon changes!", configEvent.getConfig().getFileName());
    }
}
