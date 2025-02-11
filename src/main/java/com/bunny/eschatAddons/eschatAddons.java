package com.bunny.eschatAddons;

import com.bunny.eschatAddons.Commands.NoDT;
import com.bunny.eschatAddons.GUI.GuiEschat;
import com.bunny.eschatAddons.config.ConfigHandler;
import com.bunny.eschatAddons.features.NoDTListener;
import com.bunny.eschatAddons.HUD.NoDTHUD;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import org.lwjgl.input.Keyboard;

@Mod(modid = eschatAddons.MODID, version = eschatAddons.VERSION)
public class eschatAddons {
    public static final String MODID = "eschatAddons";
    public static final String VERSION = "0.1";

    @Mod.Instance
    public static eschatAddons instance; // Ensure instance is available

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        System.out.println("[eschatAddons] Pre-initialization started...");
        ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        System.out.println("[eschatAddons] Registering commands...");

        MinecraftForge.EVENT_BUS.register(new Object() {
            @SubscribeEvent
            public void onKeyInput(InputEvent.KeyInputEvent event) {
                if (Keyboard.isKeyDown(Keyboard.KEY_EQUALS)) { // Press 'G' to open GUI
                    Minecraft.getMinecraft().displayGuiScreen(new GuiEschat());
                    System.out.println("Keybind triggered: Opening GUI");
                }
            }
        });

        // Register NoDTListener (renamed from NoDT to avoid conflicts)
        MinecraftForge.EVENT_BUS.register(new NoDTListener());
        MinecraftForge.EVENT_BUS.register(new NoDTHUD());
    }
}