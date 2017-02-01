package me.cose.utils.texture;

public enum SkinType {
	
	DEFAULT(0, "Normal", false),
	EXTENDED(1, "Würfel", true);
	
	private static final int max = 1;
	private int id;
	private String name;
	private boolean cubed;
	
	SkinType(int i, String n, boolean c)
	{
		id = i;
		name = n;
		cubed = c;
	}
	
	public String getName()
	{
		return name;
	}
	
	public SkinType next()
	{
		int next = id + 1;
		if(next > max)
			next = 0;
		return SkinType.values()[next];
	}
	
	public boolean isCubed()
	{
		return cubed;
	}
	
}
