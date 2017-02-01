package me.cose.config;

public class Options {
	
	public static boolean online;
	public static boolean inRound;
	
	public static boolean blockChat;
	public static boolean crazyButtons;
	
	public static boolean inChatModus = false;
	
	public static void load()
	{
		blockChat = Boolean.valueOf(Core.config.get("blockChat", "" + false));
		crazyButtons = Boolean.valueOf(Core.config.get("crazyButtons", "" + false));
	}
	
	public static void save()
	{
		Core.config.set("blockChat", ""+blockChat);
		Core.config.set("crazyButtons", ""+crazyButtons);
	}
	
}
