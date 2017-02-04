package me.cose.crypter;

import me.cose.config.Core;

public class ServerCrypter {
	
	private static String keyCode = "";
	private static boolean changed = false;
	
	public static String getKey()
	{
		return keyCode;
	}
	
	public static boolean isKey()
	{
		return changed;
	}
	
	public static void setKey(String key)
	{
		keyCode = key;
		changed = true;
	}
	
	public static String encrypt(String input)
	{
		if(Core.developerMode)
		{
			System.out.println("KEY >> "+keyCode);
			System.out.println("EN >> "+input);
		}
		input = input.replace(' ', '#');
		if(Core.developerMode)
			System.out.println("EN >> "+input);
		try
		{
			input += "  ";
			String out = null;
			int current = 0;
			int max = input.length() - 1;
			int currentKey = 0;
			int maxKey = keyCode.length() - 1;
			while(current < max)
			{
				int cCode = (int) input.charAt(current);
				int kCode = (int) keyCode.charAt(currentKey);
				int c = cCode + kCode;
				if(out == null)
					out = ""+c;
				else
					out += " "+c;
				current ++;
				currentKey ++;
				if(currentKey > maxKey)
					currentKey = 0;
			}
			if(Core.developerMode)
				System.out.println("EN >> "+out);
			return out;
		}
		catch(Exception err) {}
		if(Core.developerMode)
			System.out.println("EN >> ERROR");
		return input;
	}
	
	public static String decrypt(String input)
	{
		if(Core.developerMode)
		{
			System.out.println("KEY >> "+keyCode);
			System.out.println("DE >> "+input);
		}
		try
		{
			String out = "";
			String[] inputs = input.split(" ");
			int currentKey = 0;
			int maxKey = keyCode.length() - 1;
			for(String in : inputs)
			{
				int cCode = Integer.valueOf(in);
				int kCode = (int) keyCode.charAt(currentKey);
				int c = cCode - kCode;
				char ch = (char) c;
				out += ch;
				currentKey ++;
				if(currentKey > maxKey)
					currentKey = 0;
			}
			if(Core.developerMode)
				System.out.println("DE >> "+out);
			out = out.replace('#', ' ');
			if(Core.developerMode)
				System.out.println("DE >> "+out);
			return out;
		}
		catch(Exception err) {}
		if(Core.developerMode)
			System.out.println("DE >> ERROR");
		return input;
	}
	
}
