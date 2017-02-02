package me.cose.gui.info;

import java.io.IOException;

import org.lwjgl.input.Keyboard;

import me.cose.gui.info.utils.Renderer;
import me.cose.gui.info.utils.ShowInfos;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.GuiScreen;

public class GuiInfoOptions extends GuiScreen {
	
	private static boolean isNormalBold = true;
	private static boolean isNormalRainbow = true;
	private static boolean isNormalUnderlined = true;
	private static boolean isNormalCursive = true;
	private static boolean isNormal2 = false;
	private GuiScreen oldScreen;
	
	private GuiButton btnBold;
	private GuiButton btnCursive;
	private GuiButton btnUnderlined;
	private GuiButton btnRainbow;
	private GuiButton btnNormal;
	private GuiButton btnSwitcher;
	
	public GuiInfoOptions(GuiIngameMenu screen) {
		oldScreen = screen;
	}
	
	public void initGui()
	{
		Keyboard.enableRepeatEvents(true);
		buttonList.clear();
		
		btnBold = new GuiButton(0, width / 2 + 4, height / 2 - 94, 75, 20, "");
		btnCursive = new GuiButton(1, width / 2 - 82, height / 2 - 94, 75, 20, "");
		btnRainbow = new GuiButton(2, width / 2 + 90, height / 2 - 94, 75, 20, "");
		btnUnderlined = new GuiButton(3, width / 2 - 168 , height / 2 - 94, 75, 20, "");
		btnSwitcher = new GuiButton(8, 5, 5, 70, 20, "");
	    buttonList.add(btnSwitcher);
		
		//btnNormal = new GuiButton(4, width / 2  , height / 2 - 65, 75, 20, "Normal");
		
		buttonList.add(btnBold);
		buttonList.add(btnCursive);
		//buttonList.add(btnNormal);
		
		buttonList.add(btnRainbow);
		buttonList.add(btnUnderlined);
		
		buttonStateChange();
	}
	public void onGuiClosed()
	{
		Keyboard.enableRepeatEvents(false);
	}
	
	public void drawScreen(int mouseX, int mouseY, float partialTicks)
	{
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTicks);
		
		drawCenteredString(fontRendererObj, "§l§nOptionen für die Infos", width / 2 , height / 2 - 110, 0xFFFFFF);
	}
	
	 private void buttonStateChange()
	    {
		 	btnSwitcher.displayString = isNormal2 ? "Option Menu" : "Vanilla Menu";
	    	btnBold.displayString = isNormalBold ? "§lBold§r: Off" : "§lBold§r: On";
	    	btnRainbow.displayString = isNormalRainbow ? "Rainbow: Off" : "Rainbow: On";
	    	btnUnderlined.displayString = isNormalUnderlined ? "§oUnderlined§r: Off" : "§oUnderlined§r: On";
	    	btnCursive.displayString = isNormalCursive ? "§nCursive§r: Off" : "§nCursive§r: On";
	    
	    }
	protected void actionPerformed(GuiButton button) throws IOException
	{
		switch (button.id) {
			case 0:
				if(isNormalBold){
					Renderer.setCurrent(1);
					ShowInfos.renderClock();
					isNormalBold = false;
					break;
				} else {
				Renderer.setCurrent(0);
				ShowInfos.renderClock();
				isNormalBold = true;
				break;
				}
			case 1:
				if(isNormalCursive){
					Renderer.setCurrent(3);
					ShowInfos.renderClock();
					isNormalCursive = false;
					break;
				} else {
				Renderer.setCurrent(0);
				ShowInfos.renderClock();
				isNormalCursive = true;
				break;
				}
			case 2:
				if(isNormalRainbow){
					Renderer.rainbow = true;
					ShowInfos.renderClock();
					isNormalRainbow = false;
					break;
				} else {
				Renderer.rainbow = false;
				ShowInfos.renderClock();
				isNormalRainbow = true;
				break;
				}	
			case 3:
				if(isNormalUnderlined){
					Renderer.setCurrent(2);
					ShowInfos.renderClock();
					isNormalUnderlined = false;
					break;
				} else {
				Renderer.setCurrent(0);
				ShowInfos.renderClock();
				isNormalUnderlined = true;
				break;
				}
			 case 8:
	      
	            	this.mc.displayGuiScreen(new GuiIngameMenu());
	            	isNormal2 = true;
	            	buttonStateChange();
	            	break;
			/*case 4:
				Renderer.setCurrent(0);
				Renderer.rainbow = false;
				ShowInfos.renderClock();
				break;*/
		}
		buttonStateChange();
	}
	

}
