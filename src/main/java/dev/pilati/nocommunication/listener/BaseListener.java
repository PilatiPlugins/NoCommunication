package dev.pilati.nocommunication.listener;

import org.bukkit.command.CommandSender;

import dev.pilati.nocommunication.manager.MessageManager;
import dev.pilati.nocommunication.manager.PermissionManager;

public abstract class BaseListener {

    public boolean checkPermission(CommandSender sender, String permission) {
        return PermissionManager.getInstance().hasPermission(sender, permission);
    }

    public void sendMessage(CommandSender sender, String messageKey) {
        MessageManager.getInstance().sendMessage(sender, messageKey);
    }

}
