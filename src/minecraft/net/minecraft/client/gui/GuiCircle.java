package net.minecraft.client.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;

import me.cose.utils.texture.HeadManager;
import me.cose.utils.texture.TextureManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiCircle extends Gui {
	
	private static BufferedImage loadImage0()
	{
		try {
			return ImageIO.read(new URL("https://facepunch.com/image.php?u=725902&dateline=1454277964"));
		} catch(Exception err) {}
		return null;
	}
	
	private static BufferedImage loadImage1()
	{
		try {
			return ImageIO.read(new URL("http://res.cloudinary.com/hrscywv4p/image/upload/c_fill,h_64,w_64,f_auto,g_faces:center,q_90/v1/844623/zen_circle_png_tkgidb.png"));
		} catch(Exception err) {}
		return null;
	}
	
	private static final BufferedImage img_0 = loadImage0();
	private static final BufferedImage img_1 = loadImage1();
	private static BufferedImage img_2 = img_1;
	private GuiScreen screen;
	private String displayString;
	private double angle;
	private boolean visible;
	private boolean enabled;
	private int x;
	private int y;
	private final int width = 64;
	private final int height = 64;
	
	public GuiCircle(GuiScreen screen, String displayString, int x, int y)
	{
		this.screen = screen;
		this.displayString = displayString;
		angle = 0.0;
		visible = true;
		enabled = true;
		this.x = x;
		this.y = y;
	}
	
	public void mouseClicked(int mouseX, int mouseY)
	{
		if(!enabled)
			return;
		if(mouseX >= x && mouseX <= x + width)
			if(mouseY >= y && mouseY <= y + height)
				setVolume(getVolumen() + 1);
	}
	
	public void mouseReleased()
	{
		if(!enabled)
			return;
	}
	
	public void mouseClickMove()
	{
		if(!enabled)
			return;
	}
	
	public void drawGui()
	{
		if(this.visible)
		{
			Minecraft mc = Minecraft.getMinecraft();
			FontRenderer renderer = mc.fontRendererObj;
			mc.getTextureManager().bindTexture(new ResourceLocation("textures/circle.png"), img_0);
			GlStateManager.color(1, 1, 1);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
			GlStateManager.blendFunc(770, 771);
			drawModalRectWithCustomSizedTexture(x, y, 0, 0, width, height, 64, 64);
			mc.getTextureManager().bindTexture(new ResourceLocation("textures/circle.png"), img_2);
			drawModalRectWithCustomSizedTexture(x, y, 0, 0, width, height, img_2.getWidth(), img_2.getHeight());
			GlStateManager.disableBlend();
			drawCenteredString(renderer, displayString, x + width / 2, y + height / 2, 0xFFFFFF);
		}
	}
	
	public void setVisible(boolean visible)
	{
		this.visible = visible;
	}
	
	public void setEnabled(boolean enabled)
	{
		this.enabled = enabled;
	}
	
	public void setVolume(double percent)
	{
		angle = percent * 3.6;
		img_2 = TextureManager.rotation(img_1, (percent / 100) * (Math.PI * 2));
	}
	
	public double getVolumen()
	{
		return angle / 3.6;
	}
	
}
