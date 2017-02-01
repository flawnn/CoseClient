package me.cose.gui.join.utils;

import java.awt.image.BufferedImage;

import me.cose.config.Core;
import me.cose.gui.join.CoseJoinScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public class PlayerDataInfo implements GuiListExtended.IGuiListEntry {
	
private static final ResourceLocation SERVER_SELECTION_BUTTONS = new ResourceLocation("textures/gui/server_selection.png");
	
	private BufferedImage image;
	
	private CoseJoinScreen owner;
	private String name;
	private boolean online;
	private String server;
	private boolean loaded;
	private Minecraft mc;
	
	public PlayerDataInfo(CoseJoinScreen ownerIn, String nameIn)
	{
		owner = ownerIn;
		name = nameIn;
		online = false;
		server = "";
		loaded = false;
		mc = Minecraft.getMinecraft();
		
		loadImage();
	}
	
	private void loadImage()
	{
		if(Core.head.type.isCubed())
			image = Core.head.getCubed(name);
		else
			image = Core.head.getNormal(name);
	}
	
	public String getName()
	{
		return name;
	}
	
	public boolean isOnline()
	{
		return online;
	}
	
	public String getServer()
	{
		return server;
	}
	
	public void updateOnline(boolean onlineIn)
	{
		online = onlineIn;
	}
	
	public void updateServer(String serverIn)
	{
		server = serverIn;
	}
	
	private void drawHead(int x, int y)
	{
		if(image == null)
			return;
		BufferedImage img = image;
		ResourceLocation rl = new ResourceLocation("servers/localhost/icon");
		DynamicTexture dt = new DynamicTexture(img);
		mc.getTextureManager().loadTexture(rl, dt);
		image.getRGB(0, 0, image.getWidth(), image.getHeight(), dt.getTextureData(), 0, image.getWidth());
        dt.updateDynamicTexture();
        mc.getTextureManager().bindTexture(rl);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.enableBlend();
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
        GlStateManager.disableBlend();
	}
	
	public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected)
	{
		boolean drawed = false;
		
		if(!loaded)
		{
			loaded = true;
			online = Core.connection.isPlayerOnline(name);
			if(online)
				server = Core.connection.getServerPlayerIsOn(name);
		}
		
		String name0 = "§6§l" + name;
		if(mc.getSession().getUsername().equals(name))
			name0 = "§8[§7Du§8] §6§l" + name + " §8[§7Du§8]";
		
		mc.fontRendererObj.drawString(name0, x + 32 + 3, y + 1, 16777215);
		mc.fontRendererObj.drawString((online ? "  §2Online" : "  §4Offline"), x + 32 + 3, y + mc.fontRendererObj.FONT_HEIGHT + 2, 0xFFFFFF);
		if(online)
			mc.fontRendererObj.drawString("  §8"+server ,x + 32 + 3, y + 2 + mc.fontRendererObj.FONT_HEIGHT * 2, 0xFFFFFF);
		
		if(this.mc.gameSettings.touchscreen || isSelected)
		{
			mc.getTextureManager().bindTexture(SERVER_SELECTION_BUTTONS);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			
			int k1 = mouseX - x;
			int l1 = mouseY - y;
			
			if(online)
				if(k1 < 32 && k1 > 0)
				{
					Gui.drawModalRectWithCustomSizedTexture(x - 50, y, 0.0F, 32.0F, 32, 32, 256.0F, 256.0F);
					drawed = true;
				}
		}
		
		if(online)
			if(!drawed)
			{
				mc.getTextureManager().bindTexture(SERVER_SELECTION_BUTTONS);
				Gui.drawModalRectWithCustomSizedTexture(x - 50, y, 0.0F, 0.0F, 32, 32, 256.0F, 256.0F);
			}
		
		drawHead(x, y);
	}
	
	public boolean mousePressed(int slotIndex, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_)
	{
		if(p_148278_5_ < 32 && p_148278_5_ > 0)
		{
			owner.select(slotIndex);
			owner.joinSelectedServer();
			return true;
		}

		owner.select(slotIndex);
		return false;
	}
	
	public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {}
	
	public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {}
	
	public boolean isTextureMode() { return false; }
	
}
