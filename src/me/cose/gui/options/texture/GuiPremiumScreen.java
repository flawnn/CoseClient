package me.cose.gui.options.texture;

import java.io.IOException;

import me.cose.config.InfoPremium;
import net.minecraft.client.gui.GuiScreen;

public class GuiPremiumScreen extends GuiScreen {
	
	private GuiScreen oldScreen;
	
	public GuiPremiumScreen(GuiScreen screen)
	{
		oldScreen = screen;
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		super.drawScreen(mouseX, mouseY, partialTicks);
		drawDefaultBackground();
		
		drawCenteredString(fontRendererObj, InfoPremium.title, width / 2, 10, 0xFFFFFF);
		
		int add = 0;
		boolean premium = false;
		boolean green = true;
		int up = 0;
		for(String line : InfoPremium.lines)
		{
			add ++;
			String c = green ? "§a" : "§e";
			green = !green;
			int x = width / 4 * (premium ? 3 : 1);
			if(add > 2)
				x -= 9;
			String l = line.replaceAll("&c", c).replace('|', '┃');
			drawCenteredString(fontRendererObj, l, x, 40+up, 0xFFFFFF);
			if(premium)
				up += 12;
			premium = !premium;
		}
	}
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		mc.displayGuiScreen(oldScreen);
	}
	
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		mc.displayGuiScreen(oldScreen);
	}
	
}

