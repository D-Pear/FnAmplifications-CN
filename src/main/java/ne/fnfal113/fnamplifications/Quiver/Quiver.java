package ne.fnfal113.fnamplifications.Quiver;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import ne.fnfal113.fnamplifications.FNAmplifications;
import ne.fnfal113.fnamplifications.Items.FNAmpItems;
import ne.fnfal113.fnamplifications.Multiblock.FnAssemblyStation;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Quiver extends SlimefunItem {

    private static final SlimefunAddon plugin = FNAmplifications.getInstance();

    private final NamespacedKey defaultUsageKey;
    private final NamespacedKey defaultUsageKey2;

    public Quiver(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);

        this.defaultUsageKey = new NamespacedKey(FNAmplifications.getInstance(), "arrows");
        this.defaultUsageKey2 = new NamespacedKey(FNAmplifications.getInstance(), "arrowid");
    }

    protected @Nonnull
    NamespacedKey getStorageKey() {
        return defaultUsageKey;
    }

    protected @Nonnull
    NamespacedKey getStorageKey2() {
        return defaultUsageKey2;
    }

    public void onRightClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        NamespacedKey key = getStorageKey();
        NamespacedKey key2 = getStorageKey2();
        SlimefunItem sfCheck = SlimefunItem.getByItem(player.getInventory().getItemInMainHand());
        ItemStack itemState = player.getInventory().getItemInMainHand();
        boolean instance = sfCheck instanceof Quiver;
        boolean instance2 = sfCheck instanceof UpgradedQuiver;
        boolean actionRight = (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK);
        boolean actionLeft = (event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK);

        List<String> lore = new ArrayList<>();

        ItemMeta arrowMeta = itemState.getItemMeta();

        if(arrowMeta == null){
            return;
        }
        PersistentDataContainer arrows_Check = arrowMeta.getPersistentDataContainer();
        int arrowsCheckPDC = arrows_Check.getOrDefault(key, PersistentDataType.INTEGER, 0);
        boolean pdcCheck = arrowsCheckPDC != 0;

        if(actionRight) {
            if (pdcCheck && instance && itemState.getType() == Material.LEATHER) {

                if(player.isSneaking()) {
                    withdrawArrows(itemState, arrowMeta, lore, player, arrows_Check, key);
                    return;
                }

                itemState.setType(Material.ARROW);
                lore.add(0, "");
                lore.add(1, ChatColor.LIGHT_PURPLE + "右击 箭");
                lore.add(2, ChatColor.LIGHT_PURPLE + "储存至箭袋");
                lore.add(3, ChatColor.LIGHT_PURPLE + "shift+右击 取出箭");
                lore.add(4, "");
                lore.add(5, ChatColor.YELLOW + "左击/右击 切换形态");
                lore.add(6, ChatColor.YELLOW + "容量: 192 支");
                lore.add(7, ChatColor.YELLOW + "箭数: " + ChatColor.WHITE + arrowsCheckPDC);
                lore.add(8, ChatColor.YELLOW + "状态: 开启");
                arrowMeta.setLore(lore);
                itemState.setItemMeta(arrowMeta);
            }
        }

        if(actionLeft) {
            if (pdcCheck && instance && itemState.getType() == Material.ARROW) {

                if(player.isSneaking()) {
                    withdrawArrows(itemState, arrowMeta, lore, player, arrows_Check, key);
                    return;
                }

                itemState.setType(Material.LEATHER);
                lore.add(0, "");
                lore.add(1, ChatColor.LIGHT_PURPLE + "右击 箭");
                lore.add(2, ChatColor.LIGHT_PURPLE + "储存至箭袋");
                lore.add(3, ChatColor.LIGHT_PURPLE + "shift+右击 取出箭");
                lore.add(4, "");
                lore.add(5, ChatColor.YELLOW + "左击/右击 切换形态");
                lore.add(6, ChatColor.YELLOW + "容量: 192 支");
                lore.add(7, ChatColor.YELLOW + "箭数: " + ChatColor.WHITE + arrowsCheckPDC);
                lore.add(8, ChatColor.YELLOW + "状态: 关闭");
                arrowMeta.setLore(lore);
                itemState.setItemMeta(arrowMeta);

            }
        }

        if(actionRight) {

            if(itemState.getType() == Material.ARROW && !(instance) && !(instance2)) {
                for (int i = 0; i < player.getInventory().getContents().length; i++) {
                    SlimefunItem sfItem = SlimefunItem.getByItem(player.getInventory().getItem(i));
                    ItemStack item = player.getInventory().getItem(i);

                    if (sfItem instanceof Quiver) {
                        if (item != null && item.getAmount() == 1) {
                            ItemStack itemStack = player.getInventory().getItem(i);
                            if (itemStack == null) {
                                return;
                            }

                            ItemMeta meta = itemStack.getItemMeta();
                            if (meta == null) {
                                return;
                            }

                            PersistentDataContainer arrow_Left = meta.getPersistentDataContainer();
                            int arrows = arrow_Left.getOrDefault(key, PersistentDataType.INTEGER, 0);

                            if (arrows != 192) {
                                updateMetaArrows(itemStack, meta, key, key2, player);
                                break;
                            }

                        } // null and amount check
                    } // instance check

                } // loop
            } // instance and material type check
        } // event action check

    }

    public void withdrawArrows(ItemStack itemState, ItemMeta arrowMeta, List<String> lore, Player player, PersistentDataContainer arrows_Check, NamespacedKey key){
        int decrement = arrows_Check.getOrDefault(key, PersistentDataType.INTEGER, 0);
        int amount = decrement - 1;
        arrows_Check.set(key, PersistentDataType.INTEGER, amount);
        lore.add(0, "");
        lore.add(1, ChatColor.LIGHT_PURPLE + "右击 箭");
        lore.add(2, ChatColor.LIGHT_PURPLE + "储存至箭袋");
        lore.add(3, ChatColor.LIGHT_PURPLE + "shift+右击 取出箭");
        lore.add(4, "");
        lore.add(5, ChatColor.YELLOW + "左击/右击 切换形态");
        lore.add(6, ChatColor.YELLOW + "容量: 192 支");
        lore.add(7, ChatColor.YELLOW + "箭数: " + ChatColor.WHITE + amount);
        if(amount == 0){
            lore.add(8, ChatColor.YELLOW + "状态: 关闭(空)");
            itemState.setType(Material.LEATHER);
            player.sendMessage(ChatColor.GOLD + "箭袋已经空了！");
        } else if (itemState.getType() == Material.ARROW){
            lore.add(8, ChatColor.YELLOW + "状态: 开启");
        } else {
            lore.add(8, ChatColor.YELLOW + "状态: 关闭");
        }
        arrowMeta.setLore(lore);
        itemState.setItemMeta(arrowMeta);
        player.getInventory().addItem(new ItemStack(Material.ARROW, 1));
    }

    public void updateMetaArrows(ItemStack item, ItemMeta meta, NamespacedKey key, NamespacedKey key2, Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        PersistentDataContainer arrow_Left = meta.getPersistentDataContainer();
        int arrows = arrow_Left.getOrDefault(key, PersistentDataType.INTEGER, 0);
        int increment = arrows + 1;

        List<String> lore = new ArrayList<>();

        if (increment != 193) {
            arrow_Left.set(key, PersistentDataType.INTEGER, increment);
            lore.add(0, "");
            lore.add(1, ChatColor.LIGHT_PURPLE + "右击 箭");
            lore.add(2, ChatColor.LIGHT_PURPLE + "储存至箭袋");
            lore.add(3, ChatColor.LIGHT_PURPLE + "shift+右击 取出箭");
            lore.add(4, "");
            lore.add(5, ChatColor.YELLOW + "左击/右击 切换形态");
            lore.add(6, ChatColor.YELLOW + "容量: 192 支");
            lore.add(7, ChatColor.YELLOW + "箭数: " + ChatColor.WHITE + increment);
            lore.add(8, ChatColor.YELLOW + "状态: 开启");
            meta.setLore(lore);
            if(increment <= 2) {
                int random = ThreadLocalRandom.current().nextInt(1, 999999 + 1);
                meta.getPersistentDataContainer().set(key2, PersistentDataType.INTEGER, random);
            }
            item.setItemMeta(meta);
            item.setType(Material.ARROW);
            itemStack.setAmount(itemStack.getAmount() - 1);

            if(increment == 192){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&l箭袋已经满了!"));
            }

        }

    }

    public void bowShoot(EntityShootBowEvent event){
        ItemStack itemStack = event.getConsumable();
        ItemStack itemStack2 = event.getBow();
        SlimefunItem slimefunItem = SlimefunItem.getByItem(itemStack);

        if(itemStack == null){
            return;
        }

        if(itemStack2 == null){
            return;
        }

        if(slimefunItem instanceof Quiver) {
            Player player = (Player) event.getEntity();
            event.setConsumeItem(false);
            player.updateInventory();
            ItemMeta meta = itemStack.getItemMeta();
            ItemMeta meta2 = itemStack2.getItemMeta();
            NamespacedKey key = getStorageKey();

            if(meta == null){
                return;
            }

            if(meta2 == null){
                return;
            }

            if(!(meta2.hasEnchant(Enchantment.ARROW_INFINITE))) {
                PersistentDataContainer arrow_Left = meta.getPersistentDataContainer();
                int arrows = arrow_Left.getOrDefault(key, PersistentDataType.INTEGER, 0);
                int decrement = arrows - 1;

                List<String> lore = new ArrayList<>();

                if (decrement >= 0) {
                    arrow_Left.set(key, PersistentDataType.INTEGER, decrement);
                    lore.add(0, "");
                    lore.add(1, ChatColor.LIGHT_PURPLE + "右击 箭");
                    lore.add(2, ChatColor.LIGHT_PURPLE + "储存至箭袋");
                    lore.add(3, ChatColor.LIGHT_PURPLE + "shift+右击 取出箭");
                    lore.add(4, "");
                    lore.add(5, ChatColor.YELLOW + "左击/右击 切换形态");
                    lore.add(6, ChatColor.YELLOW + "容量: 192 Arrows");
                    lore.add(7, ChatColor.YELLOW + "箭数: " + ChatColor.WHITE + decrement);
                    lore.add(8, ChatColor.YELLOW + "状态: 开启");
                    meta.setLore(lore);
                    itemStack.setItemMeta(meta);

                    if (decrement == 0) {
                        itemStack.setType(Material.LEATHER);
                        lore.set(8, ChatColor.YELLOW + "状态: 关闭 (空)");
                        meta.setLore(lore);
                        itemStack.setItemMeta(meta);
                        player.sendMessage(ChatColor.GOLD + "箭袋现在是空的!");
                    }

                }
            }

        }

    }

    public static void setup() {
        new Quiver(FNAmpItems.FN_MISC, FNAmpItems.FN_QUIVER, FnAssemblyStation.RECIPE_TYPE, new ItemStack[]{
                new SlimefunItemStack(SlimefunItems.TIN_INGOT, 5), new ItemStack(Material.LEAD, 3), new SlimefunItemStack(SlimefunItems.COPPER_INGOT, 5),
                new ItemStack(Material.STRING, 16), new ItemStack(Material.STICK, 24),  new ItemStack(Material.STRING, 16),
                new SlimefunItemStack(SlimefunItems.TIN_INGOT, 5), new ItemStack(Material.LEATHER, 16), new SlimefunItemStack(SlimefunItems.COPPER_INGOT, 5)})
                .register(plugin);
    }
}