package me.cose.config;

import java.util.List;

import com.google.common.collect.Lists;

public class InfoPremium {
	
	public static String title;
	public static List<String> lines;
	
	public static void load()
	{
		title = "§6§n§lVorteile > Für den Premium Client";
		lines = Lists.newArrayList();
		
		a("§8§n§l[> §a§nNormaler Client §8§n§l<]§r ");
		a("§8§n§l[> §e§nPremium Client §8§n§l<]§r ");
		
		a("&cFreunde's Liste §8| &c10");
		a("&cFreunde's Liste §8| &cUnbegrenzt");
		
		a("&cFadenkreuze §8| &cNormal - Extra 2");
		a("&cFadenkreuze §8| &cNormal - Extra 5");
		a("&cCrazy Buttons §8| &cNein");
		a("&cCrazy Buttons §8| &cJa");
	}
	
	private static void a(String b)
	{
		lines.add(b);
	}
	
}
