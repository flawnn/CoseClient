package me.cose.config;

import java.util.HashMap;
import java.util.Map;

public class InfoAccounts {
	
	private int next;
	private Map<Integer, String> names;
	private Map<Integer, String> ingames;
	private Map<Integer, String> links;
	
	public InfoAccounts()
	{
		next = 0;
		names = new HashMap<Integer, String>();
		ingames = new HashMap<Integer, String>();
		links = new HashMap<Integer, String>();
		
		register("OzZkar", "OzZkarYT", "https://www.youtube.com/channel/UChMI4b4FEdrx7D2FjLLna8A");
		register("SvendermanGaming", "AtmoArtwork", "https://www.youtube.com/channel/UCpiRRzQlZyLjTXbg5Ahw4Vg");
		register("Verzaukeks", "Verzaukeks", "https://www.youtube.com/channel/UCh5TDz7-u1-UJ8rcqG4C6wA");
		register("dasPausenbrot", "dasPausenbrot", "http://pixelsite.tk/");
	}
	
	private void register(String name, String ingame, String link)
	{
		names.put(next, name);
		ingames.put(next, ingame);
		links.put(next, link);
		next ++;
	}
	
	public int getMax()
	{
		return next -1;
	}
	
	public String[] getData(int index)
	{
		try
		{
			String name = names.get(index);
			String ingame = ingames.get(index);
			String link = links.get(index);
			return new String[] {name, ingame, link};
		}
		catch(Exception err) {}
		return new String[] {"", "", ""};
	}
	
}
