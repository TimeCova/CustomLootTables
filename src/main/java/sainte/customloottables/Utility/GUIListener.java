package sainte.customloottables.Utility;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import sainte.customloottables.Commands.CLTCommands;
import sainte.customloottables.CustomLootTables;

public class GUIListener implements Listener {
    CustomLootTables plugin;
    public GUIListener(CustomLootTables plugin){
        this.plugin = plugin;
    }
    public String translate(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
    String chestguifull = translate("&9&lCLT » &rConfiguration Editor");
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        int slot = event.getSlot();
        if(event.getView().getTitle().equalsIgnoreCase(chestguifull)){
            switch (slot){
                case 20:
                    p.closeInventory();
                    plugin.openMobMenu(p);
                    break;
                case 24:

                    break;
                case 40:
                    p.closeInventory();
                default:
                    break;
            }
            event.setCancelled(true);
        }
    }
}
