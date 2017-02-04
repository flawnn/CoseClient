package me.cose.skins;

import java.util.List;

import com.google.common.collect.Lists;

import me.cose.skins.gui.CoseItem.CoseItemSlot;
import me.cose.skins.modules.SkinCapes;
import me.cose.skins.modules.SkinHatCylinder;
import me.cose.skins.modules.SkinOtherFinalWings;
import me.cose.skins.modules.SkinScarfRed;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;

public class SkinManager {
	
	public static List<Skin> skins;
	public static Skin none;
	
	public static Skin currentSkin;
	public static Skin currentHat;
	public static Skin currentGlasses;
	public static Skin currentScarf;
	public static Skin currentCape;
	public static Skin currentOther;
	
	public static int nextSecretId = 0;
	
	static
	{
		skins = Lists.newArrayList();
		none = new Skin(null, CoseItemSlot.CASES, "", false, false, false, false, false, false) {public boolean shallRender(EntityLivingBase entity){return false;}public void loadModel(ModelPlayer modelPlayer, float unkown){}};
		SkinCapes.load();
		skins.add(new SkinOtherFinalWings());
		skins.add(new SkinHatCylinder());
		skins.add(new SkinScarfRed());
	}
	
	public static List<Skin> getSkins(CoseItemSlot slot)
	{
		List<Skin> list = Lists.newArrayList();
		for(Skin skin : skins)
			if(skin.slot.equals(slot))
				list.add(skin);
		return list;
	}
	
	public static void loadLayers(RenderPlayer render)
	{
		for(Skin skin : skins)
			if(!skin.slot.equals(CoseItemSlot.CAPE))
				render.addLayer(skin);
	}
	
	public static void loadModels(ModelPlayer modelPlayer, float unkown)
	{
		for(Skin skin : skins)
			if(!skin.slot.equals(CoseItemSlot.CAPE))
				skin.loadModel(modelPlayer, unkown);
	}
	
	public static void showModel(boolean show)
	{
		for(Skin skin : skins)
			if(!skin.slot.equals(CoseItemSlot.CAPE))
				try {
					skin.renderer.showModel = show;
				} catch(Exception err) {}
	}
	
	public static void select(Skin skin)
	{
		if(skin == null)
			skin = none;
		if(!skin.allowSkin)
			currentSkin = null;
		if(!skin.allowHat)
			currentHat = null;
		if(!skin.allowGlasses)
			currentGlasses = null;
		if(!skin.allowScarf)
			currentScarf = null;
		if(!skin.allowCape)
			currentCape = null;
		if(!skin.allowOther)
			currentOther = null;
		switch(skin.slot)
		{
		case SKINS:
			currentSkin = skin;
			break;
		case HAT:
			currentHat = skin;
			break;
		case GLASSES:
			currentGlasses = skin;
			break;
		case SCARF:
			currentScarf = skin;
			break;
		case CAPE:
			currentCape = skin;
			break;
		case OTHER:
			currentOther = skin;
			break;
		case CASES: break;
		}
	}
	
}
