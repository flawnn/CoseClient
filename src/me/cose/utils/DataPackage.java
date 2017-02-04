package me.cose.utils;

import java.awt.image.BufferedImage;

import net.minecraft.item.ItemStack;

public class DataPackage {
	
	private String[] strings;
	private int[] ints;
	private double[] doubles;
	private boolean[] booleans;
	private ItemStack[] items;
	private BufferedImage[] images;
	
	public String[] getStrings()
	{
		return strings;
	}
	
	public void setStrings(String[] strings)
	{
		this.strings = strings;
	}
	
	public int[] getInts()
	{
		return ints;
	}
	
	public void setInts(int[] ints)
	{
		this.ints = ints;
	}
	
	public double[] getDoubles()
	{
		return doubles;
	}
	
	public void setDoubles(double[] doubles)
	{
		this.doubles = doubles;
	}
	
	public boolean[] getBooleans()
	{
		return booleans;
	}
	
	public void setBooleans(boolean[] booleans)
	{
		this.booleans = booleans;
	}
	
	public ItemStack[] getItems()
	{
		return items;
	}
	
	public void setItems(ItemStack[] items)
	{
		this.items = items;
	}
	
	public BufferedImage[] getImages()
	{
		return images;
	}
	
	public void setImages(BufferedImage[] images)
	{
		this.images = images;
	}
	
}
