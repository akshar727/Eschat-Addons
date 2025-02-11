package com.example.eschatAddons.config;

import net.minecraftforge.common.config.Configuration;
import java.io.File;

public class ConfigHandler {

    private static Configuration config;

    // No Downtime Config
    public static boolean NoDTEnabled;
    public static int NoDTDelay;

    public static void loadConfig(File file) {
        config = new Configuration(file);

        try {
            config.load();
        } catch (Exception e) {
            System.err.println("Failed to load configuration file: " + file.getName());
            e.printStackTrace();
        }

        NoDTEnabled = config.get("NoDT", "Enabled", false, "Is No Downtime Enabled?").getBoolean();
        NoDTDelay = config.get("NoDT", "Delay", 0, "How long is the delay before entering a new run?").getInt();
    }

    public static void saveConfig() {
        if (config == null) {
            System.err.println("Config not initialized. Call loadConfig() first.");
            return;
        }

        config.get("NoDT", "Enabled", NoDTEnabled, "Is No Downtime Enabled?").set(NoDTEnabled);
        config.get("NoDT", "Delay", NoDTDelay, "How long is the delay before entering a new run?").set(NoDTDelay);

        if (config.hasChanged()) {
            config.save();
        }
    }
}
