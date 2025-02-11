package com.bunny.eschatAddons.HUD;

import com.bunny.eschatAddons.config.ConfigHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.spi.CurrencyNameProvider;

public class NoDTHUD {

    private int DTEnabled = 0xFF0000;
    private int DTDisabled = 0x00FF00;

    private int CurrentColor;

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {

        if (!ConfigHandler.NoDTHUD) return;

        if (event.type == RenderGameOverlayEvent.ElementType.TEXT);
        this.drawHUD(event.resolution);
    }

    private void drawHUD(ScaledResolution resolution){
        final int top = 0;
        final int left = 0;
        final int bottom = resolution.getScaledHeight();
        final int right = resolution.getScaledWidth();

        final FontRenderer fr = Minecraft.getMinecraft().fontRendererObj;

        final String text = "NoDT";

        int CurrentColor = ConfigHandler.NoDTEnabled ? DTDisabled : DTEnabled;

        fr.drawStringWithShadow(text, ConfigHandler.NoDTHUD_X, ConfigHandler.NoDTHUD_Y, CurrentColor);
    }

}