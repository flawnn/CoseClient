package meThread.cose;

import me.cose.config.Core;
import me.cose.config.Options;
import me.cose.gui.join.utils.CosePlayerList;
import me.cose.utils.texture.HeadManager;
import me.cose.utils.texture.TextureManager;
import net.minecraft.client.Minecraft;

public class CoseThreadInit implements Runnable {
	
	public CoseThreadInit()
	{
		Thread thread = new Thread(this);
		thread.setName("CoseThreadInit");
		thread.start();
	}
	
	public void run()
	{
		Core.texture = new TextureManager();
		Core.head = new HeadManager();
		Options.inRound = Core.connection.isPlayerInRound(Minecraft.getMinecraft().getSession().getUsername());
		CosePlayerList.register();
	}
	
}
