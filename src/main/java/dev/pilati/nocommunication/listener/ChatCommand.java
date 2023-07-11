package dev.pilati.nocommunication.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatCommand extends BaseListener implements Listener {

    @EventHandler
    public void onPlayerCommandPreprocessEvent(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage().split(" ")[0].trim().replace("/", "");

        switch (command) {
            case "me":
            case "tell":
            case "msg":
            case "teammsg":
            case "tm":
            case "w":
                if (checkPermission(event.getPlayer(), "nocommunication.bypass.command." + command)) {
                    return;
                }

                event.setCancelled(true);
                sendMessage(event.getPlayer(), "deny-chat-command");
                break;
        }
    }
}
