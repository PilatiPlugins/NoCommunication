package dev.pilati.nocommunication.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.EventHandler;

public class Sign extends BaseListener implements Listener {

    @EventHandler
    public void onSignChangeEvent(SignChangeEvent event) {
        if (checkPermission(event.getPlayer(), "nocommunication.bypass.sign")) {
            return;
        }

        event.setCancelled(true);

        for (String line : event.getLines()) {
            if (line.trim().length() > 0) {
                sendMessage(event.getPlayer(), "deny-sign");
                break;
            }
        }
    }
}
