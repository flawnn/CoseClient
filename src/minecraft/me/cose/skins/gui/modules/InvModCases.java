package me.cose.skins.gui.modules;

import me.cose.skins.gui.CoseItem.CoseItemSlot;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class InvModCases extends InventoryModule {
	
	public InvModCases()
	{
		super(CoseItemSlot.CASES);
	}
	
	public void init()
	{
		
	}
	
	public void button(GuiButton btn)
	{
		
	}
	
	public void update(GuiScreen screen)
	{
		screen.drawCenteredString(screen.fontRendererObj, "Cases incoming!", screen.width / 2, screen.height / 2, 0xFFFFFF);
	}
	
	public void key(char keyChar, int keyCode)
	{
		
	}
	
	public void click(int mouseX, int mouseY, int mouseButton)
	{
		
	}
	
	public void close()
	{
		
	}
	
}
