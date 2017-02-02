package me.cose.gui.info.utils;

import me.cose.utils.ColorUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public class Renderer {
	
	public static  int current = 0;
	public static boolean rainbow = false;
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
		
		String end = new StringBuilder(s).insert(index + 1 , "§o").toString();
		String end2 = new StringBuilder(end).insert(index2 + 2, "§r").toString();
		return end2;
		
	}
	public static int renderRainbow() {
		int a;
		ColorUtils cu = new ColorUtils();
		if(rainbow) {
			a = cu.rainbowEffect(0 + 1 * 200000000L, 1.0F).getRGB();
		} else {
			a = 0xFFFFFF;
		}
		return a;
	}
	public static String renderNormal(String s){
		int index = s.indexOf("[");
		int index2 = s.indexOf("]");
		String end = new StringBuilder(s).insert(index + 1, "§r").toString();
		String end2 = new StringBuilder(end).insert(index2 + 2, "§r").toString();
		return end2;
		
		
	}
	public static int getCurrent() {
		return current;
	}
	public static void setCurrent(int i){
		if(i > 3) {
			return;
		} else {
		current = i;
		}
	}
	

}
