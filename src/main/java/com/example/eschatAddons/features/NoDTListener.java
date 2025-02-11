package com.example.eschatAddons.features;

import com.example.eschatAddons.config.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;

public class NoDTListener {

    public NoDTListener() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        if (!ConfigHandler.NoDTEnabled) return;
        if (event.isCanceled()) {
            System.out.println("Chat event was canceled, skipping...");
            return;
        }

        // Remove Minecraft color codes and hidden characters
        String formattedMessage = event.message.getFormattedText()
                .replaceAll("§.", "") // Removes all Minecraft color codes
                .replaceAll("[^\\x20-\\x7E]", "") // Removes non-ASCII characters
                .trim()
                .replaceAll("\\s+", " "); // Normalize spaces

        // Check if the message contains the desired text
        if (formattedMessage.contains("rf 6> elEXTRA STATS 6<r")) {
            System.out.println("Detected EXTRA STATS message! Sending commands...");
            if (Minecraft.getMinecraft().thePlayer != null) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/instancerequeue");
            }
        }
    }
}
