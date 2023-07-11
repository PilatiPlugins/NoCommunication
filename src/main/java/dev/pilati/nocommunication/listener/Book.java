package dev.pilati.nocommunication.listener;

import java.util.ArrayList;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.inventory.meta.BookMeta;

public class Book extends BaseListener implements Listener {

    @EventHandler
    public void onPlayerEditBookEvent(PlayerEditBookEvent event) {
        if (checkPermission(event.getPlayer(), "nocommunication.bypass.book")) {
            return;
        }

        BookMeta bookMeta = event.getNewBookMeta();
        ArrayList<String> pages = new ArrayList<String>();
        pages.add("");
        bookMeta.setPages(pages);
        event.setNewBookMeta(bookMeta);
        event.setCancelled(true);

        sendMessage(event.getPlayer(), "deny-book");
    }
}
