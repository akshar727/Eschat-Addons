package com.example.eschatAddons.Commands;

import jdk.nashorn.internal.runtime.regexp.joni.Config;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.CommandBase;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import com.example.eschatAddons.config.ConfigHandler;

import java.util.List;

public class NoDT extends CommandBase {

    @Override
    public String getCommandName(){
        return "nodt";
    }

    @Override
    public String getCommandUsage(ICommandSender sender){
        return "/" + this.getCommandName();
    }

    @Override
    public boolean canCommandSenderUseCommand(ICommandSender sender){
        return true;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (ConfigHandler.NoDTEnabled){
            ConfigHandler.NoDTEnabled = false;
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.BOLD + "[EschatAddons] " + EnumChatFormatting.GOLD + "" + EnumChatFormatting.BOLD + "No Downtime Enabled: " + EnumChatFormatting.AQUA + "" + EnumChatFormatting.BOLD + "" +  ConfigHandler.NoDTEnabled));
        }else{
            ConfigHandler.NoDTEnabled = true;
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_PURPLE + "" + EnumChatFormatting.BOLD + "[EschatAddons] " + EnumChatFormatting.GOLD + "" + EnumChatFormatting.BOLD + "No Downtime Enabled: " + EnumChatFormatting.AQUA + "" + EnumChatFormatting.BOLD + "" + ConfigHandler.NoDTEnabled));
        }

}}