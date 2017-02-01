package me.cose.utils.texture;

import net.minecraft.client.gui.GuiButton;

public class TextureButton {
	
	private GuiButton btn;
	private int id;
	private int x;
	private int y;
	private String name;
	private String[] paths;
	private String[] values;
	private int i;
	private int min;
	private static final String[] colors = {"§a","§c","§e","§9","§b","§d"};
	
	public TextureButton(int id, int x, int y, String name, String[] paths, String[] names)
	{
		this.id = id;
			if(x < 0)
			x -= 175;
		this.x = x;
			y *= 22;
			y -= 22;
		this.y = y;
		this.name = name;
		this.paths = paths;
		values = names;
		i = 0;
		min = Math.min(paths.length, names.length);
		update();
	}
	
	public TextureButton setEnabled(boolean enabled)
	{
		if(btn == null)
			btn = new GuiButton(id, 0, 0, 175, 20, name+": "+colors[i]+values[i]);
		btn.enabled = enabled;
		return this;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void next()
	{
		i ++;
		if(i >= min)
			i = 0;
	}
	
	public int getCurrent()
	{
		return i;
	}
	
	public String getCurrentPath()
	{
		return paths[i];
	}
	
	public String getCurrentName()
	{
		return values[i];
	}
	
	public String getOldPath()
	{
		try
		{
			return paths[i-1];
		}
		catch(Exception err)
		{
			return paths[paths.length-1];
		}
	}
	
	public void set(int i)
	{
		if(i >= min)
			this.i = min;
		else if(i < 0)
			this.i = 0;
		else
			this.i = i;
	}
	
	public GuiButton getButton()
	{
		return btn;
	}
	
	public void update()
	{
		if(btn == null)
			btn = new GuiButton(id, 0, 0, 175, 20, name+": "+colors[i]+values[i]);
		else
			btn.displayString = name+": "+colors[i]+values[i];
	}
	
	public String[] getPaths()
	{
		return paths;
	}
	
	public void setButton(GuiButton btn)
	{
		this.btn = btn;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
}
