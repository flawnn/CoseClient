package me.cose.utils.texture;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Mouse;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

public class GuiScrollList {
	
	private int x;
	private int y;
	private int width;
	private int height;
	private int color;
	private String headline;
	private List<String> infos;
	private ScrollItem scrollItem;
	private boolean changeHeadlineColor;
	private int headlineColor;
	private static Minecraft mc;
	
	public GuiScrollList(int x, int y, int width, int height, int color, String headline, List<String> lines, boolean allowChangeHeadlineColor)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
		this.headline = headline;
		this.infos = lines;
		if(infos == null)
			infos = Lists.newArrayList();
		int sx = x + width;
		int sminy = y;
		int smaxy = y + height;
		int sh = scrollItem.iconHeightFinal;
		scrollItem = new ScrollItem(sx, sminy, smaxy, sh);
		this.changeHeadlineColor = allowChangeHeadlineColor;
		this.headlineColor = 0xFFFFFF;
		this.mc = Minecraft.getMinecraft();
	}
	
	public void drawGui()
	{
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		FontRenderer renderer = mc.fontRendererObj;
		Gui.drawRect(x, y, x + width, y + height, color);
		Gui.drawRect(x, y, x + width, y + 22, color);
		Scale.scale(1.1, 1.1, 1.1);
		String text = "§n§l" + headline;
		int x = this.x + width / 2;
		x /= 1.1;
		int y = this.y + 3;
		Gui.drawCenteredString(renderer, text, x, y - 3, headlineColor);
		Scale.reset();
		double scl = 0.7;
		Scale.scale(scl, scl, scl);
		y = (int) (this.y / scl) + (int) (25 / scl);
		x = (int) (this.x / scl) + (int) (4 / scl);
		int offset;
		try {
			offset = (int) ((scrollItem.getYBehind() * 100.0 / (height - scrollItem.iconHeight)) / 100 * (infos.size() - 18));
		} catch(Exception err) {
			offset = 0;
		}
		for(String line : infos)
			if(offset > 0)
				offset --;
			else if(y * scl < this.y + height - 3)
			{
				Gui.drawString(renderer, line, x, y, 0xFFFFFF);
				y += 7 + 2;
			}
		Scale.reset();
		scrollItem.render();
	}
	
	public void handleMouseInput() throws IOException
	{
		int speed = (int) (Mouse.getEventDWheel() * 0.05);
		if(speed != 0)
			scrollItem.scroll(scrollItem.y - speed);
	}
	
	public void mouseClicked(int mouseX, int mouseY)
	{
		if(scrollItem.isItem(mouseX, mouseY))
			scrollItem.selected = mouseY - scrollItem.y;
		if(changeHeadlineColor)
		{
			int xx = this.x + width / 2;
			int yy = this.y + 3;
			int sw = mc.fontRendererObj.getStringWidth(headline);
			sw /= 1.4;
			int tx = xx + sw;
			int ty = yy + 15;
			xx -= sw;
			if(mouseX >= xx && mouseX <= tx)
				if(mouseY >= yy && mouseY <= ty)
					headlineColor = new Random().nextInt(0xFFFFFF);
		}
	}
	
	public void mouseReleased()
	{
		scrollItem.selected = -1;
	}
	
	public void mouseClickMove(int mouseX, int mouseY)
	{
		if(scrollItem.selected > -1)
			scrollItem.scroll(mouseY - scrollItem.selected);
	}
	
	private static class ScrollItem {
		
		private static final ResourceLocation icon = new ResourceLocation("textures/gui/scroll.png");
		private static final int iconWidth = 4;
		private static final int iconHeightFinal = 35;
		private int iconHeight = iconHeightFinal;
		private int x;
		private int y;
		private int minY;
		private int maxY;
		private int selected;
		
		private ScrollItem(int x, int minY, int maxY, int iconHeight)
		{
			this.iconHeight = iconHeight;
			this.x = x;
			this.y = minY;
			this.minY = minY;
			this.maxY = maxY;
			this.selected = -1;
		}
		
		private boolean isItem(int x, int y)
		{
			if(x >= this.x && x <= this.x + iconWidth)
				if(y >= this.y && y <= this.y + iconHeight)
					return true;
			return false;
		}
		
		private void scroll(int mouseY)
		{
			if(mouseY < minY)
				mouseY = minY;
			if(mouseY > maxY - iconHeight)
				mouseY = maxY - iconHeight;
			y = mouseY;
		}
		
		private void render()
		{
			mc.getTextureManager().bindTexture(icon);
			Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0F, 0.0F, iconWidth, iconHeight, iconWidth, iconHeightFinal);
		}
		
		private int getYBehind()
		{
			return y - minY;
		}
		
	}
	
	private static class Scale {
		
		private static double[] scale;
		
		private static void scale(double a, double b, double c)
		{
			scale = new double[] {a, b, c};
			GlStateManager.scale(a, b, c);
		}
		
		private static void reset()
		{
			double a = 1 / scale[0];
			double b = 1 / scale[1];
			double c = 1 / scale[2];
			GlStateManager.scale(a, b, c);
			scale = null;
		}
		
	}
	
}