package me.cose.config;

import java.util.List;

import com.google.common.collect.Lists;

public class InfoUpdates {
	
	public static List<String> lines;
	
	public static void load()
	{
		lines = Lists.newArrayList();
		a("§l0.4.0");
		a("  - Magische");
		a("     Miesmuschel");
		a("  - Optifine");
		a("");
		a("§l0.3.4");
		a("+ Cosmetics");
		a("  - Roter Schal");
		a("  - Schwarzer");
		a("      CylinderHut");
		a("  - Flügel");
		a("  - Capes");
		a("   - Holz bis Diamant");
		a("");
		a("§l0.3.3");
		a("+ Cose Inventar");
		a("  - Cases");
		a("  - Skins");
		a("  - Hüte");
		a("  - Brillen");
		a("  - Schale");
		a("  - Capes");
		a("  - Anderes");
		a("+ Hauptmenü");
		a("  - Update Liste");
		a("  - Skin Anzeige");
		a("");
		a("§l<= 0.3.2");
		a("+ Nachjoinen");
		a("+ Account");
		a("  - Status");
		a("  - Chat Status");
		a("+ Texturen");
		a("  - Crazy Buttons");
		a("  - Fadenkreuze");
		a("  - Schwerter");
		a("+ Schlagen & Essen");
		a("-- Kein direkter");
		a("   - Disconnect");
	}
	
	private static void a(String b)
	{
		lines.add(b);
	}
	
}
