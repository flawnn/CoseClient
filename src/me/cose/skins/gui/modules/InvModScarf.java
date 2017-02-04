package me.cose.skins.gui.modules;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import me.cose.skins.Skin;
import me.cose.skins.SkinManager;
import me.cose.skins.gui.CoseItem.CoseItemSlot;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiButtonSlot;
import net.minecraft.client.gui.GuiScreen;

public class InvModScarf extends InventoryModule {
	
	private List<GuiButtonSlot> buttons;
	private List<Integer> ids;
	private Map<Integer, Skin> idToSkin;
	
	public InvModScarf()
	{
		super(CoseItemSlot.SCARF);
		buttons = Lists.newArrayList();
		idToSkin = Maps.newHashMap();
		ids = Lists.newArrayList();
		Map<Integer, Skin> skinId = Maps.newHashMap();
		for(Skin skin : SkinManager.getSkins(CoseItemSlot.SCARF))
			skinId.put(skin.buttonId, skin);
		int n = -1;
		GuiButtonSlot btn;
		for(int i = 0; i < 16; i ++)
		{
			buttons.add(btn = new GuiButtonSlot(i, 0, 0));
			Skin skin = skinId.get(btn.rawid);
			if(skin != null)
			{
				btn.item = skin.item;
				idToSkin.put(btn.rawid, skin);
			}
			ids.add(i);
			for(int i0 = 0; i0 < 4; i0 ++)
			{
				buttons.add(btn = new GuiButtonSlot(n, 0, 0));
				skin = skinId.get(btn.rawid);
				if(skin != null)
				{
					btn.item = skin.item;
					idToSkin.put(btn.rawid, skin);
				}
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
		Skin skin = idToSkin.get(btn.rawid);
		SkinManager.select(skin);
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
