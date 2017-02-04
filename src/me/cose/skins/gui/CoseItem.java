package me.cose.skins.gui;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

public class CoseItem {
	
	private CoseItemType type;
	private CoseItemSlot slotType;
	private String slotName;
	private String name;
	private Item item;
	private Block block;
	private String picturePath;
	private String extra;
	
	public CoseItem(CoseItemType type)
	{
		this.type = type;
		slotType = type.getSlotType();
		slotName = slotType.getName();
		name = type.getName();
		item = type.getItem();
		block = type.getBlock();
		picturePath = type.getPicturePath();
	}
	
	public CoseItemType getType()
	{
		return type;
	}
	
	public void setExtra(String extra)
	{
		this.extra = extra;
	}
	
	public String getExtra()
	{
		return extra;
	}
	
	public enum CoseItemSlot {
		
		CASES("Cases"),
		SKINS("Skins"),
		HAT("Hut"),
		GLASSES("Brille"),
		SCARF("Schal"),
		CAPE("Cape"),
		OTHER("Anderes");
		
		private String name;
		
		CoseItemSlot(String n)
		{
			name = n;
		}
		
		public String getName()
		{
			return name;
		}
		
	}
	
	public enum CoseItemType {
		
		COINS("Münze(n)", Items.gold_nugget, CoseItemSlot.OTHER);
		
		private String name = null;
		private CoseItemSlot slot = null;
		private Item item = null;
		private Block block = null;
		private String picturePath = null;
		
		CoseItemType(String n, Item i, CoseItemSlot s)
		{
			name = n;
			item = i;
			slot = s;
		}
		
		CoseItemType(String n, Block b, CoseItemSlot s)
		{
			name = n;
			block = b;
			slot = s;
		}
		
		CoseItemType(String n, String p, CoseItemSlot s)
		{
			name = n;
			picturePath = p;
			slot = s;
		}
		
		public String getName()
		{
			return name;
		}
		
		public CoseItemSlot getSlotType()
		{
			return slot;
		}
		
		public Item getItem()
		{
			return item;
		}
		
		public Block getBlock()
		{
			return block;
		}
		
		public String getPicturePath()
		{
			return picturePath;
		}
		
	}
	
}
