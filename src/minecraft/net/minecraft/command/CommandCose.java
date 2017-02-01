package net.minecraft.command;

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class CommandCose extends CommandBase {
	
	public String getCommandName()
	{
		return "cose";
	}
	
	public int getRequiredPermissionLevel()
	{
		return 0;
	}
	
	public String getCommandUsage(ICommandSender sender)
	{
		return "command.cose.usage";
	}
	
	public List getCommandAliases()
	{
		return Arrays.asList(new String[] {"?"});
	}
	
	public void processCommand(ICommandSender sender, String[] args) throws CommandException
	{
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;
		player.addChatMessage(new ChatComponentText("Na"));
	}
	
}
