package lol.house1.newdomainwhitelist;

import lol.house1.newdomainwhitelist.DomainWhitelistPlugin;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.Plugin;

public class PlayerJoinListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Plugin plugin = DomainWhitelistPlugin.getPlugin();

        String whitelistDomain = plugin.getConfig().getString("whitelist-domain");
        String message = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("join-message"));

        if (whitelistDomain != null && !whitelistDomain.isEmpty()) {
            event.getPlayer().sendMessage(message.replace("{domain}", whitelistDomain));
        }
    }
}