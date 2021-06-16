package pozer;
import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public File config = new File(getDataFolder() + File.separator + "config.yml");
	public void onEnable() {
		getLogger().info("SuperNear: enable!");
		if (!this.config.exists()) {
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
		} 

		getCommand("near").setExecutor(new Shop(this));

	}
}


