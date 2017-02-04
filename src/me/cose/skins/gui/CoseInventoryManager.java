package me.cose.skins.gui;

import java.util.List;

import com.google.common.collect.Lists;

public class CoseInventoryManager {
	
	private List<CoseItem> items;
	
	public CoseInventoryManager()
	{
		loadItems();
	}
	
	private void loadItems()
	{
		items = Lists.newArrayList();
	}
	
}
