package com.bunny.eschatAddons.features;

import com.bunny.eschatAddons.config.ConfigHandler;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class NoDTListener {

    private long executeAtTime = 0; // Time when the command should be executed
    private boolean commandQueued = false; // Flag to track if a command is scheduled

    public NoDTListener() {
        MinecraftForge.EVENT_BUS.register(this);
        FMLCommonHandler.instance().bus().register(this); // Register tick event
    }

    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        if (!ConfigHandler.NoDTEnabled) return;
        if (event.isCanceled()) return;

        // Remove Minecraft color codes and hidden characters
        String formattedMessage = event.message.getFormattedText()
                .replaceAll("§.", "") // Removes all Minecraft color codes
                .replaceAll("[^\\x20-\\x7E]", "") // Removes non-ASCII characters
                .trim()
                .replaceAll("\\s+", " "); // Normalize spaces

        if (formattedMessage.contains("rf 6> elEXTRA STATS 6<r")) {
            System.out.println("Detected EXTRA STATS message! Command will be sent after delay...");
            executeAtTime = System.currentTimeMillis() + ConfigHandler.NoDTDelay;
            commandQueued = true; // Mark command for future execution
        }
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (!commandQueued) return;

        if (System.currentTimeMillis() >= executeAtTime) {
            if (Minecraft.getMinecraft().thePlayer != null) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage("/instancerequeue");
                System.out.println("Sending /instancerequeue after delay!");
            }
            commandQueued = false; // Reset flag after execution
        }
    }
}
