package me.cose.utils.texture;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.common.collect.Maps;

import me.cose.config.Core;
import me.cose.config.InfoAccounts;

public class HeadManager {
	
	public static String urlMainHead = "https://minotar.net/armor/body/%name%/48.png";
	public static BufferedImage imgMainHead = null;
	public static double currentMainHead;
	
	private String urlMenu = "https://minotar.net/helm/%name%/24.png";
	private String urlNormal = "https://minotar.net/helm/%name%/64.png";
	private String urlCube = "https://minotar.net/cube/%name%/64.png";
	public SkinType type;
	
	private BufferedImage tree;
	private List<BufferedImage> menuSkins;
	private Map<String, File> skinsNormal;
	private Map<String, File> skinsCubed;
	
	public HeadManager()
	{
		type = SkinType.DEFAULT;
		skinsNormal = Maps.newHashMap();
		skinsCubed = Maps.newHashMap();
	}
	
	public static BufferedImage getNull(int width, int height)
	{
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		g.setColor(new Color(100, 100, 100, 100));
		g.fill3DRect(0, 0, width, height, true);
		return image;
	}
	
	public BufferedImage getNormal(String name)
	{
		if(skinsNormal.containsKey(name))
			try
			{
				return ImageIO.read(skinsNormal.get(name));
			}
			catch(Exception err)
			{
				return getNull(64, 64);
			}
		loadNormal(name);
		return getNormal(name);
	}
	
	public BufferedImage getCubed(String name)
	{
		if(skinsCubed.containsKey(name))
			try
			{
				return ImageIO.read(skinsCubed.get(name));
			}
			catch(Exception err)
			{
				return getNull(64, 64);
			}
		loadCubed(name);
		return getCubed(name);
	}
	
	public List<BufferedImage> getMenu()
	{
		if(menuSkins == null)
			load();
		return menuSkins;
	}
	
	private void load()
	{
		menuSkins = new ArrayList<BufferedImage>();
		List<BufferedImage> list = new ArrayList<BufferedImage>();
		InfoAccounts in = Core.accounts;
		for(int i = 0; i <= in.getMax(); i ++)
		{
			String name = in.getData(i)[1];
			list.add(get(name));
		}
		menuSkins = list;
	}
	
	private BufferedImage get(String name)
	{
		BufferedImage image;
		String link;
		try
		{
			if(name.contains("Player")) {
			 link = urlMenu.replaceAll("%name%", "OzzkarYT");
			
			} else {
			 link = urlMenu.replaceAll("%name%", name);
			}
			URL url = new URL(link);
			BufferedImage img = null;
			try {
				img = ImageIO.read(url);
			} catch(Exception err) {
				image = null;
			}
			image = img;
		}
		catch(Exception err)
		{
			err.printStackTrace();
			image = null;
		}
		if(image == null)
			image = getNull(24, 24);
		return image;
	}
	
	private void loadNormal(String name)
	{
		BufferedImage image;
		try
		{
			String link = urlNormal.replaceAll("%name%", name);
			URL url = new URL(link);
			BufferedImage img = null;
			try {
				img = ImageIO.read(url);
			} catch(Exception err) {
				image = null;
			}
			image = img;
		}
		catch(Exception err)
		{
			err.printStackTrace();
			image = null;
		}
		if(image != null)
			try
			{
				File file = File.createTempFile(name, ".png", new File("Cose"));
				file.deleteOnExit();
				ImageIO.write(image, "png", file);
				skinsNormal.put(name, file);
			}
			catch(Exception err) {}
	}
	
	private void loadCubed(String name)
	{
		BufferedImage image;
		try
		{
			String link = urlCube.replaceAll("%name%", name);
			URL url = new URL(link);
			BufferedImage img = null;
			try {
				img = ImageIO.read(url);
			} catch(Exception err) {
				image = null;
			}
			image = img;
		}
		catch(Exception err)
		{
			err.printStackTrace();
			image = null;
		}
		if(image != null)
			try
			{
				File file = File.createTempFile(name, ".png", new File("Cose"));
				file.deleteOnExit();
				ImageIO.write(image, "png", file);
				skinsCubed.put(name, file);
			}
			catch(Exception err) {}
	}
	
}
