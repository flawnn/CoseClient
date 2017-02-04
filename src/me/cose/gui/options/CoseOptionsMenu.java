package me.cose.gui.options;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import me.cose.gui.options.texture.GuiScreenTexturesPacks;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;

public class CoseOptionsMenu extends GuiScreen {
	
	private GuiScreen oldScreen;
	
	private GuiButton btnAccount;
	private GuiButton btnTexture;
	private GuiButton btnBack;
	
	public CoseOptionsMenu(GuiScreen screen)
	{
		oldScreen = screen;
	}
	
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
		buttonList.clear();
		
		btnAccount = new GuiButton(0, width / 2 - 85, height / 3, 170, 20, "Account");
		btnTexture = new GuiButton(1, width / 2 - 85, height / 2, 170, 20, "Texturen");
		btnBack = new GuiButton(2, width / 2 - 100, height / 6 + 168, 200, 20, "Zurück");
		
		buttonList.add(btnAccount);
		buttonList.add(btnTexture);
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
		this.drawCenteredString(this.fontRendererObj, "Cose Optionen", this.width / 2, 15, 0xFFFFFF);
		super.drawScreen(mouseX, mouseY, partialTicks);
	}
	
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if(button.id == btnAccount.id)
		{
			mc.displayGuiScreen(new CoseOptionsAccountMenu(this));
		}
		
		if(button.id == btnTexture.id)
		{
			mc.displayGuiScreen(new GuiScreenTexturesPacks(this));
		}
		
		if(button.id == btnBack.id)
		{
			mc.displayGuiScreen(oldScreen);
		}
	}
	
}
