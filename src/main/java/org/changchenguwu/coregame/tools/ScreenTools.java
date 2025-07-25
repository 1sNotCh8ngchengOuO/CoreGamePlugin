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
import org.changchenguwu.coregame.commands.LoadAllScreenCommand;
import org.changchenguwu.coregame.events.admintools.AdminScreenListener;
import org.changchenguwu.coregame.main;

import java.util.List;

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

    public static Inventory toolsSettingChest() {
        Inventory inventory = Bukkit.createInventory(null,9, "STC设置");

        ItemStack info = new ItemStack(Material.PAPER, 1);
        ItemMeta meta = info.getItemMeta();
        assert meta != null;
        meta.setDisplayName("§e§l荧幕信息");
        meta.setLore(List.of(" ","§7已经加载的荧幕:","§7"+ LoadAllScreenCommand.getLoadId()));
        info.setItemMeta(meta);

        ItemStack set = new ItemStack(Material.COMMAND_BLOCK, 1);
        ItemMeta meta1 = set.getItemMeta();
        assert meta1 != null;
        meta1.setDisplayName("§a§l设置序列号");
        meta1.setLore(List.of(" ","§7点击设置荧幕序列号","§7当前序列号: §f"+ AdminScreenListener.id));
        set.setItemMeta(meta1);

        ItemStack reload = new ItemStack(Material.EGG, 1);
        ItemMeta meta2 = reload.getItemMeta();
        assert meta2 != null;
        meta2.setDisplayName("§c§l重载荧幕");
        reload.setItemMeta(meta2);

        inventory.setItem(7, reload);
        inventory.setItem(8, info);
        inventory.setItem(0,set);
        return inventory;
    }
}