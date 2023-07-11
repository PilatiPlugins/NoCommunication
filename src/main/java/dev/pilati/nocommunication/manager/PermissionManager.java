package dev.pilati.nocommunication.manager;

import org.bukkit.command.CommandSender;
import org.bukkit.plugin.RegisteredServiceProvider;

import dev.pilati.nocommunication.NoCommunication;
import net.milkbowl.vault.permission.Permission;

public class PermissionManager {

    private static PermissionManager instance;

    private Permission permission;

    public static PermissionManager getInstance() {
        if (instance == null) {
            instance = new PermissionManager();
        }

        return instance;
    }

    public PermissionManager() {
        super();
        instance = this;
    }

    public void load() {
        NoCommunication plugin = NoCommunication.getInstance();

        if (plugin.getServer().getPluginManager().isPluginEnabled("Vault")) {

            RegisteredServiceProvider<Permission> rsp = plugin
                    .getServer()
                    .getServicesManager()
                    .getRegistration(Permission.class);

            permission = rsp.getProvider();
        }
    }

    public void unload() {
        instance = null;
    }

    public boolean hasPermission(CommandSender sender, String permissionKey) {
        if (permission != null) {
            return permission.has(sender, permissionKey);
        }

        return sender.hasPermission(permissionKey);
    }

}
