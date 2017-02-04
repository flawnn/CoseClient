package me.cose.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;

public class ConfigManager {
	
	private File file;
	
	public ConfigManager()
	{
		file = new File(Minecraft.getMinecraft().mcDataDir + "/Cose", "config.data");
		if(!file.exists())
			try {
				file.createNewFile();
			} catch(Exception err) {}
	}
	
	public File getFile()
	{
		return file;
	}
	
	public String get(String object)
	{
		for(String line : getLines())
		{
			String[] splitted = line.split(":");
			String first = splitted[0];
			if(first.equals(object))
				try {
					return splitted[1];
				} catch(Exception err) {}
		}
		return null;
	}
	
	public String get(String object, String defaultValue)
	{
		String value = get(object);
		if(value == null)
			value = defaultValue;
		return value;
	}
	
	public void set(String object, String value)
	{
		String oldValue = get(object);
		List<String> lines = getLines();
		if(oldValue != null)
			lines.remove(object + ":" + oldValue);
		lines.add(object + ":" + value);
		setLines(lines);
	}
	
	private void setLines(List<String> lines)
	{
		try
		{
			file.delete();
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			for(String line : lines)
			{
				printWriter.println(line);
				printWriter.flush();
			}
			printWriter.close();
			fileWriter.close();
		}
		catch(Exception err) {}
	}
	
	private List<String> getLines()
	{
		List<String> lines = Lists.newArrayList();
		try
		{
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while((line = bufferedReader.readLine()) != null)
				lines.add(line);
			bufferedReader.close();
			fileReader.close();
		}
		catch(Exception err)
		{
			lines.clear();
		}
		return lines;
	}
	
}
