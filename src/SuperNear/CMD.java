package SuperNear;
import java.util.ArrayList;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class CMD implements CommandExecutor {

	private Main main = (Main)Main.getPlugin(Main.class);

	public CMD(Main main) {

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player)sender;
			if(args.length == 0) {
				if(player.hasPermission("near.usage")) {
					int rad = 0;
					StringBuilder near = new StringBuilder("");
					String colourBracket = main.getConfig().getString("ColourBracket").replace("&", "§");
					String colourDistanse = main.getConfig().getString("ColourDistanse").replace("&", "§");
					String empty = main.getConfig().getString("Empty").replace("&", "§");
					for (String perms : main.getConfig().getConfigurationSection("perms").getKeys(false)) {
					    if(player.hasPermission(perms)) {
					    	rad = main.getConfig().getInt("perms." + perms);
					    }
					}
					String NearMessage = main.getConfig().getString("NearMessage").replace("&", "§").replace("%RADIUS%", rad+"");

					for(Player Online : player.getWorld().getPlayers()) {
						if(player.getLocation().distance(Online.getLocation()) <= rad) {
							if(player != Online) {
								near.append(Online.getName()).append(colourBracket).append("(").append(colourDistanse).append((int) player.getLocation().distance(Online.getLocation())).append(colourBracket).append(") ");
							}
						}
					}
					if(near.toString().isEmpty()) {
						player.sendMessage(NearMessage + empty);
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

		}
		return false;
	}
}


