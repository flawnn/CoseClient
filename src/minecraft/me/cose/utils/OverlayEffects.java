package me.cose.utils;

import java.awt.image.BufferedImage;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;

public class OverlayEffects {
	
	public static void achiev(String title, String description, Item item, boolean always, boolean wait)
	{
		Minecraft.getMinecraft().guiAchievement.displayAchievement(title, description, item, always, wait);
	}
	
	public static void achiev(String title, String description, Block item, boolean always, boolean wait)
	{
		Minecraft.getMinecraft().guiAchievement.displayAchievement(title, description, item, always, wait);
	}
	
}
