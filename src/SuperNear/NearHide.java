package SuperNear;



import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NearHide implements CommandExecutor {
	private Main main = (Main)Main.getPlugin(Main.class);
	public NearHide(Main main) {
		this.main = main;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player) {
			ConfigData cfg = new ConfigData(main);
			Player player = (Player) sender;
			if(player.hasPermission("near.hide")) {
				if(args.length == 1) {
					try {
						int time = Integer.parseInt(args[0]);
						time = time * 20;
						player.addAttachment(main, "near.invisibility", true, time);
						player.sendMessage("SuperNear: Теперь вас невидно на /near " + args[0] + " секунд.");

						return false;
					} catch (Exception e) {
						player.sendMessage("§eSuperNear: Время должно быть числом! В секундном формате.");
						return false;
					}				
				}
				if(args.length == 2) {
					try {
						int time = Integer.parseInt(args[0]);
						time = time * 20;
						Player hidePlayer = Bukkit.getPlayer(args[1]);
						if(hidePlayer != null) {
							hidePlayer.addAttachment(main, "near.invisibility", true, time);
							player.sendMessage("§eSuperNear: Теперь игрока " + args[1] + " невидно на /near " + args[0] + " секунд.");	
						}else {
							player.sendMessage("§eSuperNear: Игрок " + args[1] + " не онлайн.");	
						}					
						return false;
					} catch (Exception e) {
						player.sendMessage("§eSuperNear: Время должно быть числом! В секундном формате.");
						return false;
					}
				}			
				player.sendMessage("§eSuperNear: Правильное использование - /nearhide <секунды>.");

			}else {
				player.sendMessage(cfg.getNoRerm());
			}
		}else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Команду можно использовать только от имени игрока!"); 
		}
		return false;
	}

}
