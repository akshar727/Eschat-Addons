package com.example.eschatAddons.Commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentText;

import java.util.List;

public class TestCommand extends CommandBase {

    @Override
    public String getCommandName(){
        return "examplecommand";
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
    public void processCommand (ICommandSender sender, String[] args){
        sender.addChatMessage(new ChatComponentText("Hello!"));

        if (args.length > 0 && args[0].equalsIgnoreCase("Option1")){
            sender.addChatMessage(new ChatComponentText("Option 1 selected"));
        }

        else if

        (args.length > 0 && args[0].equalsIgnoreCase("Option2")){
            sender.addChatMessage(new ChatComponentText("Option 2 selected"));
        }
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args, BlockPos pos) {
        final String[] options = new String[]{"Option1", "Option2"};
        return getListOfStringsMatchingLastWord(args, options);
    }

}