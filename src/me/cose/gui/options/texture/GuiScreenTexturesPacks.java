package me.cose.gui.options.texture;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.Lists;

import me.cose.config.Core;
import me.cose.config.Options;
import me.cose.utils.texture.TextureButton;
import me.cose.utils.texture.TextureManager;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.gui.GuiResourcePackAvailable;
import net.minecraft.client.gui.GuiResourcePackSelected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackListEntry;
import net.minecraft.client.resources.ResourcePackListEntryDefault;
import net.minecraft.client.resources.ResourcePackListEntryFound;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.ResourceLocation;

public class GuiScreenTexturesPacks extends GuiScreen {
	
	private GuiScreen oldScreen;
	private List packsAvaible;
	private List packsConnected;
	private GuiResourcePackAvailable guiPacksAvaible;
	private GuiResourcePackSelected guiPacksConnected;
	private boolean somethingChanged = false;
	private boolean text = false;
	
	public GuiScreenTexturesPacks(GuiScreen screen)
	{
		oldScreen = screen;
	}
	
	public void initGui()
	{
		buttonList.add(new GuiOptionButton(0, width / 2 - 100, height - 48, 200, 20, I18n.format("gui.done", new Object[0])));
		
		if(!text)
			for(TextureButton btn : Core.texture.getTextureButtons())
			{
				GuiButton button = btn.getButton();
				button.xPosition = width / 2 + btn.getX();
				button.yPosition = height / 2 + btn.getY();
				btn.setButton(button);
				buttonList.add(button);
			}
		
		packsAvaible = Lists.newArrayList();
		packsConnected = Lists.newArrayList();
		ResourcePackRepository var1 = mc.getResourcePackRepository();
		var1.updateRepositoryEntriesAll_Textures();
		ArrayList var2 = Lists.newArrayList(var1.getRepositoryEntriesAll());
		var2.removeAll(var1.getRepositoryEntries());
		Iterator var3 = var2.iterator();
		ResourcePackRepository.Entry var4;
		
		while(var3.hasNext())
		{
			var4 = (ResourcePackRepository.Entry) var3.next();
			packsAvaible.add(new ResourcePackListEntryFound(this, var4));
		}
		
		var3 = Lists.reverse(var1.getRepositoryEntries()).iterator();
		
		while(var3.hasNext())
		{
			var4 = (ResourcePackRepository.Entry) var3.next();
			packsConnected.add(new ResourcePackListEntryFound(this, var4));
		}
		
		packsConnected.add(new ResourcePackListEntryDefault(this));
		guiPacksAvaible = new GuiResourcePackAvailable(mc, 200, height, packsAvaible, !text);
		guiPacksAvaible.setSlotXBoundsFromLeft(width / 2 - 4 - 200);
		guiPacksAvaible.registerScrollButtons(7, 8);
		guiPacksConnected = new GuiResourcePackSelected(mc, 200, height, packsConnected, !text);
		guiPacksConnected.setSlotXBoundsFromLeft(width / 2 + 4);
		guiPacksConnected.registerScrollButtons(7, 8);
		
		Iterator it = packsConnected.iterator();
		while(it.hasNext())
		{
			ResourcePackListEntry rawEntry = (ResourcePackListEntry) it.next();
			if(rawEntry instanceof ResourcePackListEntryFound)
			{
				ResourcePackRepository.Entry entry = ((ResourcePackListEntryFound) rawEntry).func_148318_i();
				TextureButton btn = getButton(entry.getResourcePackName());
				if(btn != null)
				{
					String packName = entry.getResourcePackName();
					packName = packName.substring(0, packName.length()-4);
					while(!packName.equals(btn.getPaths()[btn.getCurrent()]))
						Core.texture.update(btn.getButton().id);
				}
			}
		}
		
		if(Options.crazyButtons && Core.texture.getTextureButtons().get(0).getCurrent() == 0)
			Core.texture.update(1);
	}
	
	private TextureButton getButton(String path)
	{
		for(TextureButton btn : Core.texture.getTextureButtons())
		{
			List<String> paths = Lists.newArrayList();
			for(String btnPath : btn.getPaths())
				paths.add(btnPath+".zip");
			if(paths.contains(path))
				return btn;
		}
		return null;
	}
	
	public void handleMouseInput() throws IOException
	{
		super.handleMouseInput();
		guiPacksConnected.func_178039_p();
		guiPacksAvaible.func_178039_p();
	}
	
	public boolean hasResourcePackEntry(ResourcePackListEntry entry)
	{
		return packsConnected.contains(entry);
	}
	
	public List func_146962_b(ResourcePackListEntry entry)
	{
		return hasResourcePackEntry(entry) ? packsConnected : packsAvaible;
	}
	
	public List func_146964_g()
	{
		return packsAvaible;
	}
	
	public List func_146963_h()
	{
		return packsConnected;
	}
	
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if(button.id == 0)
		{
			if(somethingChanged)
			{	
				ArrayList var10 = Lists.newArrayList();
				Iterator var11 = packsConnected.iterator();
				
				while(var11.hasNext())
				{
					ResourcePackListEntry var13 = (ResourcePackListEntry) var11.next();
					
					if(var13 instanceof ResourcePackListEntryFound)
						var10.add(((ResourcePackListEntryFound) var13).func_148318_i());
				}
				
				Collections.reverse(var10);
				mc.getResourcePackRepository().func_148527_a(var10);
				mc.gameSettings.resourcePacks.clear();
				var11 = var10.iterator();
				
				while(var11.hasNext())
				{
					ResourcePackRepository.Entry var14 = (ResourcePackRepository.Entry) var11.next();
					mc.gameSettings.resourcePacks.add(var14.getResourcePackName());
				}
				
				mc.gameSettings.saveOptions();
				mc.refreshResources();
			}
			mc.displayGuiScreen(oldScreen);
		}
		
		if(button.id == 1)
		{
			TextureButton btn = Core.texture.update(1);
			Options.crazyButtons = btn.getCurrent() == 1;
			Core.config.set("crazybuttons", ""+Options.crazyButtons);
		}
		
		if(button.id == 15)
		{
			TextureButton btn = Core.texture.update(button.id);
			if(btn == null)
				return;
			if(btn.getCurrentPath() == null)
				TextureManager.stringCross = null;
			else
				TextureManager.stringCross = new ResourceLocation("textures/gui/"+(btn.getCurrentName().replace(" ", "_"))+".png");
		}
		
		if((button.id >= 10 && button.id != 15) && !text)
		{
			TextureButton btn = Core.texture.update(button.id);
			if(btn == null)
				return;
			String path = btn.getCurrentPath();
			String oldPath = btn.getOldPath();
			if(path == null)
				remove(oldPath);
			else
				add(path);
		}
	}
	
	private void add(String path)
	{
		ResourcePackListEntryFound pack = null;
		path += ".zip";
		Iterator it = packsAvaible.iterator();
		while(it.hasNext())
		{
			ResourcePackListEntryFound listEntry = (ResourcePackListEntryFound) it.next();
			ResourcePackRepository.Entry entry = listEntry.func_148318_i();
			if(entry.getResourcePackName().equals(path))
				pack = listEntry;
		}
		if(pack != null)
			pack.mousePressed(0, 0, 0, 0, 0, 0);
	}
	
	private void remove(String path)
	{
		ResourcePackListEntryFound pack = null;
		path += ".zip";
		Iterator it = packsConnected.iterator();
		while(it.hasNext())
		{
			ResourcePackListEntry rawListEntry = (ResourcePackListEntry) it.next();
			if(rawListEntry instanceof ResourcePackListEntryFound)
			{
				ResourcePackListEntryFound listEntry = (ResourcePackListEntryFound) rawListEntry;
				ResourcePackRepository.Entry entry = listEntry.func_148318_i();
				if(entry.getResourcePackName().equals(path))
					pack = listEntry;
			}
		}
		if(pack != null)
			pack.mousePressed(0, 0, 0, 0, 0, 0);
	}
	
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);
		if(text)
		{
			guiPacksAvaible.func_148179_a(mouseX, mouseY, mouseButton);
			guiPacksConnected.func_148179_a(mouseX, mouseY, mouseButton);
		}
	}
	
	protected void mouseReleased(int mouseX, int mouseY, int state)
	{
		super.mouseReleased(mouseX, mouseY, state);
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		if(text)
		{
			guiPacksAvaible.drawScreen(mouseX, mouseY, partialTicks);
			guiPacksConnected.drawScreen(mouseX, mouseY, partialTicks);
		}
		drawCenteredString(fontRendererObj, "Cose Textur Optionen", width / 2, 15, 0xFFFFFF);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	public void func_175288_g()
	{
		somethingChanged = true;
	}
	
}
