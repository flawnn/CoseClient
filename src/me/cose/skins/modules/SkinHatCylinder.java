package me.cose.skins.modules;

import me.cose.skins.Skin;
import me.cose.skins.SkinManager;
import me.cose.skins.gui.CoseItem.CoseItemSlot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;

public class SkinHatCylinder extends Skin {
	
	public SkinHatCylinder()
	{
		super(createItem(Blocks.wool, 15), CoseItemSlot.HAT, "textures/blocks/obsidian.png");
		buttonId = -1;
	}
	
	public void doRenderLayer(EntityLivingBase entity, float a, float b, float c, float d, float e, float f, float g)
	{
		boolean ask = false;
		if(SkinManager.currentHat != null)
			if(slot.equals(CoseItemSlot.HAT) && SkinManager.currentHat.secretId == secretId)
				ask = true;
		if(ask)
			if(shallRender(entity))
			{
				Minecraft.getMinecraft().getTextureManager().bindTexture(texturePath);
				if(entity.isSneaking())
					renderer.offsetY = 0.2F;
				else
					renderer.offsetY = 0;
				GlStateManager.pushMatrix();
				renderer.render(0.0625F);
				GlStateManager.popMatrix();
			}
	}
	
	public boolean shallRender(EntityLivingBase entity)
	{
		if(entity.getName().equals(Minecraft.getMinecraft().getSession().getUsername()))
			return true;
		return false;
	}
	
	public void loadModel(ModelPlayer modelPlayer, float unkown)
	{
		renderer = new ModelRenderer(modelPlayer, "hat");
		renderer.setTextureSize(16, 16);
		renderer.setRotationPoint(0.0F, 0.0F, 0.0F);
		renderer.addBox(-4, -15, -4, 8, 7, 8);
		renderer.addBox(-5F, -8.6F, -5F, 10, 2, 10);
	}
	
}
