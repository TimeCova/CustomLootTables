package sainte.customloottables.Commands;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import net.minecraft.*;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.checkerframework.checker.units.qual.A;
import sainte.customheadsapi.CustomHeadsAPI;
import sainte.customloottables.CustomLootTables;

import java.io.ByteArrayInputStream;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;

public class CLTCommands implements CommandExecutor {

    private CustomHeadsAPI api = (CustomHeadsAPI) Bukkit.getServer().getPluginManager().getPlugin("CustomHeadsAPI");

    String mobsdisplay = translate("&9&lEdit Mob Loot Tables");
    String chestsdisplay = translate("&9&lEdit Chest Loot Tables");
    private final CustomLootTables plugin;
    public CLTCommands(CustomLootTables plugin) {
        this.plugin = plugin;
    }

    // eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzQ4NDNlYTVmZTA5MzMxNWQ4YjNmNDAxNWIyYTZjMmNjMjNhZTUyZThhYWIxNDczYmJmMmY2MjM1NDJmNTI1YiJ9fX0=
    String titleMob = translate("&9&lEdit Mob Loot Tables");
    public void openMobMenu(Player p){
        Inventory openMobMenu = Bukkit.createInventory(null, 54, titleMob);

        p.openInventory(openMobMenu);
    }

    public String translate(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
    String reload = translate("&9&lCLT » &rThe config has been successfully reloaded!");
    public String prefix = translate("&9&lCLT » ");
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            return false;
        }

        switch (args[0].toLowerCase()) {
            case "reload":
                if (sender.hasPermission("clt.reload")) {
                    plugin.reloadConfig();
                    sender.sendMessage(reload);
                } else {
                    sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                }
                break;
            case "edit":
                Player p = (Player) sender;
                if (sender instanceof Player) {
                    if (!p.hasPermission("clt.edit")){
                        sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
                    }
                    String chestguifull = translate("&9&lCLT » &rConfiguration Editor");
                    String close = translate("&9Close Configuration Editor");
                    Inventory inventory = Bukkit.createInventory(null, 54, chestguifull);
                    // Set the first item
                    ItemStack item0 = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE, 1);
                    ItemMeta meta0 = item0.getItemMeta();
                    meta0.setDisplayName(" ");
                    item0.setItemMeta(meta0);



                    ItemStack Barrier = new ItemStack(Material.BARRIER);
                    ItemMeta BarrierM = Barrier.getItemMeta();
                    BarrierM.setDisplayName(close);
                    Barrier.setItemMeta(BarrierM);

                    ItemStack mobs = api.headManager.getCustomTextureHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODdjNjNkOTA3OWI3NWY5MDk3OTc4M2NmMDdjYTcyNmY2NWUzMDI0NDE1YWM2MjJhN2M5MDZjZDI1MDgyYWYifX19");
                    ItemMeta customHeadMeta = mobs.getItemMeta();
                    ArrayList<String> mob_lore = new ArrayList<>();
                    mob_lore.add(ChatColor.AQUA + "Click to configure the loot tables of chests.");
                    mob_lore.add(ChatColor.AQUA + "This changes the config.yml file.");
                    customHeadMeta.setLore(mob_lore);
                    customHeadMeta.setDisplayName(mobsdisplay);
                    mobs.setItemMeta(customHeadMeta);

                    ItemStack chests = api.headManager.getCustomTextureHead("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZWRjMzZjOWNiNTBhNTI3YWE1NTYwN2EwZGY3MTg1YWQyMGFhYmFhOTAzZThkOWFiZmM3ODI2MDcwNTU0MGRlZiJ9fX0=");
                    ItemMeta customHeadMeta1 = mobs.getItemMeta();
                    ArrayList<String> chest_lore = new ArrayList<>();
                    chest_lore.add(ChatColor.AQUA + "Click to configure the loot tables of chests.");
                    chest_lore.add(ChatColor.AQUA + "This changes the config.yml file.");
                    customHeadMeta1.setLore(chest_lore);
                    customHeadMeta1.setDisplayName(chestsdisplay);
                    mobs.setItemMeta(customHeadMeta1);

                    //upval eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNmNjYmY5ODgzZGQzNTlmZGYyMzg1YzkwYTQ1OWQ3Mzc3NjUzODJlYzQxMTdiMDQ4OTVhYzRkYzRiNjBmYyJ9fX0=
                    //downval eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzI0MzE5MTFmNDE3OGI0ZDJiNDEzYWE3ZjVjNzhhZTQ0NDdmZTkyNDY5NDNjMzFkZjMxMTYzYzBlMDQzZTBkNiJ9fX0=
                    //leftval eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzdhZWU5YTc1YmYwZGY3ODk3MTgzMDE1Y2NhMGIyYTdkNzU1YzYzMzg4ZmYwMTc1MmQ1ZjQ0MTlmYzY0NSJ9fX0=
                    //rightval eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjgyYWQxYjljYjRkZDIxMjU5YzBkNzVhYTMxNWZmMzg5YzNjZWY3NTJiZTM5NDkzMzgxNjRiYWM4NGE5NmUifX19
                    //backward eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODY0Zjc3OWE4ZTNmZmEyMzExNDNmYTY5Yjk2YjE0ZWUzNWMxNmQ2NjllMTljNzVmZDFhN2RhNGJmMzA2YyJ9fX0=
                    //forwardeyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDllY2NjNWMxYzc5YWE3ODI2YTE1YTdmNWYxMmZiNDAzMjgxNTdjNTI0MjE2NGJhMmFlZjQ3ZTVkZTlhNWNmYyJ9fX0=

                    inventory.setItem(20, mobs);
                    inventory.setItem(24, chests);
                    inventory.setItem(40, Barrier);
                    inventory.setItem(0, item0);
                    inventory.setItem(2, item0);
                    inventory.setItem(3, item0);
                    inventory.setItem(4, item0);
                    inventory.setItem(5, item0);
                    inventory.setItem(6, item0);
                    inventory.setItem(1, item0);
                    inventory.setItem(9, item0);
                    inventory.setItem(18, item0);
                    inventory.setItem(27, item0);
                    inventory.setItem(36, item0);
                    inventory.setItem(45, item0);
                    inventory.setItem(46, item0);
                    inventory.setItem(8, item0);
                    inventory.setItem(7, item0);
                    inventory.setItem(17, item0);
                    inventory.setItem(26, item0);
                    inventory.setItem(35, item0);
                    inventory.setItem(44, item0);
                    inventory.setItem(52, item0);
                    inventory.setItem(53, item0);
                    p.openInventory(inventory);
                    break;
                }
            default:
                return false;
        }
        return true;
    }
}
