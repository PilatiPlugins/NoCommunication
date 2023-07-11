package dev.pilati.nocommunication.listener;

import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.EventHandler;

public class ItemRename extends BaseListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        if (!(event.getInventory() instanceof AnvilInventory)) {
            return;
        }

        if (event.getSlotType() != InventoryType.SlotType.RESULT) {
            return;
        }

        if (checkPermission(event.getWhoClicked(), "nocommunication.bypass.item-rename")) {
            return;
        }

        ItemMeta itemMeta = event.getCurrentItem().getItemMeta();
        if (itemMeta.hasDisplayName() || itemMeta.hasLocalizedName()) {
            sendMessage(event.getWhoClicked(), "deny-item-rename");
            event.setCancelled(true);
        }
    }
}
