package me.cose.functions;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiCircle;
import net.minecraft.client.gui.GuiScreen;

public class ScreenSettings extends GuiScreen {
	
	private static int soundVolume = 0;
	private GuiScreen oldScreen;
	private GuiCircle gui;
	
	public ScreenSettings(GuiScreen oldScreen)
	{
		this.oldScreen = oldScreen;
	}
	
	public void initGui()
	{
		gui = new GuiCircle(this, "Sound Volume", 5, 5);
//		gui.setVolume(soundVolume);
	}
	
	public void onGuiClosed()
	{
		
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		gui.drawGui();
	}
	
	protected void actionPerformed(GuiButton button) throws IOException
	{
		super.actionPerformed(button);
	}
	
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);
		gui.mouseClicked(mouseX, mouseY);
	}
	
	protected void mouseReleased(int mouseX, int mouseY, int state)
	{
		super.mouseReleased(mouseX, mouseY, state);
		gui.mouseReleased();
	}
	
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
	{
		gui.mouseClickMove();
	}
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		if(keyCode == Keyboard.KEY_ESCAPE)
			mc.displayGuiScreen(oldScreen);
	}
	
}
