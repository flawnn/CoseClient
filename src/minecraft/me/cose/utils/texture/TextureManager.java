package me.cose.utils.texture;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import me.cose.config.Core;
import net.minecraft.util.ResourceLocation;

public class TextureManager {
	
	public static ResourceLocation stringCross = null;
	
	private List<TextureButton> buttons;
	private List<String> stopTextures;
	
	public TextureManager()
	{
		buttons = Lists.newArrayList();
		stopTextures = Lists.newArrayList();
		
		buttons.add(new TextureButton(1, 2, -2, "Crazy Buttons", new String[] {null, null}, new String[] {"OFF", "ON"}).setEnabled(Core.premiumMode));
		
		buttons.add(new TextureButton(10, -2, -2, "Holzschwert", new String[] {null,"Holz-Schwert-Klein"}, new String[] {"Normal","Klein"}).setEnabled(false));
		buttons.add(new TextureButton(11, -2, -1, "Steinschwert", new String[] {null,"Stein-Schwert-Klein"}, new String[] {"Normal","Klein"}).setEnabled(false));
		buttons.add(new TextureButton(12, -2, 0, "Goldschwert", new String[] {null,"Gold-Schwert-Klein"}, new String[] {"Normal","Klein"}).setEnabled(false));
		buttons.add(new TextureButton(13, -2, 1, "Eisenschwert", new String[] {null,"Eisen-Schwert-Klein"}, new String[] {"Normal","Klein"}).setEnabled(false));
		buttons.add(new TextureButton(14, -2, 2, "Diamantschwert", new String[] {null,"Diamant-Schwert-Klein"}, new String[] {"Normal","Klein"}).setEnabled(false));
		
		if(Core.premiumMode)
			buttons.add(new TextureButton(15, 2, -1, "Fadenkreuz", new String[] {null,".",".",".",".","."}, new String[] {"Normal","Extra 1","Extra 2","Extra 3","Extra 4","Extra 5"}).setEnabled(false));
		else
			buttons.add(new TextureButton(15, 2, -1, "Fadenkreuz", new String[] {null,".","."}, new String[] {"Normal","Extra 1","Extra 2"}).setEnabled(false));
		
		for(TextureButton btn : buttons)
			for(String path : btn.getPaths())
				if(path != null)
					if(!stopTextures.contains(path+".zip"))
						stopTextures.add(path+".zip");
	}
	
	public List<TextureButton> getTextureButtons()
	{
		return buttons;
	}
	
	public TextureButton update(int id)
	{
		Iterator<TextureButton> it = buttons.iterator();
		while(it.hasNext())
		{
			TextureButton tx = it.next();
			if(tx.getButton().id == id)
			{
				tx.next();
				tx.update();
				return tx;
			}
		}
		return null;
	}
	
	public List<String> getStopTexturePaths()
	{
		return stopTextures;
	}
	
	public static BufferedImage rotation(BufferedImage image, double angle)
	{
		double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
		int w = image.getWidth(), h = image.getHeight();
		int neww = (int) Math.floor(w * cos + h * sin), newh = (int) Math.floor(h * cos + w * sin);
		GraphicsConfiguration gc = getDefaultConfiguration();
		if(neww == 96 && newh == 48)
			newh *= 2;
		BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
		Graphics2D g = result.createGraphics();
		g.translate((neww - w) / 2, 0 / 2);
		g.rotate(angle, w / 2, h / 2);
		g.drawRenderedImage(image, null);
		g.dispose();
		return result;
	}
	
	private static GraphicsConfiguration getDefaultConfiguration()
	{
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice gd = ge.getDefaultScreenDevice();
		return gd.getDefaultConfiguration();
	}
	
}
