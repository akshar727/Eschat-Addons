package com.bunny.eschatAddons.features;

import com.bunny.eschatAddons.config.ConfigHandler;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import net.minecraft.client.Minecraft;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.StringUtils;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.*;
import java.util.stream.Collectors;
import java.text.DecimalFormat;

public class RevTradeListener {

    public RevTradeListener() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    private String cleanSB(String scoreboard) {
        char[] nvString = StringUtils.stripControlCodes(scoreboard).toCharArray();
        StringBuilder cleaned = new StringBuilder();

        for (char c : nvString) {
            if ((int) c > 20 && (int) c < 127) {
                cleaned.append(c);
            }
        }

        return cleaned.toString();
    }

    public static List<String> getSidebarLines() {
        List<String> lines = new ArrayList<>();
        if (Minecraft.getMinecraft().theWorld == null) return lines;
        Scoreboard scoreboard = Minecraft.getMinecraft().theWorld.getScoreboard();
        if (scoreboard == null) return lines;

        ScoreObjective objective = scoreboard.getObjectiveInDisplaySlot(1);
        if (objective == null) return lines;

        Collection<Score> scores;
        try {
            scores = scoreboard.getSortedScores(objective);
        } catch (ConcurrentModificationException ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }

        List<Score> list = scores.stream()
                .filter(input -> input != null && input.getPlayerName() != null && !input.getPlayerName()
                        .startsWith("#"))
                .collect(Collectors.toList());

        if (list.size() > 15) {
            scores = Lists.newArrayList(Iterables.skip(list, scores.size() - 15));
        } else {
            scores = list;
        }

        for (Score score : scores) {
            ScorePlayerTeam team = scoreboard.getPlayersTeam(score.getPlayerName());
            lines.add(ScorePlayerTeam.formatPlayerName(team, score.getPlayerName()));
        }

        return lines;
    }

    private boolean isInScoreboard(String text) {
        List<String> scoreboard = getSidebarLines();
        for (String s : scoreboard) {
            String sCleaned = cleanSB(s);
            if (sCleaned.contains(text)) return true;
        }
        return false;
    }

    private boolean bossMessageSent =false;

    @SubscribeEvent(receiveCanceled = true)
    public void onChatReceived(ClientChatReceivedEvent event) {
        if (!ConfigHandler.RevTradeListenerEnabled) {
            return;
        }
        DecimalFormat df = new DecimalFormat("#");
        String formattedMessage = event.message.getFormattedText();
        System.out.println(bossMessageSent + " " + formattedMessage);
        if (formattedMessage.contains("§r§6§lSLAYER MINI-BOSS §r§c§lAtoned Champion§r§7 has spawned!")) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/pc Atoned Champion: x: "+df.format(Minecraft.getMinecraft().thePlayer.posX)+" y: "+df.format(Minecraft.getMinecraft().thePlayer.posY)+" z: "+df.format(Minecraft.getMinecraft().thePlayer.posZ));
        }
        if (formattedMessage.contains("§r§6§lSLAYER MINI-BOSS §r§4§lAtoned Revenant§r§7 has spawned!")) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/pc Atoned Rev: x: "+df.format(Minecraft.getMinecraft().thePlayer.posX)+" y: "+df.format(Minecraft.getMinecraft().thePlayer.posY)+" z: "+df.format(Minecraft.getMinecraft().thePlayer.posZ));
        }
        if (isInScoreboard("Slay the boss!") && isInScoreboard("Revenant Horror") && !bossMessageSent && !isInScoreboard("Boss Slain!")) {
            bossMessageSent = true;
            Minecraft.getMinecraft().thePlayer.sendChatMessage("/pc Boss: x: "+df.format(Minecraft.getMinecraft().thePlayer.posX)+" y: "+df.format(Minecraft.getMinecraft().thePlayer.posY)+" z: "+df.format(Minecraft.getMinecraft().thePlayer.posZ));
        }
        if (formattedMessage.contains("§r§a§lSLAYER QUEST COMPLETE!") && bossMessageSent) {
            bossMessageSent = false;
        }
    }
}
