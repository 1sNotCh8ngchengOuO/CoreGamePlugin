package org.changchenguwu.coregame.item.adminstacks;


import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.changchenguwu.coregame.item.permissioncard.PermissionCard;

public class AllPluginItemsUI {
    public static Inventory openAllPluginItemsUi() {
        Inventory inventory = Bukkit.createInventory(null, 54, "itemstackui");
        addItems(inventory);
        return inventory;
    }

    private static void addItems(Inventory inventory) {
        inventory.addItem(PermissionCard.getPermissionCard0());
    }
}
