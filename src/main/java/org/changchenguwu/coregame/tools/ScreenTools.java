package org.changchenguwu.coregame.tools;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.changchenguwu.coregame.main;

public class ScreenTools {

    public static Inventory createScreenToolsChest(){
        Inventory inventory = Bukkit.createInventory(null, 9, "STC");
        inventory.setItem(0, generateScreenTools());
        return inventory;
    }

    public static final NamespacedKey NEW_SCREEN_KEY = new NamespacedKey(JavaPlugin.getPlugin(main.class),"new_screen_key");
    public static ItemStack generateScreenTools() {
        ItemStack itemStack = new ItemStack(Material.STICK, 1);
        ItemMeta meta = itemStack.getItemMeta();
        if (meta != null) {
            meta.getPersistentDataContainer().set(NEW_SCREEN_KEY, PersistentDataType.BYTE, (byte) 1);
            meta.setDisplayName("§f§l生成荧幕");
            meta.addEnchant(Enchantment.LUCK,1, true);
            itemStack.setItemMeta(meta);
        }
        return itemStack;
    }
}
