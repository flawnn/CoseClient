package me.cose.config;

import me.cose.connection.ConnectionManager;
import me.cose.utils.texture.HeadManager;
import me.cose.utils.texture.TextureManager;
import net.minecraft.client.multiplayer.ServerData;

public class Core {
	
	public static final String currentVersion = "0.4.0";
	public static final String minecraftVersion = "Cose " + currentVersion + " Alpha";
	public static final String minecraftPremium = "§6Premium Modus";
	
	public static final String serverIp = "127.0.0.1";
	public static final int serverPort = 40833;
	
	public static final boolean developerMode = false;
	public static boolean premiumMode = true;
	
	public static ConfigManager config;
	public static ConnectionManager connection;
	public static TextureManager texture;
	public static HeadManager head;
	public static InfoAccounts accounts;
	
	public static ServerData ramServerData;
	
}
