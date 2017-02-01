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

public class SkinScarfRed extends Skin {
	
	public SkinScarfRed()
	{
		super(createItem(Blocks.wool, 14), CoseItemSlot.SCARF, "textures/blocks/wool_colored_red.png");
		buttonId = -1;
	}
	
	public void doRenderLayer(EntityLivingBase entity, float a, float b, float c, float d, float e, float f, float g)
	{
		boolean ask = false;
		if(SkinManager.currentScarf != null)
			if(slot.equals(CoseItemSlot.SCARF) && SkinManager.currentScarf.secretId == secretId)
				ask = true;
		if(ask)
			if(shallRender(entity))
			{
				Minecraft.getMinecraft().getTextureManager().bindTexture(texturePath);
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
		renderer = new ModelRenderer(modelPlayer, "scarf");
		renderer.setTextureSize(16, 16);
		renderer.addBox(-3F, -0.9F, -3.5F, 6, 1, 7);
		renderer.addBox(-3.5F, -0.9F, -3F, 7, 1, 6);
		renderer.addBox(-3F, -0.1F, -4F, 6, 1, 8);
		renderer.addBox(-4F, -0.1F, -3F, 8, 1, 6);
		renderer.addBox(-2.5F, 0F, -2.75F, 2.5F, 7F, 1F);
	}
	
}
