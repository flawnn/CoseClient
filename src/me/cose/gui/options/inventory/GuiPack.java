package me.cose.gui.options.inventory;

import net.minecraft.util.ResourceLocation;

public class GuiPack {
	
	private static final ResourceLocation locItems = new ResourceLocation("textures/gui/widgets.png");
	private int x;
	private int y;
	private int width;
	private int height;
	private GuiItem item;
	
	public GuiPack(int x, int y, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.item = new GuiItem();
	}
	
	public void drawGui(int currentY, int height)
	{
		
	}
	
	public void mouseClick(int mouseX, int mouseY)
	{
		
	}
	
	public void mouseReleased(int mouseX, int mouseY)
	{
		
	}
	
	public void mouseMove(int mouseX, int mouseY)
	{
		
	}
	
}
