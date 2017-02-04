package net.minecraft.client.gui;

import java.io.IOException;

import me.cose.config.Core;
import me.cose.config.Options;
import me.cose.gui.info.GuiInfoOptions;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.resources.I18n;

public class GuiIngameMenu extends GuiScreen
{
	
	private static boolean isNormal = true;
	private static boolean isNormal2 = true;
    private int field_146445_a;
    private int field_146444_f;
    private static final String __OBFID = "CL_00000703";
    private boolean quitAllow = true;
    
    private GuiButton btnSwitcher;
    
    private GuiButton btnReturnToMenu;
    private GuiButton btnReturnToGame;
    private GuiButton btnOptions;
    private GuiButton btnShareToLan;
    private GuiButton btnAchievements;
    private GuiButton btnStats;
    
    /**
     * Adds the buttons (and other controls) to the screen in question.
     */
    public void initGui()
    {
    	this.buttonList.clear();
    	
        this.field_146445_a = 0;
        byte var1 = -16;
        boolean var2 = true;
        this.buttonList.add(btnReturnToMenu = new GuiButton(1, this.width / 2 - 100, this.height / 4 + 120 + var1, I18n.format("menu.returnToMenu", new Object[0])));
        
        btnSwitcher = new GuiButton(8, 5, 5, 70, 20, "");
        buttonList.add(btnSwitcher);
        
        if (!this.mc.isIntegratedServerRunning())
        {
        	((GuiButton)this.buttonList.get(0)).displayString = I18n.format("menu.disconnect", new Object[0]);
        	quitAllow = false;
        }
        
        this.buttonList.add(btnReturnToGame = new GuiButton(4, this.width / 2 - 100, this.height / 4 + 24 + var1, I18n.format("menu.returnToGame", new Object[0])));
        this.buttonList.add(btnOptions = new GuiButton(0, this.width / 2 - 100, this.height / 4 + 96 + var1, 98, 20, I18n.format("menu.options", new Object[0])));
        GuiButton var3;
        this.buttonList.add(btnShareToLan = var3 = new GuiButton(7, this.width / 2 + 2, this.height / 4 + 96 + var1, 98, 20, I18n.format("menu.shareToLan", new Object[0])));
        this.buttonList.add(btnAchievements = new GuiButton(5, this.width / 2 - 100, this.height / 4 + 48 + var1, 98, 20, I18n.format("gui.achievements", new Object[0])));
        this.buttonList.add(btnStats = new GuiButton(6, this.width / 2 + 2, this.height / 4 + 48 + var1, 98, 20, I18n.format("gui.stats", new Object[0])));
        var3.enabled = this.mc.isSingleplayer() && !this.mc.getIntegratedServer().getPublic();
        
        buttonStateChange();
    }
    
    private void buttonStateChange()
    {
    	btnSwitcher.displayString = isNormal2 ? "Option Menu" : "Vanilla Menu";
    	
    	btnReturnToMenu.visible = isNormal;
    	btnReturnToMenu.enabled = isNormal;
    	btnReturnToGame.visible = isNormal;
    	btnReturnToGame.enabled = isNormal;
    	btnOptions.visible = isNormal;
    	btnOptions.enabled = isNormal;
    	btnShareToLan.visible = isNormal;
    	btnShareToLan.enabled = isNormal;
    	btnAchievements.visible = isNormal;
    	btnAchievements.enabled = isNormal;
    	btnStats.visible = isNormal;
    	btnStats.enabled = isNormal;
    }

    protected void actionPerformed(GuiButton button) throws IOException
    {
        switch (button.id)
        {
            case 0:
                this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                break;

            case 1:
            	if(quitAllow)
            	{
	                button.enabled = false;
	                this.mc.theWorld.sendQuittingDisconnectingPacket();
	                this.mc.loadWorld((WorldClient)null);
	                this.mc.displayGuiScreen(new GuiMainMenu());
            	}
            	else
            	{
            		quitAllow = true;
            		button.displayString = "�c" + I18n.format("menu.disconnect", new Object[0]);;
            	}
            case 2:
            case 3:
            default:
                break;

            case 4:
                this.mc.displayGuiScreen((GuiScreen)null);
                this.mc.setIngameFocus();
                break;

            case 5:
                this.mc.displayGuiScreen(new GuiAchievements(this, this.mc.thePlayer.getStatFileWriter()));
                break;

            case 6:
                this.mc.displayGuiScreen(new GuiStats(this, this.mc.thePlayer.getStatFileWriter()));
                break;

            case 7:
                this.mc.displayGuiScreen(new GuiShareToLan(this));
                break;
                
            case 8:
            	this.mc.displayGuiScreen(new GuiInfoOptions(this));
            	isNormal2 = false;
            	buttonStateChange();
            	
            	break;
        }
    }

    /**
     * Called from the main game loop to update the screen.
     */
    public void updateScreen()
    {
        super.updateScreen();
        ++this.field_146444_f;
    }

    /**
     * Draws the screen and all the components in it. Args : mouseX, mouseY, renderPartialTicks
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        if(isNormal)
        	this.drawCenteredString(this.fontRendererObj, I18n.format("menu.game", new Object[0]), this.width / 2, 40, 16777215);
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
}
