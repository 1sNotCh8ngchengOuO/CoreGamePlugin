package org.changchenguwu.coregame.item.permissioncard;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.changchenguwu.coregame.main;

import java.util.Objects;

public class PermissionCard {

    public static final NamespacedKey PERMISSION_CARD_0 = new NamespacedKey(main.getPluginInstance(), "permission_card_0");
    public static ItemStack getPermissionCard0() {
        ItemStack itemStack = new ItemStack(Material.PAPER);
        ItemMeta meta = itemStack.getItemMeta();
        Objects.requireNonNull(meta).setDisplayName("权限卡0");
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
