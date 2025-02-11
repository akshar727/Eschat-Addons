package com.example.eschatAddons;

import com.example.eschatAddons.config.ConfigHandler;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import com.example.eschatAddons.Commands.TestCommand;
import com.example.eschatAddons.Commands.NoDT;
import com.example.eschatAddons.features.NoDTListener;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = eschatAddons.MODID, version = eschatAddons.VERSION)
public class eschatAddons {
    public static final String MODID = "eschatAddons";
    public static final String VERSION = "0.1";

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new TestCommand());
        ClientCommandHandler.instance.registerCommand(new NoDT()); // This is fine for the command

        // Register NoDTListener (renamed from NoDT to avoid conflicts)
        MinecraftForge.EVENT_BUS.register(new NoDTListener());
    }
}
