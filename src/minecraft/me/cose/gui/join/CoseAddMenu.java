package me.cose.gui.join;

import java.io.IOException;
import java.util.List;

import org.lwjgl.input.Keyboard;

import me.cose.gui.join.utils.CosePlayerList;
import me.cose.gui.join.utils.PlayerDataInfo;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class CoseAddMenu extends GuiScreen {
	
	private CoseJoinScreen oldScreen;
	
	private GuiTextField fieldName;
	
	private GuiButton btnAdd;
	private GuiButton btnCancel;
	
	public CoseAddMenu(CoseJoinScreen screen)
	{
		oldScreen = screen;
	}
	
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
		buttonList.clear();
		
		fieldName = new GuiTextField(0, fontRendererObj, width / 2 - 100, height / 2 - 10, 200, 15);
		
		btnAdd = new GuiButton(0, width / 2 + 4, height / 2 + 10, 100, 20, "Hinzufügen");
		btnCancel = new GuiButton(1, width / 2 - 100 - 4, height / 2 + 10, 100, 20, "Abbrechen");
		
		buttonList.add(btnAdd);
		buttonList.add(btnCancel);
	}
	
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}
	
	protected void keyTyped(char typedChar, int keyCode) throws IOException
	{
//		super.keyTyped(typedChar, keyCode);
		fieldName.textboxKeyTyped(typedChar, keyCode);
	}
	
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException
	{
		super.mouseClicked(mouseX, mouseY, mouseButton);
		fieldName.mouseClicked(mouseX, mouseY, mouseButton);
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		fieldName.drawTextBox();
		
		drawCenteredString(fontRendererObj, "§lFreundesListe - Nachjoinen", this.width / 2, 10, 0xFFFFFF);
		drawCenteredString(fontRendererObj, "§7§lSpielername:", width / 2 - 65, height / 2 - 25, 0xFFFFFF);
	}
	
	protected void actionPerformed(GuiButton button) throws IOException
	{
		if(button.id == 0)
		{
			String name = fieldName.getText();
			System.out.println("0");
			if(name != null)
				if(!name.equals(""))
				{
					System.out.println("1");
					List<String> list = CosePlayerList.getNames();
					if(!list.contains(name))
					{
						System.out.println("2");
						list.add(name);
						CosePlayerList.setNames(list);
						List<PlayerDataInfo> infos = oldScreen.getList();
						System.out.println("5");
						infos.add(new PlayerDataInfo(oldScreen, name));
						oldScreen.setList(infos);
					}
				}
			System.out.println("7");
			mc.displayGuiScreen(oldScreen);
		}
		if(button.id == 1)
		{
			mc.displayGuiScreen(oldScreen);
		}
	}
	
}
