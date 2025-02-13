package com.bunny.eschatAddons.GUI;

import com.bunny.eschatAddons.config.ConfigHandler;
import com.bunny.eschatAddons.features.RevTradeListener;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;


public class GuiRevTrader extends GuiScreen {
    private final GuiScreen parentScreen;
    private GuiButton RevTradingEnabled;

    public GuiRevTrader(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    @Override
    public void initGui() {
        buttonList.clear();

        // Toggle Buttons
        RevTradingEnabled = new GuiButton(0, width / 2 - 100, height / 2 - 25, 200, 20, getRevTradingEnabledText());
        buttonList.add(RevTradingEnabled);


        buttonList.add(new GuiButton(20, width / 2 - 100, height / 2 + 25, 200, 20, "Back"));

    }

    @Override
    protected void actionPerformed(GuiButton button){
        if (button.id == 0) {
            ConfigHandler.RevTradeListenerEnabled = !ConfigHandler.RevTradeListenerEnabled;
            RevTradingEnabled.displayString = getRevTradingEnabledText();
        } else if (button.id == 20) {
            mc.displayGuiScreen(parentScreen); // Return to the previous menu
        }
    }

    private String getRevTradingEnabledText(){
        return "Rev Trader Messages: " + (ConfigHandler.RevTradeListenerEnabled ? "Enabled" : "Disabled");
    }

    @Override
    public void onGuiClosed() {
        ConfigHandler.saveConfig();
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
