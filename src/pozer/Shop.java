package pozer;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class Shop implements CommandExecutor {

	private Main main = (Main)Main.getPlugin(Main.class);

	public Shop(Main main) {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player)sender;
		String near = new String();
		if(args.length == 0) {
			if(player.hasPermission("near.usage")) {
				int rad = 0;
				String ColourBracket = main.getConfig().getString("ColourBracket").replace("&", "§");
				String ColourDistanse = main.getConfig().getString("ColourDistanse").replace("&", "§");
				String Empty = main.getConfig().getString("Empty").replace("&", "§");
				for(int radius = 1; radius <= 1000; radius++) {
					if(player.hasPermission("near." + radius)) {
						rad = radius;
					}
				}
				String NearMessage = main.getConfig().getString("NearMessage").replace("&", "§").replace("%RADIUS%", rad+"");

				for(Player Online : player.getWorld().getPlayers()) {
					if(player.getLocation().distance(Online.getLocation()) <= rad) {
						if(player != Online) {
							near = near + Online.getName()+ ColourBracket +"(" + ColourDistanse + (int) player.getLocation().distance(Online.getLocation()) + ColourBracket  + ") " ;
						}
					}
				}
				if(near.isEmpty()) {
					player.sendMessage(NearMessage + Empty);
				}else {
					player.sendMessage(NearMessage + near);
				}
			}else {
				String NoPerm = main.getConfig().getString("NoPerm").replace("&", "§");
				player.sendMessage(NoPerm);
			}
		}
		else {
			if(args[0].equalsIgnoreCase("reload")) {
				if(player.hasPermission("near.reload")) {
					main.reloadConfig();
					player.sendMessage("SuperNear: Ïåðåçàãðóæåí.");
				}else {
					String NoPerm = main.getConfig().getString("NoPerm").replace("&", "§");
					player.sendMessage(NoPerm);
				}
			}
		}
		return false;
	}
}


