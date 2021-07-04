package SuperNear;

public class ConfigData {
	
	
	private Main main = (Main)Main.getPlugin(Main.class);
	
	

	public ConfigData(Main main) {
		this.main = main;
	}
	public String getNearMessage(int rad) {
		String nearMessage = main.getConfig().getString("NearMessage").replace("&", "§").replace("%RADIUS%", rad+"");
		return nearMessage;
	}
	public String getPlayerMessage(String playerName, int distance) {	
		String playerMessage = main.getConfig().getString("PlayerMessage").replace("&", "§").replace("%PlayerName%", playerName).replace("%Distance%", ""+distance);
		return playerMessage;	
	}
	public String getEmpty() {
		String empty = main.getConfig().getString("Empty").replace("&", "§");
		return empty;
	}
	public String getNoRerm() {
		String NoPerm = main.getConfig().getString("NoPerm").replace("&", "§");
		return NoPerm;
	}
	public boolean getGM() {
		boolean gm = main.getConfig().getBoolean("gamemode3");
		return gm;
	}
	public boolean getInvisibility() {
		boolean invisibility = main.getConfig().getBoolean("invisibility");
		return invisibility;
	}
	public String getPos(int x, int y, int z) {
		String pos = main.getConfig().getString("position").replace("&", "§").replace("%X%", x+"").replace("%Y%", y+"").replace("%Z%", z+"");
		return pos;
	}

}
