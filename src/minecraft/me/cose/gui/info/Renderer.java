package me.cose.gui.info;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class Renderer {
	
	public static Renderer instance;
	public static String renderBold(String s){
		int index = s.indexOf("[");
		int index2 = s.indexOf("]");
		String end = new StringBuilder(s).insert(index + 1, "§l").toString();
		String end2 = new StringBuilder(end).insert(index2 + 2, "§r").toString();
		return end2;
		
		
	}
	
	public static String renderUnderlined(String s){
		int index = s.indexOf("[");
		int index2 = s.indexOf("]");
		String end = new StringBuilder(s).insert(index + 1, "§n").toString();
		String end2 = new StringBuilder(end).insert(index2 + 2, "§r").toString();
		return end2;
		
	}
	public static String renderCursive(String s){
		int index = s.indexOf("[");
		int index2 = s.indexOf("]");
		Minecraft mc = Minecraft.getMinecraft();
		mc.thePlayer.addChatMessage(new ChatComponentText("[§oClock§r]"));
		
		String end = new StringBuilder(s).insert(index + 1 , "§o").toString();
		String end2 = new StringBuilder(end).insert(index2 + 2, "§r").toString();
		return end2;
		
	}
	

}
