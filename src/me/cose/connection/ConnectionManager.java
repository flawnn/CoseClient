package me.cose.connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

import com.google.common.collect.Maps;

import me.cose.config.Core;
import me.cose.crypter.ServerCrypter;
import net.minecraft.client.Minecraft;

public class ConnectionManager implements Runnable {
	
	private Socket socket;
	private PrintWriter writer;
	private BufferedReader reader;
	private boolean connected;
	
	private Map<String, Boolean> online;
	private Map<String, String> server;
	private Map<String, Boolean> inRound;
	
	private final int delayTimer = 500;
	
	public ConnectionManager()
	{
		online = Maps.newHashMap();
		server = Maps.newHashMap();
		connected = false;
		try
		{
			socket = new Socket(Core.serverIp, Core.serverPort);
			writer = new PrintWriter(socket.getOutputStream());
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			connected = true;
		}
		catch(Exception err) {}
		if(!connected)
			return;
		Thread thread = new Thread(this);
		thread.setName("Cose Server Listener");
		thread.setDaemon(true);
		thread.start();
	}
	
	public boolean isConnected()
	{
		return connected;
	}
	
	public void run()
	{
		try
		{
			String info;
			while(connected && (info = reader.readLine()) != null)
			{
				if(!ServerCrypter.isKey())
				{
					ServerCrypter.setKey(info);
					sendCurrentPlayerData();
				}
				else
					input(ServerCrypter.decrypt(info));
			}
		}
		catch(Exception err)
		{
			connected = false;
			if(Core.developerMode)
				System.out.println("Cose Server Connection Closed");
		}
	}
	
	private void input(String input)
	{
		String[] args = input.split(" ");
		
		if(args[0].equalsIgnoreCase("checker"))
			output("checker");
		
		else if(args[0].equalsIgnoreCase("online"))
			try {
				online.put(args[1], Boolean.valueOf(args[2]));
			} catch(Exception err) {}
		
		else if(args[0].equalsIgnoreCase("server"))
			server.put(args[1], args[2]);
		else if(args[0].equalsIgnoreCase("inRound"))
			inRound.put(args[1], Boolean.valueOf(args[2]));
	}
	
	private void output(String output)
	{
		String send = ServerCrypter.encrypt(output);
		writer.write(send);
		writer.flush();
	}
	
	public void sendCurrentPlayerData()
	{
		Minecraft mc = Minecraft.getMinecraft();
		String send = "";
		send += "login ";
		send += mc.getSession().getUsername() + " ";
		send += mc.getSession().getSessionID();
		output(send);
	}
	
	public boolean isPlayerOnline(String playername)
	{
		if(!connected)
			return false;
		playername = playername.toLowerCase();
		online.put(playername, false);
		output("online get " + playername);
		stop();
		return online.get(playername);
	}
	
	public String getServerPlayerIsOn(String playername)
	{
		if(!connected)
			return "null";
		playername = playername.toLowerCase();
		server.put(playername, "null");
		output("server get " + playername);
		stop();
		return server.get(playername);
	}
	
	public void setMyOnlineStatus(boolean status)
	{
		if(!connected)
			return;
		output("online set " + status);
	}
	
	public void setMyOnlineServer(String server, int port)
	{
		if(!connected)
			return;
		output("server set " + server + ":" + port);
	}
	
	public void setMeInRound(boolean status)
	{
		if(!connected)
			return;
		output("inround set " + status);
	}
	
	public boolean isPlayerInRound(String player)
	{
		if(!connected)
			return false;
		player = player.toLowerCase();
		inRound.put(player, false);
		stop();
		return inRound.get(player);
	}
	
	private void stop()
	{
		try {
			wait(delayTimer);
		} catch(Exception err) {}
	}
	
}
