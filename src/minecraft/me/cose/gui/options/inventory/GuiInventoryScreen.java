package me.cose.gui.options.inventory;

import java.io.IOException;
import java.util.List;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class GuiInventoryScreen extends GuiScreen {
	
	private GuiScreen oldScreen;
	private int currentYState;
	private List<GuiInventoryPack> inventoryPacks;
	
	public GuiInventoryScreen(GuiScreen oldScreen)
	{
		this.oldScreen = oldScreen;
	}
	
	public void initGui()
	{
		currentYState = InventoryManager.currentYState;
		inventoryPacks = InventoryManager.inventoryPacks;
	}
	
	public void onGuiClosed()
	{
		InventoryManager.currentYState = currentYState;
	}
	
	public void drawScreen(int mouseX, int mouseY, float ticks)
	{
		drawDefaultBackground();
		for(GuiInventoryPack pack : inventoryPacks)
			pack.drawPack(currentYState);
		super.drawScreen(mouseX, mouseY, ticks);
	}
	
	protected void actionPerformed(GuiButton button) throws IOException
	{
	}
	
	public void handleMouseInput() throws IOException
	{
		super.handleMouseInput();
		int speed = (int) (Mouse.getEventDWheel() * 0.05);
		if(speed != 0)
		{
			int newy = currentYState - speed;
			if(newy < 0)
				newy = 0;
			else if(newy > inventoryPacks.size() * height)
				newy = inventoryPacks.size() * height;
			currentYState = newy;
		}
	}
	
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	protected void mouseReleased(int mouseX, int mouseY, int mouseButton)
	{
		super.mouseReleased(mouseX, mouseY, mouseButton);
	}
	
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
	{
	}
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		if(keyCode == Keyboard.KEY_ESCAPE)
		{
			mc.displayGuiScreen(oldScreen);
		}
	}
	
}
