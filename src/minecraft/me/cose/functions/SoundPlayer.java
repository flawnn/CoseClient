package me.cose.functions;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundCategory;

public class SoundPlayer implements Runnable {
	
	private String file;
	private float volume;
	private long ifDistance;
	private boolean loop;
	
	public SoundPlayer(String filename, float volume, int inSeconds, boolean stopWhenClose)
	{
		this(filename, volume, (long) (inSeconds * 1000.0), stopWhenClose);
	}
	
	public SoundPlayer(String filename, float volume, int inSeconds, boolean stopWhenClose, boolean loop)
	{
		this(filename, volume, (long) (inSeconds * 1000.0), stopWhenClose, loop);
	}
	
	public SoundPlayer()
	{
		this("STOP", Minecraft.getMinecraft().gameSettings.getSoundLevel(SoundCategory.MASTER), 0, true);
	}
	
	public SoundPlayer(String filename, float volume, long inMilliSeconds, boolean stopWhenClose)
	{
		this(filename, volume, inMilliSeconds, stopWhenClose, false);
	}
	
	public SoundPlayer(String filename, float volume, long inMilliSeconds, boolean stopWhenClose, boolean loop)
	{
		this.file = filename + ".ogg";
		this.volume = volume;
		this.ifDistance = inMilliSeconds;
		Thread t = new Thread(this);
		t.setName("SoundPlayer | " + file);
		t.setDaemon(stopWhenClose);
		t.start();
		this.loop = loop;
	}
	
	public void run()
	{
		long startMS = System.currentTimeMillis();
		long distance = 0;
		while(distance <= ifDistance)
			distance = System.currentTimeMillis() - startMS;
		Minecraft mc = Minecraft.getMinecraft();
		if(volume > 0)
			mc.gameSettings.setSoundLevel(SoundCategory.MASTER, volume);
		mc.getSoundHandler().sndManager.sndSystem.backgroundMusic("ogg music", SoundPlayer.class.getResource(file), file, loop);
	}
	
}
