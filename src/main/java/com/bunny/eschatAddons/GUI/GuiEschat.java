package com.bunny.eschatAddons.GUI;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiEschat extends GuiScreen {

    @Override
    public void initGui() {
        buttonList.clear();

        // Button to open NoDT Settings
        buttonList.add(new GuiButton(10, width / 2 - 75, height / 2 - 25, 150, 20, "NoDT Settings"));
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button.id == 10) {
            mc.displayGuiScreen(new GuiNoDTSettings(this)); // Open NoDT Settings Page
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
