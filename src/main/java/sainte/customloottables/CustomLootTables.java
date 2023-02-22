package sainte.customloottables;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.event.Listener;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import sainte.customheadsapi.CustomHeadsAPI;
import sainte.customloottables.Commands.CLTCommands;
import sainte.customloottables.MobLootTables.MobLootTables;
import sainte.customloottables.Utility.GUIListener;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.UUID;

public class CustomLootTables extends JavaPlugin implements Listener {
    private CustomHeadsAPI api = (CustomHeadsAPI) Bukkit.getServer().getPluginManager().getPlugin("CustomHeadsAPI");
    public String translate(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
    public String prefix = translate("&f&lCLT");
    @Override
    public void onEnable() {
        if (!new File(getDataFolder(), "config.yml").exists()) {
            saveDefaultConfig();
        }
        getCommand("clt").setExecutor(new CLTCommands(this));
        Bukkit.getPluginManager().registerEvents(new MobLootTables(this), this);
        getServer().getPluginManager().registerEvents(new GUIListener(this), this);
    }
    @EventHandler
    public void onChestOpen(InventoryOpenEvent event) {
        Inventory chest = event.getInventory();

        // Check if the chest is a naturally generated chest
        if (chest.getType() == InventoryType.CHEST) {
            chest.clear();

            // Add custom items to the chest
            chest.addItem(new ItemStack(Material.EMERALD, 3));
            chest.addItem(new ItemStack(Material.IRON_INGOT, 7));
        }
    }
    String mobsdisplay = translate("&9&lEdit Mob Loot Tables");
    String chestsdisplay = translate("&9&lEdit Chest Loot Tables");
    public void openMobMenu(Player p){


        ItemStack item0 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1);
        ItemMeta meta0 = item0.getItemMeta();
        meta0.setDisplayName(" ");
        item0.setItemMeta(meta0);

        Inventory mobMenu = Bukkit.createInventory(p, 54, mobsdisplay);
        mobMenu.setItem(0, item0);
        mobMenu.setItem(1, item0);
        mobMenu.setItem(2, item0);
        mobMenu.setItem(3, item0);
        mobMenu.setItem(4, item0);
        mobMenu.setItem(5, item0);
        mobMenu.setItem(6, item0);
        mobMenu.setItem(9, item0);
        mobMenu.setItem(18, item0);
        mobMenu.setItem(27, item0);
        mobMenu.setItem(36, item0);
        mobMenu.setItem(45, item0);
        mobMenu.setItem(46, item0);
        mobMenu.setItem(8, item0);
        mobMenu.setItem(7, item0);
        mobMenu.setItem(17, item0);
        mobMenu.setItem(26, item0);
        mobMenu.setItem(35, item0);
        mobMenu.setItem(44, item0);
        mobMenu.setItem(52, item0);
        mobMenu.setItem(53, item0);
        p.openInventory(mobMenu);
    }
}
