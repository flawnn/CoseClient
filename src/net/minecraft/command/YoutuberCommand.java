package net.minecraft.command;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

public class YoutuberCommand extends CommandBase {
	
	public String getCommandName()
	{
		return "youtube";
	}
	
	public int getRequiredPermissionLevel()
	{
		return 0;
	}
	
	public String getCommandUsage(ICommandSender sender)
	{
		return "commands.youtuber.usage";
	}
	
	public void processCommand(ICommandSender sender, String[] args) throws CommandException
	{
		Thread thread = new Thread(new Runnable() {
			public void run()
			{
				if(args.length > 1)
				{
					String name = args[1];
					EntityPlayer entityPlayer = null;
					Iterator it = Minecraft.getMinecraft().theWorld.playerEntities.iterator();
					while(it.hasNext())
					{
						EntityPlayer entity = (EntityPlayer) it.next();
						if(entity.getName().equals(name))
							entityPlayer = entity;
					}
					if(entityPlayer == null)
						sender.addChatMessage(new ChatComponentText("§4" + args[1] + " wurde nicht gefunden!"));
					else
					{
						String linkname = getUsernameByUUID(entityPlayer.getUniqueID().toString());
						if(!name.equals(linkname))
							sender.addChatMessage(new ChatComponentText("§e" + name + " §8>> §5" + linkname));
						else
							sender.addChatMessage(new ChatComponentText("§4" + args[1] + " ist nicht genickt!"));
					}
				}
				else
				{
					List<String> names = Lists.newArrayList();
					Iterator it = Minecraft.getMinecraft().theWorld.playerEntities.iterator();
					while(it.hasNext())
					{
						EntityPlayer entityPlayer = (EntityPlayer) it.next();
						String name = entityPlayer.getName();
						String linkname = getUsernameByUUID(entityPlayer.getUniqueID().toString());
						if(!name.equals(linkname))
							names.add(name + " " + linkname);
					}
					if(names.isEmpty())
						sender.addChatMessage(new ChatComponentText("§4Keine genickten Spieler gefunden!"));
					for(String name : names)
					{
						String[] nms = name.split(" ");
						sender.addChatMessage(new ChatComponentText("§e" + nms[0] + " §8>> §5" + nms[1]));
					}
				}
			}
		});
		thread.setName("Youtuber Checker");
		thread.start();
	}
	
	private String getUsernameByUUID(String uuid)
	{
		uuid = uuid.replaceAll("-", "");
		try
		{
			String line = new BufferedReader(new InputStreamReader(new URL("https://api.mojang.com/user/profiles/" + uuid + "/names").openConnection().getInputStream())).readLine();
			String raw = line.split(",")[0];
			String name = "";
			if(raw.endsWith("]"))
				name = raw.substring(10, raw.length() - 3);
			else
			{
				int stack = 1;
				name = null;
				while(name == null)
				{
					raw = line.split(",")[stack];
					if(raw.endsWith("]"))
					{
						raw = line.split(",")[stack - 1];
						name = raw.substring(9, raw.length() - 1);
					}
					else
						stack ++;
				}
			}
			return name;
		}
		catch(Exception err) {}
		return "§cCracked";
	}
	
}
