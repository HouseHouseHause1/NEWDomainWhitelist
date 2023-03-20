package lol.house1.newdomainwhitelist;

import org.bukkit.plugin.java.JavaPlugin;

public class DomainWhitelistPlugin extends JavaPlugin {

    private static DomainWhitelistPlugin plugin;

    @Override
    public void onEnable() {
        plugin = this;

        // Load configuration
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        // Register event listeners
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);

        // Register commands
        getCommand("domainwhitelist").setExecutor(new DomainWhitelistCommand());
    }

    public static DomainWhitelistPlugin getPlugin() {
        return plugin;
    }
}