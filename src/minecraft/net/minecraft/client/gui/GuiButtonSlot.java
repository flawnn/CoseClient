package net.minecraft.client.gui;

import java.util.Random;

import me.cose.config.Options;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class GuiButtonSlot extends GuiButton {
	
	public ItemStack item;
	public boolean selected;
	
	public GuiButtonSlot(int slotId, int x, int y)
	{
		super(slotId, x, y, 20, 20, "");
		selected = false;
	}
	
	public void generateRandomItem()
	{
		Random rnd = new Random();
		Item item = null;
		while(item == null)
		{
			int id = rnd.nextInt(200);
			item = Item.getItemById(id);
		}
		this.item = new ItemStack(item);
	}
	
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
		if(this.visible)
		{
			int x = xPosition;
			int y = yPosition;
			ScaledResolution scale = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			int var4 = scale.getScaledWidth() / 2;
			mc.getTextureManager().bindTexture(GuiIngame.widgetsTexPath);
			if(selected)
				drawTexturedModalRect(x-1, y-1, 1, 23, 22, 22);
			else
				drawTexturedModalRect(x, y, 21, 1, 20, 20);
			if(item != null)
				drawItem(mc, scale);
		}
		if(Options.crazyButtons)
			move();
    }
	
	private void drawItem(Minecraft mc, ScaledResolution scale)
	{
		GlStateManager.enableRescaleNormal();
		GlStateManager.enableBlend();
		GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
		RenderHelper.enableGUIStandardItemLighting();
		int var7 = scale.getScaledWidth() / 2 - 90 + 0 * 20 + 2;
		int var8 = scale.getScaledHeight() - 16 - 3;
		var7 = xPosition+2;
		var8 = yPosition+2;
		drawItem(mc, 0, var7, var8);
		RenderHelper.disableStandardItemLighting();
		GlStateManager.disableRescaleNormal();
		GlStateManager.disableBlend();
	}
	
	private void drawItem(Minecraft mc, int p_175184_1_, int p_175184_2_, int p_175184_3_)
	{
		ItemStack var6 = item;
		if(var6 != null)
		{
			float var7 = (float) var6.animationsToGo;
			if(var7 > 0.0F)
			{
				GlStateManager.pushMatrix();
				float var8 = 1.0F + var7 / 5.0F;
				GlStateManager.translate((float) (p_175184_2_ + 8), (float) (p_175184_3_ + 12), 0.0F);
				GlStateManager.scale(1.0F / var8, (var8 + 1.0F) / 2.0F, 1.0F);
				GlStateManager.translate((float) (-(p_175184_2_ + 8)), (float) (-(p_175184_3_ + 12)), 0.0F);
			}
			RenderItem itemRenderer = mc.getRenderItem();
			itemRenderer.func_180450_b(var6, p_175184_2_, p_175184_3_);
			if(var7 > 0.0F)
				GlStateManager.popMatrix();
			itemRenderer.func_175030_a(mc.fontRendererObj, var6, p_175184_2_, p_175184_3_);
		}
	}
	
}
