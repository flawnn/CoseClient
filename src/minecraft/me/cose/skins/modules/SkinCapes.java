package me.cose.skins.modules;

import java.util.Map;

import com.google.common.collect.Maps;

import me.cose.skins.Skin;
import me.cose.skins.SkinManager;
import me.cose.skins.gui.CoseItem.CoseItemSlot;
import me.cose.utils.DataPackage;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SkinCapes {
	
	public static void load()
	{
		Map<ItemStack, DataPackage> capes = Maps.newHashMap();
		
		capes.put(i(Blocks.log), d("log_oak", -1));
		capes.put(i(Items.coal), d("coal_block", -2));
		capes.put(i(Items.iron_ingot), d("iron_block", -3));
		capes.put(i(Items.gold_ingot), d("gold_block", -4));
		capes.put(i(Items.diamond), d("diamond_block", 1));
		
		for(ItemStack item : capes.keySet())
		{
			DataPackage data = capes.get(item);
			ItemStack a = item;
			CoseItemSlot b = CoseItemSlot.CAPE;
			String c = "textures/blocks/"+data.getStrings()[0]+".png";
			Skin skin = new Skin(a, b, c) {
				public boolean shallRender(EntityLivingBase entity)
				{
					if(entity.getName().equals(Minecraft.getMinecraft().getSession().getUsername()))
						return true;
					return false;
				}
				public void loadModel(ModelPlayer modelPlayer, float unkown) {}
			};
			skin.buttonId = data.getInts()[0];
			add(skin);
		}
	}
	
	private static void add(Skin skin)
	{
		SkinManager.skins.add(skin);
	}
	
	private static ItemStack i(Item item)
	{
		return new ItemStack(item);
	}
	
	private static ItemStack i(Block block)
	{
		return new ItemStack(block);
	}
	
	private static DataPackage d(String path, int slot)
	{
		DataPackage data = new DataPackage();
		data.setStrings(new String[] {path});
		data.setInts(new int[] {slot});
		return data;
	}
	
}
