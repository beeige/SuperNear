package SuperNear;
import java.io.File;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	public File config = new File(getDataFolder() + File.separator + "config.yml");
	public void onEnable() {
		getLogger().info("SuperNear: enable!");
		if (!this.config.exists()) {
			getConfig().options().copyDefaults(true);
			saveDefaultConfig();
		} 

		getCommand("near").setExecutor(new Near(this));
		getCommand("nearhide").setExecutor(new NearHide(this));
		getCommand("nearposition").setExecutor(new NearPosition(this));

	}
}


