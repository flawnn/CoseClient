package me.cose.skins.gui.modules;

import java.util.List;

import com.google.common.collect.Lists;

import me.cose.skins.gui.CoseItem.CoseItemSlot;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public abstract class InventoryModule {
	
	private CoseItemSlot type;
	public List<GuiButton> buttonList;
	
	public InventoryModule(CoseItemSlot type)
	{
		this.type = type;
		buttonList = Lists.newArrayList();
		InventoryModuleManager.register(this);
	}
	
	public CoseItemSlot getType()
	{
		return type;
	}
	
	public abstract void init();
	public abstract void button(GuiButton btn);
	public abstract void update(GuiScreen screen);
	public abstract void key(char keyChar, int keyCode);
	public abstract void click(int mouseX, int mouseY, int mouseButton);
	public abstract void close();
	
}
