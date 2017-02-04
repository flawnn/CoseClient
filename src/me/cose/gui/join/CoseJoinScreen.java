package me.cose.gui.join;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import me.cose.config.Core;
import me.cose.config.InfoAccounts;
import me.cose.gui.join.utils.CosePlayerList;
import me.cose.gui.join.utils.PlayerDataInfo;
import me.cose.gui.options.CoseOptionsAccountMenu;
import me.cose.utils.texture.SkinType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.multiplayer.GuiConnecting;

public class CoseJoinScreen extends GuiScreen {
	
	private GuiScreen oldScreen;
	private String hoveringText;
	
	private GuiButton btnSwitchSkin;
	private GuiButton btnAdd;
	private GuiButton btnRemove;
	private GuiButton btnJoin;
	private GuiButton btnReset;
	private GuiButton btnAccount;
	
	private PlayersList list;
	private List<String> noRemove;
	
	public CoseJoinScreen(GuiScreen screen)
	{
		oldScreen = screen;
		hoveringText = null;
	}
	
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
		buttonList.clear();
		
		noRemove = new ArrayList<String>();
		InfoAccounts in = Core.accounts;
		for(int i = 0; i <= in.getMax(); i ++)
			noRemove.add(in.getData(i)[1]);
		
		list = new PlayersList(mc, width, height, 30, height - 40, 36);
		List<PlayerDataInfo> players = new ArrayList<PlayerDataInfo>();
		List<String> names = CosePlayerList.getNames();
		for(int i = 0; i <= in.getMax(); i ++)
			if(!names.contains(in.getData(i)[1]))
			{
				names.add(in.getData(i)[1]);
			}
		
		String username = mc.getSession().getUsername();
		
		if(names.contains(username))
			names.remove(username);
		players.add(new PlayerDataInfo(this, username));
		noRemove.add(username);
		
		int delay = 13;
		if(Core.premiumMode)
			delay = -1;
		for(String name : names)
			if(delay != 0)
			{
				players.add(new PlayerDataInfo(this, name));
				delay --;
			}
		list.loadElements(players);
		
		String text = "Skin Modus: "+Core.head.type.getName();
		int l = fontRendererObj.getStringWidth(text);
		btnSwitchSkin = new GuiButton(11, width - 10 - l, 2, l + 8, 20, text);
		btnAdd = new GuiButton(0, 10, height - 30, 80, 20, "Hinzufügen");
		btnRemove = new GuiButton(1, 100, height - 30, 80, 20, "Entfernen");
		btnJoin = new GuiButton(2, width - 90, height - 30, 80, 20, "Nachjoinen");
		btnAccount = new GuiButton(3, 2, 2, fontRendererObj.getStringWidth("Account Einstellungen") + 8, 20, "Account Einstellungen");
		
		btnRemove.enabled = false;
		btnJoin.enabled = false;
		
		buttonList.add(btnSwitchSkin);
		buttonList.add(btnAdd);
		buttonList.add(btnRemove);
		buttonList.add(btnJoin);
		buttonList.add(btnAccount);
	}
	
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}
	
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if(button.id == 3)
		{
			mc.displayGuiScreen(new CoseOptionsAccountMenu(this));
		}
		
		if(button.id == 0)
		{
			mc.displayGuiScreen(new CoseAddMenu(this));
		}
		
		if(button.id == 1)
		{
			PlayerDataInfo info = get();
			List<String> names = CosePlayerList.getNames();
			if(names.contains(info.getName()))
			{
				names.remove(info.getName());
				CosePlayerList.setNames(names);
			}
			List<PlayerDataInfo> infos = getList();
			if(infos.contains(info))
			{
				infos.remove(info);
				setList(infos);
				mc.displayGuiScreen(new CoseJoinScreen(oldScreen));
			}
		}
		
		if(button.id == 2)
		{
			joinSelectedServer();
		}
		
		if(button.id == 11)
		{
			SkinType next = Core.head.type.next();
			Core.head.type = next;
			String text = "Skin Modus: "+next.getName();
			btnSwitchSkin.displayString = text;
			mc.displayGuiScreen(new CoseJoinScreen(oldScreen));
		}
	}
	
	public PlayerDataInfo get()
	{
		GuiListExtended.IGuiListEntry listEntry = list.selectedSlotIndex < 0 ? null : list.getListEntry(list.selectedSlotIndex);
		if (listEntry instanceof PlayerDataInfo)
			return (PlayerDataInfo) listEntry;
		return null;
	}
	
	public void joinSelectedServer()
	{
		PlayerDataInfo info = get();
		if(info.isOnline())
		{
			int port = 25565;
			try
			{
				port = Integer.valueOf(info.getServer().split(":")[1]);
			}
			catch(Exception err)
			{
				port = 25565;
			}
			this.mc.displayGuiScreen(new GuiConnecting(this, this.mc, info.getServer().split(":")[0], port));
		}
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		hoveringText = null;
		
		drawDefaultBackground();
		list.drawScreen(mouseX, mouseY, partialTicks);
		drawCenteredString(fontRendererObj, "§lFreundesListe - Nachjoinen", this.width / 2, 10, 0xFFFFFF);
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		String text = "§8Server >> "+(Core.connection.isConnected() ? "§2Online" : "§4Offline");
		int t = fontRendererObj.getStringWidth(text);
		drawString(fontRendererObj, text, width - 98 - t, height - 23, 0xFFFFFF);
		
		if(hoveringText != null)
			drawHoveringText(Lists.newArrayList(Splitter.on("\n").split(this.hoveringText)), mouseX, mouseY);
	}
	
	public void handleMouseInput() throws IOException
	{
		super.handleMouseInput();
		list.func_178039_p();
	}
	
	protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);
		list.func_148179_a(mouseX, mouseY, mouseButton);
	}
	
	protected void mouseReleased(int mouseX, int mouseY, int state)
	{
		super.mouseReleased(mouseX, mouseY, state);
		list.func_148181_b(mouseX, mouseY, state);
	}
	
	public int getCountedItems()
	{
		return list.getCountedItems();
	}
	
	public void select(int index)
	{
		list.setSelectedSlotIndex(index);
		GuiListExtended.IGuiListEntry listEntry = index < 0 ? null : list.getListEntry(index);
		
		btnRemove.enabled = false;
		btnJoin.enabled = false;

		if(listEntry != null)
		{
			PlayerDataInfo info = get();
			if(!noRemove.contains(info.getName()))
				btnRemove.enabled = true;
			if(info.isOnline())
				btnJoin.enabled = true;
		}
	}
	
	public List<PlayerDataInfo> getList()
	{
		return list.getList();
	}
	
	public void setList(List<PlayerDataInfo> listIn)
	{
		list.setList(listIn);
		System.out.println("6");
	}
	
	public class PlayersList extends GuiListExtended {
		
		private List<PlayerDataInfo> list = new ArrayList<PlayerDataInfo>();
		private GuiListExtended.IGuiListEntry noEntry;
		public int selectedSlotIndex = -1;
		
		public PlayersList(Minecraft mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn)
		{
			super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
			noEntry = new NoEntry();
		}

		public List<PlayerDataInfo> getList()
		{
			return list;
		}
		
		public void setList(List<PlayerDataInfo> listIn)
		{
			list = listIn;
		}
		
		public void setSelectedSlotIndex(int index)
		{
			selectedSlotIndex = index;
		}
		
		protected boolean isSelected(int slotIndex)
		{
			return slotIndex == selectedSlotIndex;
		}
		
		public GuiListExtended.IGuiListEntry getListEntry(int index)
	    {
	        if(index < list.size())
	            return (GuiListExtended.IGuiListEntry) list.get(index);
	        return noEntry;
	    }
		
		protected int getSize()
		{
			return list.size() + 1;
		}
		
		public void loadElements(List<PlayerDataInfo> listIn)
		{
			list.clear();
			
			for(PlayerDataInfo info : listIn)
				list.add(info);
		}
		
		public int getCountedItems()
		{
			return list.size()-1;
		}
		
		public class NoEntry implements GuiListExtended.IGuiListEntry {
			
			private final Minecraft mc = Minecraft.getMinecraft();
			private int delay = 0;
			private int multi = 1;
			private String s;
			
			public void setSelected(int p_178011_1_, int p_178011_2_, int p_178011_3_) {}
			
			public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected) {}
			
			public boolean mousePressed(int slotIndex, int p_148278_2_, int p_148278_3_, int p_148278_4_, int p_148278_5_, int p_148278_6_)
			{
				return false;
			}
			
			public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {}

			public boolean isTextureMode()
			{
				return false;
			}
			
		}
		
	}
	
}
