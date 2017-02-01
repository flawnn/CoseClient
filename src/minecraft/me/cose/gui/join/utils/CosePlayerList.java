package me.cose.gui.join.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import me.cose.config.Core;
import me.cose.config.InfoAccounts;
import net.minecraft.client.Minecraft;

public class CosePlayerList {
	
private static File file;
	
	public static void register()
	{
		file = new File(Minecraft.getMinecraft().mcDataDir+"/Cose", "friendlist.data");
		if(!file.exists())
			try
			{
				file.createNewFile();
				List<String> names = new ArrayList<>();
				InfoAccounts in = Core.accounts;
				for(int i = 0; i <= in.getMax(); i ++)
					names.add(in.getData(i)[1]);
				setNames(names);
			}
			catch(Exception err) {}
	}
	
	public static List<String> getNames()
	{
		try
		{
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			List<String> lines = new ArrayList<String>();
			String line;
			
			while((line = bufferedReader.readLine()) != null)
				lines.add(line);
			
			bufferedReader.close();
			fileReader.close();
			
			return lines;
		}
		catch(Exception err)
		{
			return new ArrayList<String>();
		}
	}
	
	public static void setNames(List<String> names)
	{
		try
		{
			file.delete();
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			for(String line : names)
			{
				printWriter.println(line);
				printWriter.flush();
			}
			
			printWriter.close();
			fileWriter.close();
			System.out.println("3");
		}
		catch(Exception err) {err.printStackTrace();}
		System.out.println("4");
	}
	
}
