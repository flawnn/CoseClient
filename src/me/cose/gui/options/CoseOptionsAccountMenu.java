package me.cose.gui.options;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import me.cose.config.Core;
import me.cose.config.Options;
import me.cose.gui.options.texture.GuiPremiumScreen;
import me.cose.skins.gui.CoseInventoryScreen;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

public class CoseOptionsAccountMenu extends GuiScreen {
	
	private GuiScreen oldScreen;
	
	private GuiButton btnPremium;
	private GuiButton btnInventory;
	private GuiButton btnBlockChat;
	private GuiButton btnStatus;
	private GuiButton btnBack;
	
	public CoseOptionsAccountMenu(GuiScreen screen)
	{
		oldScreen = screen;
	}
	
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
		buttonList.clear();
		
		btnPremium = new GuiButton(0, width / 4 - 85, height / 5, 170, 20, Core.minecraftPremium);
		btnInventory = new GuiButton(1, width / 4 * 3 - 85, height / 5, 170, 20, "Cose Inventar");
		
		btnBlockChat = new GuiButton(2, width / 2 - 85, height / 3 + 25, 170, 20, "Chat: "+(Options.blockChat ? "Geblockt" : "Erlaubt"));
		btnStatus = new GuiButton(3, width / 2 - 85, height / 3, 170, 20, "Status: "+(Options.online ? "Online" : "Offline"));
		btnBack = new GuiButton(5, width / 2 - 100, height / 6 + 168, 200, 20, "Zurück");
		
		buttonList.add(btnPremium);
		buttonList.add(btnInventory);
		buttonList.add(btnBlockChat);
		buttonList.add(btnStatus);
		buttonList.add(btnBack);
	}
	
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
		if(keyCode == Keyboard.KEY_ESCAPE)
		{
			mc.displayGuiScreen(oldScreen);
		}
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		this.drawCenteredString(this.fontRendererObj, "Cose Account Optionen", this.width / 2, 15, 0xFFFFFF);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if(button.id == btnPremium.id)
		{
			this.mc.displayGuiScreen(new GuiPremiumScreen(this));
		}
		
		if(button.id == btnInventory.id)
		{
			this.mc.displayGuiScreen(new CoseInventoryScreen(this));
		}
		
		if(button.id == btnBlockChat.id)
		{
			if(Options.blockChat)
				Options.blockChat = false;
			else
				Options.blockChat = true;
			btnBlockChat.displayString = "Chat: "+(Options.blockChat ? "Geblockt" : "Erlaubt");
		}
		
		if(button.id == btnStatus.id)
		{
			boolean newStatus = (Options.online ? false : true);
//			Options.online = newStatus;
			Core.connection.setMyOnlineStatus(newStatus);
			Options.online = Core.connection.isPlayerOnline(mc.getSession().getUsername());
			btnStatus.displayString = "Status: "+(Options.online ? "Online" : "Offline");
		}
		
		if(button.id == btnBack.id)
		{
			mc.displayGuiScreen(oldScreen);
		}
	}
	
}
