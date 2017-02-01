package me.cose;

import java.io.File;

import me.cose.config.Core;
import me.cose.config.Options;
import me.cose.utils.OverlayEffects;
import meThread.cose.CoseThread;
import meThread.cose.CoseThreadInit;
import net.minecraft.client.Minecraft;
import net.minecraft.init.Items;

public class Cose {
	
	public static Cose getInstance;
	
	public Cose()
	{
		getInstance = this;
		
		File file = new File(".", "Cose");
		if(!file.exists())
			file.mkdir();
		
		new CoseThread();
	}
	
	public void init()
	{
		new CoseThreadInit();
		if(Core.premiumMode)
			OverlayEffects.achiev(Core.minecraftPremium, Core.minecraftPremium, Items.gold_ingot, true, true);
	}
	
	public void close()
	{
		Core.connection.setMyOnlineStatus(false);
		Options.save();
	}
	
}
