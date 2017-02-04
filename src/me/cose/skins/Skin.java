package me.cose.skins;

import me.cose.skins.gui.CoseItem.CoseItemSlot;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class Skin implements LayerRenderer {
	
	public ItemStack item;
	public CoseItemSlot slot;
	public ResourceLocation texturePath;
	public boolean allowSkin;
	public boolean allowHat;
	public boolean allowGlasses;
	public boolean allowScarf;
	public boolean allowCape;
	public boolean allowOther;
	public int buttonId;
	
	public ModelRenderer renderer;
	
	public int secretId = 0;
	
	public Skin(ItemStack item, CoseItemSlot slot, String texturePath)
	{
		this(item, slot, texturePath, true, true, true, true, true, true);
	}
	
	public Skin(ItemStack item, CoseItemSlot slot, String texturePath, boolean allowSkin, boolean allowHat, boolean allowGlasses, boolean allowScarf, boolean allowCape, boolean allowOther)
	{
		this.item = item;
		this.slot = slot;
		this.texturePath = new ResourceLocation(texturePath);
		this.allowSkin = allowSkin;
		this.allowHat = allowHat;
		this.allowGlasses = allowGlasses;
		this.allowScarf = allowScarf;
		this.allowCape = allowCape;
		this.allowOther = allowOther;
		secretId = SkinManager.nextSecretId;
		SkinManager.nextSecretId ++;
	}
	
	public void doRenderLayer(EntityLivingBase entity, float a, float b, float c, float d, float e, float f, float g)
	{
		boolean ask = false;
		if(SkinManager.currentSkin != null)
			if(slot.equals(CoseItemSlot.SKINS) && SkinManager.currentSkin.secretId == secretId)
				ask = true;
		if(SkinManager.currentHat != null)
			if(slot.equals(CoseItemSlot.HAT) && SkinManager.currentHat.secretId == secretId)
				ask = true;
		if(SkinManager.currentGlasses != null)
			if(slot.equals(CoseItemSlot.GLASSES) && SkinManager.currentGlasses.secretId == secretId)
				ask = true;
		if(SkinManager.currentScarf != null)
			if(slot.equals(CoseItemSlot.SCARF) && SkinManager.currentScarf.secretId == secretId)
				ask = true;
		if(SkinManager.currentOther != null)
			if(slot.equals(CoseItemSlot.OTHER) && SkinManager.currentOther.secretId == secretId)
				ask = true;
		if(ask)
			if(shallRender(entity))
			{
				Minecraft.getMinecraft().getTextureManager().bindTexture(texturePath);
				renderer.render(0.0625F);
			}
	}
	
	public boolean shouldCombineTextures() {return true;}
	
	public abstract boolean shallRender(EntityLivingBase entity);
	public abstract void loadModel(ModelPlayer modelPlayer, float unkown);
	
	public static ItemStack createItem(Item item)
	{
		return new ItemStack(item);
	}
	
	public static ItemStack createItem(Block block, int meta)
	{
		return new ItemStack(block, 1, meta);
	}
	
	public static ItemStack createItem(Block block)
	{
		return new ItemStack(block);
	}
	
}
