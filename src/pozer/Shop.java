package pozer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Shop implements CommandExecutor {
	private Main plugin;
	private Main main = (Main)Main.getPlugin(Main.class);

	public Shop(Main main) {
		this.plugin = main;
	}


	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player)sender;
		String near = new String();
		if(args.length == 0) {
			if(p.hasPermission("near.usage")) {
				int rad = 0;
				String ColourBracket = main.getConfig().getString("ColourBracket").replace("&", "§");
				String ColourDistanse = main.getConfig().getString("ColourDistanse").replace("&", "§");
				String Empty = main.getConfig().getString("Empty").replace("&", "§");
				for(int radius = 1; radius <= 1000; radius++) {
					if(p.hasPermission("near." + radius)) {
						rad = radius;
					}

				}
				String NearMessage = main.getConfig().getString("NearMessage").replace("&", "§").replace("%RADIUS%", rad+"");
				for(Player Online : Bukkit.getOnlinePlayers()) {
					if(p.getWorld() == Online.getWorld()) {
						if(distanse(p.getLocation(), Online.getLocation()) <= rad) {
							if(p.getName().equalsIgnoreCase(Online.getName())) {

							}else {
								near = near + Online.getName()+ ColourBracket +"(" + ColourDistanse + distanse(p.getLocation(), Online.getLocation()) + ColourBracket  + ") " ;
							}
						}
					}
				}
				if(near.equalsIgnoreCase("")) {
					p.sendMessage(NearMessage + Empty);
				}else {
					p.sendMessage(NearMessage + near);
				}
			}else {
				String NoPerm = main.getConfig().getString("NoPerm").replace("&", "§");
				p.sendMessage(NoPerm);
			}
		}
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("reload")) {
				if(p.hasPermission("near.reload")) {
					main.reloadConfig();
					p.sendMessage("SuperNear: Ïåðåçàãðóæåí.");
				}else {
					String NoPerm = main.getConfig().getString("NoPerm").replace("&", "§");
					p.sendMessage(NoPerm);
				}
			}
		}
		return false;
	}
	public int distanse(Location loc1, Location loc2) {
		float Xentity = loc1.getBlockX();
		float Yentity = loc1.getBlockY();
		float Zentity = loc1.getBlockZ();
		float Xdamager = loc2.getBlockX();
		float Ydamager = loc2.getBlockY();
		float Zdamager = loc2.getBlockZ();
		float distanse = (float) Math.sqrt(Math.pow((Xentity - Xdamager), 2) + Math.pow((Yentity - Ydamager), 2) + Math.pow((Zentity - Zdamager), 2));
		return (int) distanse;
	}
}


