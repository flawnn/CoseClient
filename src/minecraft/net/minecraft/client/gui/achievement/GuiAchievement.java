package net.minecraft.client.gui.achievement;

import java.awt.image.BufferedImage;
import java.util.List;

import com.google.common.collect.Lists;

import me.cose.utils.DataPackage;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ResourceLocation;

public class GuiAchievement extends Gui
{
    private static final ResourceLocation achievementBg = new ResourceLocation("textures/gui/achievement/achievement_background.png");
    private Minecraft mc;
    private int width;
    private int height;
    private String achievementTitle;
    private String achievementDescription;
    private Achievement theAchievement;
    private ItemStack item;
    private boolean always;
    private List<DataPackage> list;
    private long notificationTime;
    private RenderItem renderItem;
    private boolean permanentNotification;
    private BufferedImage ownImageIcon;
    private static final String __OBFID = "CL_00000721";
    
    public GuiAchievement(Minecraft mc)
    {
        this.mc = mc;
        this.renderItem = mc.getRenderItem();
        list = Lists.newArrayList();
    }
    
    public static GuiAchievement getInstance()
    {
    	return Minecraft.getMinecraft().guiAchievement;
    }
    
    public void displayAchievement(String title, String description, BufferedImage image , boolean always, boolean wait)
    {
    	if(this.notificationTime != 0 && wait)
    	{
    		DataPackage data = new DataPackage();
    		data.setStrings(new String[] {title, description});
    		data.setItems(null);
    		data.setImages(new BufferedImage[] {image});
    		data.setBooleans(new boolean[] {always});
    		list.add(data);
    	}
    	else
    	{
	        this.achievementTitle = title;
	        this.achievementDescription = description;
    		this.notificationTime = Minecraft.getSystemTime();
	        this.theAchievement = null;
	        this.item = null;
	        this.ownImageIcon = image;
	        this.permanentNotification = false;
	        this.always = always;
    	}
    }
    
    public void displayAchievement(String title, String description, Item item, boolean always, boolean wait)
    {
    	if(this.notificationTime != 0 && wait)
    	{
    		DataPackage data = new DataPackage();
    		data.setStrings(new String[] {title, description});
    		data.setItems(new ItemStack[] {new ItemStack(item)});
    		data.setBooleans(new boolean[] {always});
    		list.add(data);
    	}
    	else
    	{
	        this.achievementTitle = title;
	        this.achievementDescription = description;
    		this.notificationTime = Minecraft.getSystemTime();
	        this.theAchievement = null;
	        this.item = new ItemStack(item);
	        this.permanentNotification = false;
	        this.always = always;
	        this.ownImageIcon = null;
    	}
    }
    
    public void displayAchievement(String title, String description, Block item, boolean always, boolean wait)
    {
    	if(this.notificationTime != 0 && wait)
    	{
    		DataPackage data = new DataPackage();
    		data.setStrings(new String[] {title, description});
    		data.setItems(new ItemStack[] {new ItemStack(item)});
    		data.setBooleans(new boolean[] {always});
    		list.add(data);
    	}
    	else
    	{
	        this.achievementTitle = title;
	        this.achievementDescription = description;
	        this.notificationTime = Minecraft.getSystemTime();
	        this.theAchievement = null;
	        this.item = new ItemStack(item);
	        this.permanentNotification = false;
	        this.always = always;
	        this.ownImageIcon = null;
    	}
    }
    
    public void displayAchievement(Achievement p_146256_1_)
    {
        this.achievementTitle = I18n.format("achievement.get", new Object[0]);
        this.achievementDescription = p_146256_1_.getStatName().getUnformattedText();
        this.notificationTime = Minecraft.getSystemTime();
        this.theAchievement = p_146256_1_;
        this.item = null;
        this.permanentNotification = false;
        this.always = false;
        this.ownImageIcon = null;
    }

    public void displayUnformattedAchievement(Achievement p_146255_1_)
    {
        this.achievementTitle = p_146255_1_.getStatName().getUnformattedText();
        this.achievementDescription = p_146255_1_.getDescription();
        this.notificationTime = Minecraft.getSystemTime() + 2500L;
        this.theAchievement = null;
        this.item = null;
        this.permanentNotification = true;
        this.always = false;
        this.ownImageIcon = null;
    }

    private void updateAchievementWindowScale()
    {
        GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        this.width = this.mc.displayWidth;
        this.height = this.mc.displayHeight;
        ScaledResolution var1 = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        this.width = var1.getScaledWidth();
        this.height = var1.getScaledHeight();
        GlStateManager.clear(256);
        GlStateManager.matrixMode(5889);
        GlStateManager.loadIdentity();
        GlStateManager.ortho(0.0D, (double)this.width, (double)this.height, 0.0D, 1000.0D, 3000.0D);
        GlStateManager.matrixMode(5888);
        GlStateManager.loadIdentity();
        GlStateManager.translate(0.0F, 0.0F, -2000.0F);
    }

    public void updateAchievementWindow()
    {
        if ((this.theAchievement != null || this.item != null) && this.notificationTime != 0L && (Minecraft.getMinecraft().thePlayer != null || always))
        {
            double var1 = (double)(Minecraft.getSystemTime() - this.notificationTime) / 3000.0D;

            if (!this.permanentNotification)
            {
                if (var1 < 0.0D || var1 > 1.0D)
                {
                    this.notificationTime = 0L;
                    return;
                }
            }
            else if (var1 > 0.5D)
            {
                var1 = 0.5D;
            }

            this.updateAchievementWindowScale();
            GlStateManager.disableDepth();
            GlStateManager.depthMask(false);
            double var3 = var1 * 2.0D;

            if (var3 > 1.0D)
            {
                var3 = 2.0D - var3;
            }

            var3 *= 4.0D;
            var3 = 1.0D - var3;

            if (var3 < 0.0D)
            {
                var3 = 0.0D;
            }

            var3 *= var3;
            var3 *= var3;
            int var5 = this.width - 160;
            int var6 = 0 - (int)(var3 * 36.0D);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            GlStateManager.func_179098_w();
            this.mc.getTextureManager().bindTexture(achievementBg);
            GlStateManager.disableLighting();
            this.drawTexturedModalRect(var5, var6, 96, 202, 160, 32);

            if (this.permanentNotification)
            {
                this.mc.fontRendererObj.drawSplitString(this.achievementDescription, var5 + 30, var6 + 7, 120, -1);
            }
            else
            {
                this.mc.fontRendererObj.drawString(this.achievementTitle, var5 + 30, var6 + 7, -256);
                this.mc.fontRendererObj.drawString(this.achievementDescription, var5 + 30, var6 + 18, -1);
            }

            RenderHelper.enableGUIStandardItemLighting();
            GlStateManager.disableLighting();
            GlStateManager.enableRescaleNormal();
            GlStateManager.enableColorMaterial();
            GlStateManager.enableLighting();
            if (this.theAchievement != null)
            	this.renderItem.func_180450_b(this.theAchievement.theItemStack, var5 + 8, var6 + 8);
            else if (this.item != null)
            	this.renderItem.func_180450_b(this.item, var5 + 8, var6 + 8);
            GlStateManager.disableLighting();
            GlStateManager.depthMask(true);
            GlStateManager.enableDepth();
        }
        if (this.notificationTime == 0 && !list.isEmpty())
        {
        	DataPackage data = list.get(0);
        	list.remove(data);
        	String title = data.getStrings()[0];
        	String description = data.getStrings()[1];
        	boolean always = data.getBooleans()[0];
        	if(data.getItems() == null)
        	{
        		BufferedImage image = data.getImages()[0];
        		displayAchievement(title, description, image, always, false);
        	}
        	else
        	{
	        	Item item = data.getItems()[0].getItem();
	        	displayAchievement(title, description, item, always, false);
        	}
        }
    }

    public void clearAchievements()
    {
        this.theAchievement = null;
        this.notificationTime = 0L;
    }
}
