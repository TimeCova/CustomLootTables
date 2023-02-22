package sainte.customloottables.MobLootTables;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import sainte.customloottables.CustomLootTables;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class MobLootTables implements Listener {

    private CustomLootTables plugin;

    public MobLootTables(CustomLootTables plugin) {
        this.plugin = plugin;
    }
    private static final Random random = new Random();

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        EntityType entityType = event.getEntityType();
        String entityName = entityType.toString();
        if (plugin.getConfig().contains(entityName)) {
            boolean keepOriginalItems = plugin.getConfig().getBoolean(entityName + ".keepOriginalItems");
            ConfigurationSection config = plugin.getConfig().getConfigurationSection(entityName);

            List<ItemStack> customDrops = new ArrayList<>();
            for (String key : config.getKeys(false)) {
                if (config.contains(key) && config.isConfigurationSection(key)) {
                    ConfigurationSection itemSection = config.getConfigurationSection(key);
                    Material material = Material.getMaterial(key);
                    int maxAmount = itemSection.getInt("MaxAmount");
                    int minAmount = itemSection.getInt("MinAmount");
                    int chance = itemSection.getInt("chance");
                    System.out.println("Checking chance for " + key + " with chance " + chance);
                    if (random.nextInt(100) < chance) {
                        int randomAmount = random.nextInt(maxAmount - minAmount + 1) + minAmount;
                        System.out.println("Adding " + randomAmount + " of " + key + " to drops");
                        ItemStack itemStack = new ItemStack(material, randomAmount);
                        if (itemSection.contains("display_name")) {
                            String displayName = itemSection.getString("display_name");
                            ItemMeta itemMeta = itemStack.getItemMeta();
                            itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayName));
                            itemStack.setItemMeta(itemMeta);
                        }
                        if (itemSection.contains("lore")) {
                            List<String> lore = itemSection.getStringList("lore");
                            ItemMeta itemMeta = itemStack.getItemMeta();
                            itemMeta.setLore(lore.stream().map(line -> ChatColor.translateAlternateColorCodes('&', line)).collect(Collectors.toList()));
                            itemStack.setItemMeta(itemMeta);
                        }
                        customDrops.add(itemStack);
                    }
                }
            }

            if (!keepOriginalItems) {
                event.getDrops().clear();
            }
            event.getDrops().addAll(customDrops);
        }
    }


}
