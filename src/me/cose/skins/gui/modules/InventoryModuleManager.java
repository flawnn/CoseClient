package me.cose.skins.gui.modules;

import java.util.Map;

import com.google.common.collect.Maps;

import me.cose.skins.gui.CoseItem.CoseItemSlot;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class InventoryModuleManager {
	
	private static Map<Integer, Integer[]> pos;
	private static Map<CoseItemSlot, InventoryModule> list;
	public static CoseItemSlot currentType;
	private static InventoryModule currentMod;
	private static int[] prev;
	
	static
	{
		list = Maps.newHashMap();
		pos = Maps.newHashMap();
		new InvModCape();
		new InvModCases();
		new InvModGlasses();
		new InvModHat();
		new InvModOther();
		new InvModScarf();
		new InvModSkins();
	}
	
	public static void register(InventoryModule mod)
	{
		list.put(mod.getType(), mod);
		prev = new int[2];
	}
	
	public static void setCurrentType(GuiScreen screen, int currentId)
	{
		if(currentMod != null)
		{
			for(GuiButton btn : currentMod.buttonList)
				screen.buttonList.remove(btn);
			currentMod.close();
		}
		try
		{
			InventoryModule mod = list.get(currentType);
			mod.buttonList.clear();
			mod.init();
			int keyId = currentId;
			for(GuiButton btn : mod.buttonList)
			{
				updateButton(screen, btn);
				btn.id = keyId;
				keyId ++;
				screen.buttonList.add(btn);
			}
			currentMod = mod;
		}
		catch(Exception err) {}
	}
	
	private static void updateButton(GuiScreen screen, GuiButton btn)
	{
		int id = btn.rawid;
		int x = btn.xPosition;
		int y = btn.yPosition;
		x = 5 + screen.width / 4 * (1 + (id % 2));
		y = 10 + screen.height / 10 * (1 + (id / 2));
		int mul = 0;
		if(id < 0)
		{
			int cid = prev[0];
			int w = prev[1];
			x = 5 + screen.width / 4 * (1 + (cid % 2)) + w;
			y = 10 + screen.height / 10 * (1 + (cid / 2));
			mul = w;
			id = cid;
		}
		btn.xPosition = x;
		btn.yPosition = y;
		prev[0] = id;
		prev[1] = btn.width + mul;
	}
	
	public static void close()
	{
		currentMod.close();
	}
	
	public static void button(GuiButton btn)
	{
		if(currentMod != null)
		currentMod.button(btn);
	}
	
	public static void update(GuiScreen screen)
	{
		if(currentMod != null)
		currentMod.update(screen);
	}
	
	public static void key(char keyChar, int keyCode)
	{
		if(currentMod != null)
		currentMod.key(keyChar, keyCode);
	}
	
	public static void click(int mouseX, int mouseY, int mouseButton)
	{
		if(currentMod != null)
		currentMod.click(mouseX, mouseY, mouseButton);
	}
	
}
