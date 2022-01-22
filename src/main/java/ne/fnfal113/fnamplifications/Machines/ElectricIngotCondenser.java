package ne.fnfal113.fnamplifications.Machines;

import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import ne.fnfal113.fnamplifications.Multiblock.FnAssemblyStation;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;

import ne.fnfal113.fnamplifications.FNAmplifications;
import ne.fnfal113.fnamplifications.Items.FNAmpItems;

public class ElectricIngotCondenser extends AContainer implements RecipeDisplayItem {

    private static final SlimefunAddon plugin = FNAmplifications.getInstance();

    public static final ItemStack VERSIONED_ITEMSTACK_COPPER;

    static {
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_17)) {
            VERSIONED_ITEMSTACK_COPPER = new ItemStack(Material.COPPER_INGOT, 2);
        } else {
            VERSIONED_ITEMSTACK_COPPER = new SlimefunItemStack(SlimefunItems.COPPER_INGOT, 2);
        }
    }

    public ElectricIngotCondenser(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new ArrayList<>(recipes.size() * 4);

        for (MachineRecipe recipe : recipes) {
            displayRecipes.add(recipe.getInput()[0]);
            displayRecipes.add(recipe.getInput()[1]);
            displayRecipes.add(new CustomItemStack(Material.PINK_STAINED_GLASS_PANE, "&e提示", "&f下面的箭头指向两个垂直输入的输出"));
            displayRecipes.add(PlayerHead.getItemStack(PlayerSkin.fromHashCode(
                    "682ad1b9cb4dd21259c0d75aa315ff389c3cef752be3949338164bac84a96e")));
            displayRecipes.add(new CustomItemStack(Material.PINK_STAINED_GLASS_PANE, "&e提示", "&f两个垂直方向的物品是输入", "&f该提示下方的物品是输出"));
            displayRecipes.add(recipe.getOutput()[0]);
        }

        return displayRecipes;
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(12, new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.GOLD_10K, 1), new SlimefunItemStack(SlimefunItems.GOLD_14K, 1)},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.GOLD_24K, 1)});
        registerRecipe(12, new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.BLISTERING_INGOT, 1), new SlimefunItemStack(SlimefunItems.URANIUM, 9)},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.BLISTERING_INGOT_3, 1)});
        registerRecipe(12, new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.DAMASCUS_STEEL_INGOT, 2), new SlimefunItemStack(SlimefunItems.COMPRESSED_CARBON, 1)},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.HARDENED_METAL_INGOT, 1)});
        registerRecipe(12, new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.STEEL_INGOT, 2), new SlimefunItemStack(SlimefunItems.CARBON, 1)},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.DAMASCUS_STEEL_INGOT, 2)});
        registerRecipe(12, new ItemStack[]{new SlimefunItemStack(SlimefunItems.BRONZE_INGOT, 1), VERSIONED_ITEMSTACK_COPPER},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.CORINTHIAN_BRONZE_INGOT, 1)});
        registerRecipe(12, new ItemStack[]{new ItemStack(Material.IRON_INGOT, 1), VERSIONED_ITEMSTACK_COPPER},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.NICKEL_INGOT, 1)});
        registerRecipe(12, new ItemStack[]{new SlimefunItemStack(SlimefunItems.NICKEL_INGOT, 1), VERSIONED_ITEMSTACK_COPPER},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.COBALT_INGOT, 1)});
        registerRecipe(12, new ItemStack[]{new SlimefunItemStack(SlimefunItems.ALUMINUM_INGOT, 1), VERSIONED_ITEMSTACK_COPPER},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.DURALUMIN_INGOT, 1)});
        registerRecipe(12, new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.ALUMINUM_INGOT, 1), new SlimefunItemStack(SlimefunItems.BRONZE_INGOT, 1)},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.ALUMINUM_BRONZE_INGOT, 1)});
        registerRecipe(12, new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.ALUMINUM_INGOT, 1), new SlimefunItemStack(SlimefunItems.BRASS_INGOT, 1)},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.ALUMINUM_BRASS_INGOT, 1)});
    }

    public static void setup() {
        new ElectricIngotCondenser(FNAmpItems.MACHINES, FNAmpItems.FN_FAL_CONDENSER_1, FnAssemblyStation.RECIPE_TYPE, new ItemStack[]{
                FNAmpItems.MOTOR_SWITCH, FNAmpItems.THREAD_PART, FNAmpItems.FUNNEL_PART,
                FNAmpItems.GEAR_PART, FNAmpItems.BASIC_MACHINE_BLOCK, FNAmpItems.POWER_COMPONENT,
                FNAmpItems.COMPRESSOR_PART, FNAmpItems.GOLD_PLATING, FNAmpItems.COMPRESSOR_PART})
        .setCapacity(1536).setEnergyConsumption(128).setProcessingSpeed(1).register(plugin);

        new ElectricIngotCondenser(FNAmpItems.MACHINES, FNAmpItems.FN_FAL_CONDENSER_2, FnAssemblyStation.RECIPE_TYPE, new ItemStack[]{
                FNAmpItems.MOTOR_SWITCH, FNAmpItems.FN_FAL_CONDENSER_1, FNAmpItems.FUNNEL_PART,
                new SlimefunItemStack(FNAmpItems.GEAR_PART, 2), FNAmpItems.BASIC_MACHINE_BLOCK, new SlimefunItemStack(FNAmpItems.POWER_COMPONENT, 2),
                FNAmpItems.THREAD_PART, FNAmpItems.DIAMOND_PLATING, FNAmpItems.COMPONENT_PART})
        .setCapacity(1536).setEnergyConsumption(192).setProcessingSpeed(2).register(plugin);

        new ElectricIngotCondenser(FNAmpItems.MACHINES, FNAmpItems.FN_FAL_CONDENSER_3, FnAssemblyStation.RECIPE_TYPE, new ItemStack[]{
                FNAmpItems.FN_FAL_CONDENSER_1, FNAmpItems.FN_FAL_CONDENSER_2, FNAmpItems.FN_FAL_CONDENSER_1,
                new SlimefunItemStack(FNAmpItems.GEAR_PART, 3), FNAmpItems.HIGHTECH_MACHINE_BLOCK, new SlimefunItemStack(FNAmpItems.POWER_COMPONENT, 3),
                FNAmpItems.THREAD_PART, FNAmpItems.REINFORCED_CASING, FNAmpItems.COMPONENT_PART})
        .setCapacity(1536).setEnergyConsumption(384).setProcessingSpeed(4).register(plugin);
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "ELECTRIC_INGOT_CONDENSER";
    }

    @Override
    public ItemStack getProgressBar() {
        return new SlimefunItemStack(FNAmpItems.CONDENSER_PART, 1);
    }
}
