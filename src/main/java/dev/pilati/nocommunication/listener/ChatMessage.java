package dev.pilati.nocommunication.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatMessage extends BaseListener implements Listener {

    @EventHandler
    public void onAsyncPlayerChatEvent(AsyncPlayerChatEvent event) {
        if (checkPermission(event.getPlayer(), "nocommunication.bypass.chat")) {
            return;
        }

        event.setCancelled(true);
        sendMessage(event.getPlayer(), "deny-chat-message");
    }
}
