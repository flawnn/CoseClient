package me.cose.gui.info;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiIngame;

public class ShowInfos {
	
public ShowInfos instance;


	public void renderClock() {
		String msg = "[Clock]: ";
		String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
		Minecraft mc = Minecraft.getMinecraft();
		GuiIngame gi = new GuiIngame(mc);
		FontRenderer fr = gi.func_175179_f();
		fr.drawString(Renderer.renderUnderlined(msg) + timeStamp, 4, 5, 0xFFFFFF);
	}
}
