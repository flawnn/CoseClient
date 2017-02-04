package me.cose.skins.gui.modules;

import java.util.List;

import com.google.common.collect.Lists;

import me.cose.skins.gui.CoseItem.CoseItemSlot;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonSlot;
import net.minecraft.client.gui.GuiScreen;

public class InvModGlasses extends InventoryModule {
	
	private List<GuiButtonSlot> buttons;
	private List<Integer> ids;
	
	public InvModGlasses()
	{
		super(CoseItemSlot.GLASSES);
		buttons = Lists.newArrayList();
		ids = Lists.newArrayList();
		int n = -1;
		for(int i = 0; i < 16; i ++)
		{
			buttons.add(new GuiButtonSlot(i, 0, 0));
			ids.add(i);
			for(int i0 = 0; i0 < 4; i0 ++)
			{
				buttons.add(new GuiButtonSlot(n, 0, 0));
				ids.add(n);
				n --;
			}
		}
	}
	
	public void init()
	{
		buttonList.addAll(buttons);
	}
	
	public void button(GuiButton btn)
	{
		int id = btn.rawid;
		if(!ids.contains(id))
			return;
		for(GuiButtonSlot slot : buttons)
			slot.selected = slot.rawid == id;
	}
	
	public void update(GuiScreen screen)
	{
		
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
