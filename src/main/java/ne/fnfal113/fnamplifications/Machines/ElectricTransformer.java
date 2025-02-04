package ne.fnfal113.fnamplifications.Machines;

import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import ne.fnfal113.fnamplifications.Multiblock.FnAssemblyStation;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.RecipeDisplayItem;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerHead;
import io.github.thebusybiscuit.slimefun4.libraries.dough.skins.PlayerSkin;

import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.AContainer;
import me.mrCookieSlime.Slimefun.Objects.SlimefunItem.abstractItems.MachineRecipe;

import ne.fnfal113.fnamplifications.FNAmplifications;
import ne.fnfal113.fnamplifications.Items.FNAmpItems;

public class ElectricTransformer extends AContainer implements RecipeDisplayItem {

    private static final SlimefunAddon plugin = FNAmplifications.getInstance();

    public ElectricTransformer(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Nonnull
    @Override
    public List<ItemStack> getDisplayRecipes() {
        List<ItemStack> displayRecipes = new ArrayList<>(recipes.size() * 4);

        for (MachineRecipe recipe : recipes) {
            displayRecipes.add(recipe.getInput()[0]);
            displayRecipes.add(recipe.getInput()[1]);
            displayRecipes.add(new CustomItemStack(Material.PINK_STAINED_GLASS_PANE, "&eIndicator", "&fArrow below point towards the output of 2 vertical inputs"));
            displayRecipes.add(PlayerHead.getItemStack(PlayerSkin.fromHashCode(
                    "682ad1b9cb4dd21259c0d75aa315ff389c3cef752be3949338164bac84a96e")));
            displayRecipes.add(new CustomItemStack(Material.PINK_STAINED_GLASS_PANE, "&eIndicator", "&fNext item beside this glass is", "&fa 2 input recipe vertical 1 output horizontal"));
            displayRecipes.add(recipe.getOutput()[0]);
        }

        return displayRecipes;
    }

    @Override
    protected void registerDefaultRecipes() {
        registerRecipe(8, new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.AUTO_ENCHANTER, 1),
                        new SlimefunItemStack(SlimefunItems.CARBONADO, 6)},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.AUTO_ENCHANTER_2, 1)});
        registerRecipe(8, new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.AUTO_DISENCHANTER, 1),
                        new SlimefunItemStack(SlimefunItems.CARBONADO, 8)},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.AUTO_DISENCHANTER_2, 1)});
        registerRecipe(8, new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.AUTO_ANVIL, 1),
                        new SlimefunItemStack(SlimefunItems.ELECTRIC_MOTOR, 12)},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.AUTO_ANVIL_2, 1)});
        registerRecipe(8, new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.ELECTRO_MAGNET, 3),
                        new SlimefunItemStack(SlimefunItems.BRONZE_INGOT, 4)},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.CARGO_CONNECTOR_NODE, 4)});
        registerRecipe(8, new ItemStack[]{new SlimefunItemStack(SlimefunItems.CARGO_CONNECTOR_NODE, 1),
                        new ItemStack(Material.HOPPER, 2)},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.CARGO_INPUT_NODE, 2)});
        registerRecipe(8, new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.CARGO_OUTPUT_NODE, 1),
                        new SlimefunItemStack(SlimefunItems.CARGO_MOTOR, 2)},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.CARGO_OUTPUT_NODE_2, 1)});
        registerRecipe(8, new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.PROGRAMMABLE_ANDROID, 1),
                        new SlimefunItemStack(SlimefunItems.ANDROID_MEMORY_CORE, 2)},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.PROGRAMMABLE_ANDROID_2, 1)});
        registerRecipe(8, new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.PROGRAMMABLE_ANDROID_2, 1),
                        new SlimefunItemStack(SlimefunItems.ANDROID_MEMORY_CORE, 4)},
                new SlimefunItemStack[]{new SlimefunItemStack(SlimefunItems.PROGRAMMABLE_ANDROID_3, 1)});
    }

    public static void setup() {
        new ElectricTransformer(FNAmpItems.MACHINES, FNAmpItems.FN_FAL_TRANSFORMER_1, FnAssemblyStation.RECIPE_TYPE, new ItemStack[]{
                FNAmpItems.FUNNEL_PART, FNAmpItems.THREAD_PART, FNAmpItems.POWER_COMPONENT,
                FNAmpItems.GEAR_PART, FNAmpItems.BASIC_MACHINE_BLOCK, FNAmpItems.GEAR_PART,
                FNAmpItems.COMPRESSOR_PART, FNAmpItems.BRASS_PLATING, FNAmpItems.CONDENSER_PART
        }).setCapacity(1536).setEnergyConsumption(128).setProcessingSpeed(1).register(plugin);

        new ElectricTransformer(FNAmpItems.MACHINES, FNAmpItems.FN_FAL_TRANSFORMER_2, FnAssemblyStation.RECIPE_TYPE, new ItemStack[]{
                FNAmpItems.FUNNEL_PART, FNAmpItems.FN_FAL_TRANSFORMER_1, FNAmpItems.POWER_COMPONENT,
                new SlimefunItemStack(FNAmpItems.GEAR_PART, 2), FNAmpItems.BASIC_MACHINE_BLOCK, new SlimefunItemStack(FNAmpItems.GEAR_PART, 2),
                FNAmpItems.THREAD_PART, FNAmpItems.ALUMINUM_PLATING, FNAmpItems.CONDENSER_PART
        }).setCapacity(1536).setEnergyConsumption(192).setProcessingSpeed(2).register(plugin);

        new ElectricTransformer(FNAmpItems.MACHINES, FNAmpItems.FN_FAL_TRANSFORMER_3, FnAssemblyStation.RECIPE_TYPE, new ItemStack[]{
                new SlimefunItemStack(FNAmpItems.FUNNEL_PART, 2), FNAmpItems.FN_FAL_TRANSFORMER_2, FNAmpItems.POWER_COMPONENT,
                FNAmpItems.FN_FAL_TRANSFORMER_1, FNAmpItems.HIGHTECH_MACHINE_BLOCK, FNAmpItems.FN_FAL_TRANSFORMER_1,
                FNAmpItems.COMPONENT_PART, FNAmpItems.REINFORCED_CASING, new SlimefunItemStack(FNAmpItems.CONDENSER_PART, 2)
        }).setCapacity(1536).setEnergyConsumption(384).setProcessingSpeed(4).register(plugin);
    }

    @Nonnull
    @Override
    public String getMachineIdentifier() {
        return "ELECTRIC_TRANSFORMER";
    }

    @Override
    public ItemStack getProgressBar() {
        return new SlimefunItemStack(FNAmpItems.MACHINE_PART, 1);
    }
}
