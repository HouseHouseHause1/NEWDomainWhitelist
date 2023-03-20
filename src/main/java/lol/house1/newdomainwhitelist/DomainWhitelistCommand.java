package lol.house1.newdomainwhitelist;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.List;

public class DomainWhitelistCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
            return true;
        }

        Plugin plugin = DomainWhitelistPlugin.getPlugin();
        Player player = (Player) sender;

        if (!player.hasPermission("domainwhitelist.edit")) {
            player.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(ChatColor.RED + "Usage: /domainwhitelist <domain>");
            return true;
        }

        String domain = args[0];
        File domainFolder = new File(plugin.getDataFolder() + File.separator + domain);

        if (!domainFolder.exists()) {
            domainFolder.mkdir();
            player.sendMessage(ChatColor.GREEN + "Domain folder created.");
        }

        List<String> domainMotd = plugin.getConfig().getStringList("domain-motd." + domain);
        if (domainMotd != null && !domainMotd.isEmpty()) {
            plugin.getConfig().set("motd", domainMotd);
            player.sendMessage(ChatColor.GREEN + "Motd updated for domain " + domain + ".");
        }

        String domainIcon = plugin.getConfig().getString("domain-icon." + domain);
        if (domainIcon != null && !domainIcon.isEmpty()) {
            plugin.getConfig().set("server-icon", domainIcon);
            player.sendMessage(ChatColor.GREEN + "Server icon updated for domain " + domain + ".");
        }

        plugin.saveConfig();
        return true;
    }
}