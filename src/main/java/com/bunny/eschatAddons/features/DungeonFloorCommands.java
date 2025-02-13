package com.bunny.eschatAddons.features;

import com.bunny.eschatAddons.config.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.regex.Pattern;

public class DungeonFloorCommands {
    String[] floors = {"one", "two", "three", "four", "five", "six", "seven"};

    public DungeonFloorCommands() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        if (!ConfigHandler.DungeonFloorCommandsEnabled) {
            return;
        }
        if (event.isCanceled()) return;
        String unFormattedMessage = event.message.getUnformattedText();
//        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(unFormattedMessage));
        boolean command = Pattern.compile("§9Party.*?: ![fFmM][1234567]?").matcher(unFormattedMessage).find();
        if (command) {
            String[] parts = unFormattedMessage.split("!", 2);
            // if parts[1] is more than three characters, ignore
            if (parts[1].length() > 2) return;
            try {
                boolean isMasterMode = (Character.toLowerCase(parts[1].charAt(0)) == 'm');
                int floor = parts[1].charAt(1) - '0';
                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Would execute /joindungeon " +
                        ((isMasterMode) ? "master_" : "") + "catacombs_floor_" + floors[floor - 1]));
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/joindungeon " +
                        ((isMasterMode) ? "master_" : "") + "catacombs_floor_" + floors[floor - 1]);
            } catch (Exception ignored) {}
        }
    }
}
