package SuperNear;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;



public class NearPosition implements CommandExecutor {

	private Main main = (Main)Main.getPlugin(Main.class);

	public NearPosition(Main main) {
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			Player player = (Player)sender;
			ConfigData cfg = new ConfigData(main);
			if(args.length == 0) {
				if(player.hasPermission("near.usage.position")) {
					int rad = 0;					
					StringBuilder near = new StringBuilder("");					
					for (String perms : main.getConfig().getConfigurationSection("perms").getKeys(false)) {						
						if(player.hasPermission(perms)) {							
							rad = main.getConfig().getInt("perms." + perms);						
						}	
					}
					for(Player Online : player.getWorld().getPlayers()) {
						if(player.getLocation().distance(Online.getLocation()) <= rad) {						
								if(player != Online) {
									if(Online.getGameMode() == GameMode.SPECTATOR) {
										if(!cfg.getGM()) {
											continue;
										}
									}
									if(Online.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
										if(!cfg.getInvisibility()) {
											continue;
										}
									}
									if(Online.hasPermission("near.invisibility")) {
										continue;
									}									
									near.append(cfg.getPlayerMessage(Online.getName(), (int) player.getLocation().distance(Online.getLocation())));									
									Location loc = Online.getLocation(); 									
									near.append(cfg.getPos(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));	
								}						
						}
					}
					if(near.toString().isEmpty()) {
						player.sendMessage(cfg.getNearMessage(rad) + cfg.getEmpty());
					}else {
						player.sendMessage(cfg.getNearMessage(rad) + "\n" +  near);
					}
				}else {
					player.sendMessage(cfg.getNoRerm());
				}
			}
			else {
				if(args[0].equalsIgnoreCase("reload")) {
					if(player.hasPermission("near.reload")) {
						main.reloadConfig();
						player.sendMessage("SuperNear: Перезагружен.");
					}else {
						player.sendMessage(cfg.getNoRerm());
					}
				}
			}
		}else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Команду можно использовать только от имени игрока!"); 
		}
		return false;
	}
}


