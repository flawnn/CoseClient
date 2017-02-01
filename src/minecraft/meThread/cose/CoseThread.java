package meThread.cose;

import me.cose.config.ConfigManager;
import me.cose.config.Core;
import me.cose.config.InfoAccounts;
import me.cose.config.InfoPremium;
import me.cose.config.InfoUpdates;
import me.cose.config.Options;
import me.cose.connection.ConnectionManager;
import me.cose.utils.texture.TextureManager;

public class CoseThread implements Runnable {
	
	public CoseThread()
	{
		Thread thread = new Thread(this);
		thread.setName("CoseThread");
		thread.start();
	}
	
	public void run()
	{
		Core.config = new ConfigManager();
		Core.connection = new ConnectionManager();
		Core.accounts = new InfoAccounts();
		Options.load();
		InfoPremium.load();
		InfoUpdates.load();
	}
	
}
