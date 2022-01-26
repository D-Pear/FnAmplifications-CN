package ne.fnfal113.fnamplifications.Staffs;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import ne.fnfal113.fnamplifications.config.ConfigManager;
import ne.fnfal113.fnamplifications.FNAmplifications;
import ne.fnfal113.fnamplifications.Items.FNAmpItems;
import ne.fnfal113.fnamplifications.Multiblock.FnAssemblyStation;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StaffOfMuster extends SlimefunItem {

    private static final SlimefunAddon plugin = FNAmplifications.getInstance();

    private static final ConfigManager value = FNAmplifications.getInstance().getConfigManager();

    private final NamespacedKey defaultUsageKey;

    public StaffOfMuster(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        this.defaultUsageKey = new NamespacedKey(FNAmplifications.getInstance(), "musterstaff");
    }

    protected @Nonnull
    NamespacedKey getStorageKey() {
        return defaultUsageKey;
    }

    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        NamespacedKey key = getStorageKey();
        Block block = event.getPlayer().getTargetBlockExact(50);

        if(block == null || item.getType() == Material.AIR){
            return;
        }

        if (!Slimefun.getProtectionManager().hasPermission(
                Bukkit.getOfflinePlayer(player.getUniqueId()),
                block,
                Interaction.BREAK_BLOCK)
        ) {
            player.sendMessage(ChatColor.DARK_RED + "You don't have permission to cast muster there!");
            return;
        }

        if(item.getItemMeta() == null){
            return;
        }

        int amount = 0;
        for (Entity entity : block.getWorld().getNearbyEntities(block.getLocation(), 50, 50, 50)) {
            if(Slimefun.getProtectionManager().hasPermission
                    (Bukkit.getOfflinePlayer(player.getUniqueId()),
                            entity.getLocation(),
                            Interaction.INTERACT_ENTITY)) {

                if (entity instanceof LivingEntity && !(entity instanceof ArmorStand)
                        && !((LivingEntity) entity).isLeashed() && !entity.isInWater() && !(entity instanceof Player)) {
                    entity.teleport(block.getLocation().clone().add(0.5, 1, 0.5));
                    amount = amount + 1;
                } // instanceof check
            } // permission check
        } // for each

        player.sendMessage(ChatColor.GREEN + "Mustered " + amount + " entities");

        block.getWorld().playEffect(block.getLocation().clone().add(0.5, 1, 0.5), Effect.ENDER_SIGNAL, 1);

        ItemMeta meta = item.getItemMeta();

        updateMeta(item, meta, key, player);

        Objects.requireNonNull(player.getLocation().getWorld()).playSound(player.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1, 1);

    }

    public void updateMeta(ItemStack item, ItemMeta meta, NamespacedKey key, Player player){
        PersistentDataContainer max_Uses = meta.getPersistentDataContainer();
        int uses_Left = max_Uses.getOrDefault(key, PersistentDataType.INTEGER, value.staffOfMuster());
        int decrement = uses_Left - 1;

        List<String> lore = new ArrayList<>();

        if(decrement > 0) {
            max_Uses.set(key, PersistentDataType.INTEGER, decrement);
            lore.add(0, "");
            lore.add(1, ChatColor.LIGHT_PURPLE + "Right click a target block to teleport");
            lore.add(2, ChatColor.LIGHT_PURPLE + "nearby entities that are on ground");
            lore.add(3, ChatColor.LIGHT_PURPLE + "within 50 block radius");
            lore.add(4, "");
            lore.add(5, ChatColor.YELLOW + "Uses left: " + decrement);
            meta.setLore(lore);
            item.setItemMeta(meta);
        } else {
            player.getInventory().setItemInMainHand(null);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lMuster staff has reached max uses!"));
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1 ,1);
        }


    }

    public static void setup(){
        new StaffOfMuster(FNAmpItems.FN_STAFFS, FNAmpItems.FN_STAFF_MUSTER, FnAssemblyStation.RECIPE_TYPE, new ItemStack[]{
                new SlimefunItemStack(SlimefunItems.ENDER_RUNE, 2), new ItemStack(Material.ENDER_PEARL, 16), new SlimefunItemStack(SlimefunItems.ENDER_RUNE, 2),
                SlimefunItems.MAGIC_EYE_OF_ENDER, new ItemStack(Material.BLAZE_ROD), SlimefunItems.MAGIC_EYE_OF_ENDER,
                new SlimefunItemStack(SlimefunItems.ENDER_LUMP_3, 10), SlimefunItems.MAGIC_SUGAR, new SlimefunItemStack(SlimefunItems.ENDER_LUMP_3, 10)})
                .register(plugin);
    }

}
