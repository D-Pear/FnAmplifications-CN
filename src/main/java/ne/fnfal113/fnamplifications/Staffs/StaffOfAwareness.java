package ne.fnfal113.fnamplifications.Staffs;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import ne.fnfal113.fnamplifications.ConfigValues.ReturnConfValue;
import ne.fnfal113.fnamplifications.FNAmplifications;
import ne.fnfal113.fnamplifications.Items.FNAmpItems;
import ne.fnfal113.fnamplifications.Multiblock.FnAssemblyStation;
import org.bukkit.*;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.*;

public class StaffOfAwareness extends SlimefunItem {

    private static final SlimefunAddon plugin = FNAmplifications.getInstance();

    private static final ReturnConfValue value = new ReturnConfValue();

    private final NamespacedKey defaultUsageKey;

    public StaffOfAwareness(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        this.defaultUsageKey = new NamespacedKey(FNAmplifications.getInstance(), "awarestaff");
    }

    protected @Nonnull
    NamespacedKey getStorageKey() {
        return defaultUsageKey;
    }

    public void onRightClick(PlayerInteractEvent event){
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        NamespacedKey key = getStorageKey();
        Map<Entity, String> PLAYERS = new HashMap<>();
        List<String> players = new ArrayList<>();
        List<String> firstPage = new ArrayList<>();
        int amount = 0;

        if(item.getItemMeta() == null){
            return;
        }

        ItemMeta meta = item.getItemMeta();

        ItemStack writtenBook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) writtenBook.getItemMeta();
        if(bookMeta == null){
            return;
        }
        bookMeta.setTitle("Players around 50 block radius");
        bookMeta.setAuthor("FN_FAL113");

        for (Entity entity: player.getWorld().getNearbyEntities(player.getLocation().clone(), 50, 50, 50)) {
            if(entity instanceof Player && !entity.getName().equals(player.getName())){
                PLAYERS.put(entity, entity.getName());
                amount = amount + 1;
            }
        }

        if(amount != 0) {
            PLAYERS.forEach((key1, value1) -> players.add(ChatColor.DARK_GREEN + value1));

            firstPage.add(ChatColor.GOLD + "  Staff of Awareness\n\n " + ChatColor.GRAY +
                    "The power of staff yields the needed information around your 50 block radius vicinity in which upon players are nearby in your own knowing only");
            bookMeta.addPage(firstPageBook(firstPage));
            for (int i = 0; i < players.size(); i = i + 5) {
                    bookMeta.addPage(players.subList(i, Math.min(i + 5, players.size())).toString()
                            .replace("[", "")
                            .replace("]", "")
                            .replace(":", ChatColor.GRAY + " =")
                            .replace(", ", "\n\n")
                            .replace("_", " "));

            }
        } else {
            firstPage.add(ChatColor.GOLD + "  Staff of Awareness\n\n " + ChatColor.GRAY +
                    "No players around your vicinity");
            bookMeta.addPage(firstPageBook(firstPage));
        }

        updateMeta(item, meta, key, player);

        writtenBook.setItemMeta(bookMeta);
        player.openBook(writtenBook);

        Objects.requireNonNull(player.getLocation().getWorld()).playSound(player.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1, 1);

    }

    public String firstPageBook(List<String> firstPage){
        return firstPage.toString().replace("[", "").replace("]", "");
    }

    public void updateMeta(ItemStack item, ItemMeta meta, NamespacedKey key, Player player){
        PersistentDataContainer max_Uses = meta.getPersistentDataContainer();
        int uses_Left = max_Uses.getOrDefault(key, PersistentDataType.INTEGER, value.staffOfAwareness());
        int decrement = uses_Left - 1;

        List<String> lore = new ArrayList<>();

        if(decrement > 0) {
            max_Uses.set(key, PersistentDataType.INTEGER, decrement);
            lore.add(0, "");
            lore.add(1, ChatColor.LIGHT_PURPLE + "Right click to receive information");
            lore.add(2, ChatColor.LIGHT_PURPLE + "regarding the nearest players around");
            lore.add(3, ChatColor.LIGHT_PURPLE + "50 block radius");
            lore.add(4, "");
            lore.add(5, ChatColor.YELLOW + "Uses left: " + decrement);
            meta.setLore(lore);
            item.setItemMeta(meta);
        } else {
            player.getInventory().setItemInMainHand(null);
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lAwareness staff has reached max uses!"));
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1 ,1);
        }


    }

    public static void setup(){
        new StaffOfAwareness(FNAmpItems.FN_STAFFS, FNAmpItems.FN_STAFF_AWARENESS, FnAssemblyStation.RECIPE_TYPE, new ItemStack[]{
                new SlimefunItemStack(SlimefunItems.EARTH_RUNE, 2), new ItemStack(Material.BLAZE_POWDER, 16), new SlimefunItemStack(SlimefunItems.LIGHTNING_RUNE, 2),
                SlimefunItems.MAGICAL_GLASS, new ItemStack(Material.BLAZE_ROD), SlimefunItems.MAGICAL_GLASS,
                new SlimefunItemStack(SlimefunItems.FIRE_RUNE, 2), SlimefunItems.MAGIC_SUGAR, new SlimefunItemStack(SlimefunItems.AIR_RUNE, 2)})
                .register(plugin);
    }

}
