package me.cose.gui.info.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import me.cose.utils.ColorUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;

public class ShowInfos {
	
public ShowInfos instance;


	public static String current(String msg){
		int i = Renderer.getCurrent();
		String a = null;
		if(i == 1) {
			a = Renderer.renderBold(msg);
		
		}
		if(i == 0) {
			a = Renderer.renderNormal(msg);
		
		}
		if(i == 2) {
		 a = Renderer.renderUnderlined(msg);
		
		}
		if(i == 3) {
		 a = Renderer.renderCursive(msg);
			
		}
		return a;
	}

	public static void renderClock() {
		String msg = "[Clock]: ";
		String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
		Minecraft mc = Minecraft.getMinecraft();
		GuiIngame gi = new GuiIngame(mc);
		ColorUtils cu = new ColorUtils();
		FontRenderer fr = gi.func_175179_f();
		String a  = Renderer.renderBold(msg);
		fr.drawString(current(msg) + timeStamp, 4, 5, Renderer.renderRainbow());
	}
}
