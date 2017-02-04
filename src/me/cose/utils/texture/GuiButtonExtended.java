package me.cose.utils.texture;

import java.awt.image.BufferedImage;

import me.cose.config.Options;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.ResourceLocation;

public class GuiButtonExtended extends GuiButton {
	
	private BufferedImage image;
	
	public GuiButtonExtended(int buttonId, int x, int y, BufferedImage image)
	{
		super(buttonId, x, y, image.getWidth(), image.getHeight(), "");
		this.image = image;
	}
	
	public void updateImage(BufferedImage image)
	{
		this.image = image;
		super.width = image.getWidth();
		super.height = image.getHeight();
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	public void drawButton(Minecraft mc, int mouseX, int mouseY)
	{
		if(this.visible)
		{
			if(image == null)
				return;
			mc.getTextureManager().bindTexture(new ResourceLocation("textures/guibuttonextened.png"), image);
	        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	        GlStateManager.enableBlend();
	        Gui.drawModalRectWithCustomSizedTexture(xPosition, yPosition, 0.0F, 0.0F, image.getWidth(), image.getHeight(), image.getWidth(), image.getHeight());
	        GlStateManager.disableBlend();
		}
		if(Options.crazyButtons)
        	move();
	}
	
}
