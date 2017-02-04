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

public class SkinOtherFinalWings extends Skin {
	
	private ModelRenderer wing;
	private ModelRenderer wingTip;
	private float offsetWingY;
	private float offsetWingZ;
	private float time = 0;
	private long lastMS = 0;
	
	public SkinOtherFinalWings()
	{
		super(createItem(Blocks.dragon_egg), CoseItemSlot.OTHER, "textures/entity/enderdragon/dragon.png", false, true, true, true, false, true);
		buttonId = -1;
	}
	
	public void doRenderLayer(EntityLivingBase entity, float a, float b, float c, float d, float e, float f, float g)
	{
		boolean ask = false;
		if(SkinManager.currentOther != null)
			if(slot.equals(CoseItemSlot.OTHER) && SkinManager.currentOther.secretId == secretId)
				ask = true;
		if(ask)
			if(shallRender(entity))
			{
				Minecraft.getMinecraft().getTextureManager().bindTexture(texturePath);
				if(entity.isSneaking())
				{
					renderer.offsetY = 0.35F;
					renderer.rotateAngleX = 0.75F;
					renderer.offsetZ = 0.05F;
				}
				else
				{
					renderer.offsetY = offsetWingY;
					renderer.offsetZ = 0;
					renderer.rotateAngleX = 0.0F;
				}
				timer(entity);
				render();
			}
	}
	
	private void timer(EntityLivingBase entity)
	{
		long currentMS = System.currentTimeMillis();
		long distance = currentMS - lastMS;
		if(distance >= 100 / 6)
		{
			time += 0.025;
			if(entity.posX != entity.prevPosX || entity.posY != entity.prevPosY || entity.posZ != entity.prevPosZ)
				time += 0.075;
			lastMS = currentMS;
		}
	}
	
	private void render()
    {
		GlStateManager.pushMatrix();
		float mathPi = (float) Math.PI * 2 + (float) Math.sin(time) / 2 + 0.6F;
		float mathPi_ = (float) Math.PI * 2 + (float) Math.sin(time);
		wing.rotateAngleX = -0.725F - (float) Math.cos((double) mathPi) * 0.2F;
		wing.rotateAngleZ = 0.25F - (float) Math.cos(time) * 0.25F;
		wing.rotateAngleY = (float) (Math.sin((double) mathPi) + 0.125D) * 0.8F;
		wingTip.rotateAngleZ = -((float) (Math.sin((double) (mathPi_ * 1.2 + 2.0F)) + 0.5D)) * 0.75F;
		renderer.render(0.0625F);
		wing.offsetX = 0.1875F;
		wing.rotateAngleX *= -1;
		wing.rotateAngleZ = (float) Math.PI - wing.rotateAngleZ;
		wingTip.rotateAngleZ *= -1;
		renderer.render(0.0625F);
		wing.offsetX = 0;
		GlStateManager.popMatrix();
    }
	
	public boolean shallRender(EntityLivingBase entity)
	{
		if(entity.getName().equals(Minecraft.getMinecraft().getSession().getUsername()))
			return true;
		return false;
	}
	
	public void loadModel(ModelPlayer modelPlayer, float unkown)
	{
			int textureSize = 32;
		int textureOffset0 = -7;
		int textureOffset1 = 11;
		int textureOffset2 = 14;
		int textureOffset3 = 18;
		int textureOffset4 = 17;
			float boxPos0 = -7F;
			float boxPos1 = 0F;
			float boxPos2 = 0.25F;
			float boxPos3 = -0.5F;
			float boxPos4 = 0.25F;
			float boxPos5 = -0.25F;
			float boxPos6 = -1.5F;
			float boxPos7 = 0.625F;
		float boxSize0 = 7.0F;
		float boxSize1 = 0.01F;
		float boxSize2 = 1.0F;
		float boxSize3 = 0.5F;
		
		renderer = new ModelRenderer(modelPlayer, "wings");
		renderer.setTextureSize(textureSize, textureSize);
			wing = new ModelRenderer(modelPlayer, "wing");
			offsetWingZ = (wing.offsetZ = 0.115F);
			offsetWingY = (wing.offsetY = 0.05F);
			wing.setTextureSize(textureSize, textureSize);
			wing.setTextureOffset(textureOffset0, textureOffset1);
			wing.setRotationPoint(boxPos6, boxPos7, boxPos4);
			wing.addBox(boxPos0, boxPos1, boxPos2, boxSize0, boxSize1, boxSize0);
				ModelRenderer wingBone = new ModelRenderer(modelPlayer, "wingbone");
				wingBone.setTextureSize(textureSize, textureSize);
				wingBone.setTextureOffset(textureOffset2, textureOffset1);
				wingBone.addBox(boxPos0, boxPos3, boxPos3, boxSize0, boxSize2, boxSize2);
			wing.addChild(wingBone);
				wingTip = new ModelRenderer(modelPlayer, "wingtip");
				wingTip.setTextureSize(textureSize, textureSize);
				wingTip.setTextureOffset(textureOffset0, textureOffset3);
				wingTip.setRotationPoint(boxPos0, boxPos1, boxPos1);
				wingTip.addBox(boxPos0, boxPos1, boxPos4, boxSize0, boxSize1, boxSize0);
					ModelRenderer wingTipBone = new ModelRenderer(modelPlayer, "wingtipbone");
					wingTipBone.setTextureSize(textureSize, textureSize);
					wingTipBone.setTextureOffset(textureOffset2, textureOffset4);
					wingTipBone.addBox(boxPos0, boxPos5, boxPos5, boxSize0, boxSize3, boxSize3);
				wingTip.addChild(wingTipBone);
			wing.addChild(wingTip);
		renderer.addChild(wing);
//		
//		renderer = new ModelRenderer(modelPlayer, "wings");
//		renderer.setTextureSize(256, 256);
//			wing = new ModelRenderer(modelPlayer, "wing");
//			wing.setTextureSize(256, 256);
//			wing.setTextureOffset(-56, 88);
//			wing.setRotationPoint(-12.0F, 5.0F, 2.0F);
//			wing.addBox(-56.0F, 0.0F, 2.0F, 56, 0, 56);
//				ModelRenderer wingBone = new ModelRenderer(modelPlayer, "wingbone");
//				wingBone.setTextureSize(256, 256);
//				wingBone.setTextureOffset(112, 88);
//				wingBone.addBox(-56.0F, -4.0F, -4.0F, 56, 8, 8);
//			wing.addChild(wingBone);
//				wingTip = new ModelRenderer(modelPlayer, "wingtip");
//				wingTip.setTextureSize(256, 256);
//				wingTip.setTextureOffset(-56, 144);
//				wingTip.setRotationPoint(-56.0F, 0.0F, 0.0F);
//				wingTip.addBox(-56.0F, 0.0F, 2.0F, 56, 0, 56);
//					ModelRenderer wingTipBone = new ModelRenderer(modelPlayer, "wingtipbone");
//					wingTipBone.setTextureSize(256, 256);
//					wingTipBone.setTextureOffset(112, 136);
//					wingTipBone.addBox(-56.0F, -2.0F, -2.0F, 56, 4, 4);
//				wingTip.addChild(wingTipBone);
//			wing.addChild(wingTip);
//		renderer.addChild(wing);

//		renderer = new ModelRenderer(modelPlayer, "wings");
//		renderer.setTextureSize(16, 16);
//		renderer.addBox(-3, 0, 2, 6, 8, 1);
//		renderer.addBox(-1F, 0.5F, 2.5F, 2, 7, 1);
//		ModelRenderer leftWing = new ModelRenderer(modelPlayer, "leftWing");
//		leftWing.setTextureSize(16, 16);
//		leftWing.setRotationPoint(1F, 0F, 1);
//		leftWing.rotateAngleX = 0.1F;
//		leftWing.rotateAngleY = -0.3F;
//		leftWing.rotateAngleZ = -0.2F;
//		leftWing.addBox(1, 0, 1, 10, 1, 1);
//		leftWing.addBox(1F, 0F, 1.5F, 10, 8, 0);
//			ModelRenderer leftWingInner = new ModelRenderer(modelPlayer, "leftWingInner");
//			leftWingInner.setTextureSize(16, 16);
//			leftWingInner.setRotationPoint(11, 0, 1);
//			leftWingInner.rotateAngleY = 0.6F;
//			leftWingInner.rotateAngleZ = 0.4F;
//			leftWingInner.addBox(0, 0, 0, 5, 1, 1);
//			leftWingInner.addBox(0F, 1F, 0.5F, 5, 7, 0);
//			leftWing.addChild(leftWingInner);
//		renderer.addChild(leftWing);
//		ModelRenderer rightWing = new ModelRenderer(modelPlayer, "rightWing");
//		rightWing.setTextureSize(16, 16);
//		rightWing.setRotationPoint(-1F, 0F, 1);
//		rightWing.rotateAngleX = 0.1F;
//		rightWing.rotateAngleY = 0.3F;
//		rightWing.rotateAngleZ = 0.2F;
//		rightWing.addBox(-11, 0, 1, 10, 1, 1);
//		rightWing.addBox(-11F, 0F, 1.5F, 10, 8, 0);
//			ModelRenderer rightWingInner = new ModelRenderer(modelPlayer, "rightWingInner");
//			rightWingInner.setTextureSize(16, 16);
//			rightWingInner.setRotationPoint(-11, 0, 1);
//			rightWingInner.rotateAngleY = -0.6F;
//			rightWingInner.rotateAngleZ = -0.4F;
//			rightWingInner.addBox(-5, 0, 0, 5, 1, 1);
//			rightWingInner.addBox(-5F, 1F, 0.5F, 5, 7, 0);
//			rightWing.addChild(rightWingInner);
//		renderer.addChild(rightWing);
	}
	
}