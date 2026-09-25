package org.changchenguwu.coregame.gui;


import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class AllPluginItemsMenu {
    public static Inventory openAllPluginItemsUi() {
        Inventory inventory = Bukkit.createInventory(null, 54, "itemstackui");
        addItems(inventory);
        return inventory;
    }

    private static void addItems(Inventory inventory) {
        inventory.addItem(PermissionCard.getPermissionCard0());
    }
}
