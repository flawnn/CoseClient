package me.cose.skins.gui;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Maps;

import me.cose.config.Core;
import me.cose.skins.gui.CoseItem.CoseItemSlot;
import me.cose.skins.gui.modules.InventoryModuleManager;
import me.cose.utils.texture.GuiButtonExtended;
import me.cose.utils.texture.HeadManager;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;

public class CoseInventoryScreen extends GuiScreen {
	
	private static int current = -1;
	public static int currentId;
	private GuiScreen oldScreen;
	private static CoseInventoryScreen s;
	private String currentMod;
	private GuiButtonExtended skinBtn;
	
	public CoseInventoryScreen(GuiScreen screen)
	{
		s = this;
		oldScreen = screen;
		currentMod = "";
	}
	
	public void initGui()
	{
		currentId = 0;
		buttonList.clear();
		for(CoseItemSlot type : CoseItemSlot.values())
		{
			int x = PositionManager.getX(currentId);
			int y = PositionManager.getY(currentId);
			GuiButton btn = new GuiButton(currentId, x, y, 75, 20, type.getName()+(Core.developerMode ? " ["+currentId+"]" : ""));
			PositionManager.set(btn);
			currentId ++;
			buttonList.add(btn);
		}
		PositionManager.update(this);
		skinBtn = new GuiButtonExtended(currentId, 0, 0, HeadManager.imgMainHead);
		skinBtn.xPosition = width - skinBtn.getImage().getWidth() / 2 - 48;
		skinBtn.yPosition = height / 2 - skinBtn.getImage().getHeight() / 2;
		buttonList.add(skinBtn);
		currentId ++;
	}
	
	public void onGuiClosed()
	{
		InventoryModuleManager.close();
	}
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		if(keyCode == Keyboard.KEY_ESCAPE)
		{
			mc.displayGuiScreen(oldScreen);
		}
		InventoryModuleManager.key(typedChar, keyCode);
//		super.keyTyped(typedChar, keyCode);
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		TickManager.onTick(this);
		drawDefaultBackground();
		String c = "┃";
		drawCenteredString(fontRendererObj, "Cose Inventar "+c+" "+currentMod, width / 5 * 3, 10, 0xFFFFFF);
		
		drawField(0, 0, 98, height);
		drawField(102, 26, width - 100, height - 10);
		drawField(width - 96, 26, width - 4, height - 10);
		
		drawString(fontRendererObj, ">>", 7, height / 2 - 3, 0xFFFFFF);
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		InventoryModuleManager.update(this);
	}
	
	private void drawField(int fromX, int fromY, int toX, int toY)
	{
		Tessellator var6 = Tessellator.getInstance();
		WorldRenderer var7 = var6.getWorldRenderer();
		mc.getTextureManager().bindTexture(Gui.optionsBackground);
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		float var8 = 40.0F;
		var7.startDrawingQuads();
		var7.func_178991_c(2105376);
		var7.addVertexWithUV((double) fromX, (double) toY, 0.0D, (double) ((float) fromX / var8), (double) ((float) toY / var8));
		var7.addVertexWithUV((double) toX, (double) toY, 0.0D, (double) ((float) toX / var8), (double) ((float) toY / var8));
		var7.addVertexWithUV((double) toX, (double) fromY, 0.0D, (double) ((float) toX / var8), (double) ((float)fromY / var8));
        var7.addVertexWithUV((double) fromX, (double) fromY, 0.0D, (double) ((float) fromX / var8), (double) ((float) fromY / var8));
        var6.draw();
	}
	
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);
		InventoryModuleManager.click(mouseX, mouseY, mouseButton);
	}
	
	protected void mouseReleased(int mouseX, int mouseY, int state)
	{
//		super.mouseReleased(mouseX, mouseY, state);
	}
	
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if(button.equals(skinBtn))
		{
			return;
		}
		if(PositionManager.is(button.id))
		{
			PositionManager.select(button.id, this, true);
			return;
		}
		InventoryModuleManager.button(button);
//		super.actionPerformed(button);
	}
	
	private static class PositionManager {
		private static long wait;
		private static Map<Integer, GuiButton> map;
		public static int selected;
		static
		{
			map = Maps.newHashMap();
			wait = System.currentTimeMillis();
			selected = 0;
		}
		public static void set(GuiButton btn)
		{
			map.put(btn.id, btn);
		}
		public static boolean is(int id)
		{
			long time = System.currentTimeMillis();
			long dis = time - wait;
			if(dis < 100)
				return false;
			wait = time;
			return map.containsKey(id);
		}
		public static void update(GuiScreen screen)
		{
			if(current == -1)
				current = (int) map.keySet().toArray()[map.size() / 2];
			select(current, screen, false);
			selected = current;
			InventoryModuleManager.currentType = CoseItemSlot.values()[current];
			InventoryModuleManager.setCurrentType(screen, currentId);
			((CoseInventoryScreen) screen).currentMod = InventoryModuleManager.currentType.getName();
		}
		public static void select(int id, GuiScreen screen, boolean toggle)
		{
			current = (int) map.keySet().toArray()[id];
			Iterator<Integer> keys = map.keySet().iterator();
			while(keys.hasNext())
			{
				int key = keys.next();
				int dis = key - current;
				int change = dis > 0 ? -1 : 1;
				int x = 20 / (dis * change * -1 + 1); // (dis * 10 * change) + (22);
				if(!toggle)
					map.get(key).xPosition = x;
				int y = (screen.height / 2) + (dis * screen.height / (map.size()+1)) - (10);
				if(!toggle)
					map.get(key).yPosition = y;
				else
					TickManager.set(map.get(key), x, y);
			}
			setHover();
		}
		private static void setHover()
		{
			for(GuiButton btn : map.values())
				if(btn.id == current)
					btn.setHover = true;
				else
					btn.setHover = false;
		}
		public static int getX(int i)
		{
			return 0;
		}
		public static int getY(int i)
		{
			return 0;
		}
	}
	
	private static class TickManager {
		private static long last;
		private static Map<GuiButton, Integer> xmap;
		private static Map<GuiButton, Integer> ymap;
		static
		{
			last = System.currentTimeMillis();
			xmap = Maps.newHashMap();
			ymap = Maps.newHashMap();
		}
		public static void set(GuiButton btn, int posX, int posY)
		{
			xmap.put(btn, posX);
			ymap.put(btn, posY);
		}
		public static void tick(GuiScreen screen)
		{
			boolean f = false, co = false;
			Map<GuiButton, Integer> nmap = Maps.newHashMap();
			for(GuiButton btn : xmap.keySet())
			{
				if(btn.id == current)
					co = true;
				int c = btn.xPosition;
				int t = xmap.get(btn);
				if(c > t)
				{
					btn.xPosition --;
					nmap.put(btn, t);
					if(btn.id == current)
						f = true;
				}
				else if(c < t)
				{
					btn.xPosition ++;
					nmap.put(btn, t);
					if(btn.id == current)
						f = true;
				}
			}
			xmap.clear();
			xmap = nmap;
			
			nmap = Maps.newHashMap();
			for(GuiButton btn : ymap.keySet())
			{
				if(btn.id == current)
					co = true;
				int c = btn.yPosition;
				int t = ymap.get(btn);
				int d = Math.abs(c - t);
				int n = d / 10;
				int h = 0;
				if(n == 0)
					n = 1;
				if(c > t)
				{
					btn.yPosition -= n;
					nmap.put(btn, t);
					if(btn.id == current)
						f = true;
				}
				else if(c < t)
				{
					btn.yPosition += n;
					nmap.put(btn, t);
					if(btn.id == current)
						f = true;
				}
			}
			ymap.clear();
			ymap = nmap;
			if(!f && co)
			{
				PositionManager.selected = current;
				InventoryModuleManager.currentType = CoseItemSlot.values()[current];
				InventoryModuleManager.setCurrentType(screen, currentId);
				((CoseInventoryScreen) screen).currentMod = InventoryModuleManager.currentType.getName();
			}
		}
		public static void onTick(GuiScreen screen)
		{
			long current = System.currentTimeMillis();
			long distance = current - last;
			if(distance > 10)
			{
				last = current;
				tick(screen);
			}
		}
	}
	
}

