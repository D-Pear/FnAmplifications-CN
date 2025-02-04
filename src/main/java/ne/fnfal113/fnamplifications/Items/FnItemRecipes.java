package ne.fnfal113.fnamplifications.Items;

import ne.fnfal113.fnamplifications.ConfigValues.ReturnConfValue;
import ne.fnfal113.fnamplifications.Machines.ElectricMachineDowngrader;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.MinecraftVersion;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.implementation.items.blocks.UnplaceableBlock;

import ne.fnfal113.fnamplifications.FNAmplifications;

public class FnItemRecipes {

    private static final SlimefunAddon plugin = FNAmplifications.getInstance();
    private static final ReturnConfValue value = new ReturnConfValue();

    public static final ItemStack VERSIONED_ITEMSTACK_COPPER;
    public static final ItemStack VERSIONED_ITEMSTACK_COPPER_BATTERY;
    public static final ItemStack VERSIONED_ITEMSTACK_COPPER_BLOCK_INGOT;
    public static final ItemStack VERSIONED_ITEMSTACK_AMETHYSTSHARD_IRON;
    public static final ItemStack VERSIONED_ITEMSTACK_AMETHYSTSHARD_IRONBLOCK;
    public static final ItemStack VERSIONED_ITEMSTACK_CALCITE_IRONINGOT;
    public static final ItemStack VERSIONED_ITEMSTACK_ROD_BATTERY;
    public static final ItemStack VERSIONED_ITEMSTACK_AMETHYSTCLUSTER_IRON;

    static {
        if (Slimefun.getMinecraftVersion().isAtLeast(MinecraftVersion.MINECRAFT_1_17) && value.latestMcVersionRecipe()) {
            VERSIONED_ITEMSTACK_COPPER = new ItemStack(Material.COPPER_INGOT);
            VERSIONED_ITEMSTACK_COPPER_BATTERY = new ItemStack(Material.COPPER_INGOT);
            VERSIONED_ITEMSTACK_COPPER_BLOCK_INGOT = new ItemStack(Material.COPPER_BLOCK);
            VERSIONED_ITEMSTACK_AMETHYSTSHARD_IRON = new ItemStack(Material.AMETHYST_SHARD);
            VERSIONED_ITEMSTACK_CALCITE_IRONINGOT = new ItemStack(Material.CALCITE);
            VERSIONED_ITEMSTACK_ROD_BATTERY = new ItemStack(Material.LIGHTNING_ROD);
            VERSIONED_ITEMSTACK_AMETHYSTCLUSTER_IRON = new ItemStack(Material.AMETHYST_CLUSTER);
            VERSIONED_ITEMSTACK_AMETHYSTSHARD_IRONBLOCK = new ItemStack(Material.AMETHYST_SHARD);
        } else {
            VERSIONED_ITEMSTACK_COPPER = SlimefunItems.COPPER_INGOT;
            VERSIONED_ITEMSTACK_COPPER_BATTERY = SlimefunItems.BATTERY;
            VERSIONED_ITEMSTACK_COPPER_BLOCK_INGOT = SlimefunItems.COPPER_INGOT;
            VERSIONED_ITEMSTACK_AMETHYSTSHARD_IRON = new ItemStack(Material.IRON_NUGGET);
            VERSIONED_ITEMSTACK_CALCITE_IRONINGOT = new ItemStack(Material.IRON_INGOT);
            VERSIONED_ITEMSTACK_ROD_BATTERY = SlimefunItems.BATTERY;
            VERSIONED_ITEMSTACK_AMETHYSTCLUSTER_IRON = new ItemStack(Material.IRON_INGOT);
            VERSIONED_ITEMSTACK_AMETHYSTSHARD_IRONBLOCK = new ItemStack(Material.IRON_BLOCK);
        }
    }

    public static void setup() {
        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.MACHINE_PART, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.BRONZE_INGOT, SlimefunItems.GOLD_4K, SlimefunItems.BRONZE_INGOT,
                SlimefunItems.ADVANCED_CIRCUIT_BOARD, SlimefunItems.ELECTRO_MAGNET, SlimefunItems.ADVANCED_CIRCUIT_BOARD,
                SlimefunItems.COPPER_WIRE, VERSIONED_ITEMSTACK_ROD_BATTERY, SlimefunItems.COPPER_WIRE})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.COMPONENT_PART, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                VERSIONED_ITEMSTACK_COPPER, new ItemStack(Material.GOLD_INGOT), VERSIONED_ITEMSTACK_COPPER,
                SlimefunItems.BASIC_CIRCUIT_BOARD, SlimefunItems.MAGNET, SlimefunItems.BASIC_CIRCUIT_BOARD,
                SlimefunItems.COPPER_WIRE, VERSIONED_ITEMSTACK_ROD_BATTERY, SlimefunItems.COPPER_WIRE})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.MOTOR_SWITCH, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.ALUMINUM_BRASS_INGOT, SlimefunItems.ELECTRIC_MOTOR,
                new ItemStack(Material.LEVER), new ItemStack(Material.REDSTONE), new ItemStack(Material.LEVER),
                SlimefunItems.COPPER_WIRE, VERSIONED_ITEMSTACK_ROD_BATTERY, SlimefunItems.COPPER_WIRE})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.GEAR_PART, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.ELECTRIC_MOTOR, SlimefunItems.CARGO_MOTOR, SlimefunItems.ELECTRIC_MOTOR,
                new ItemStack(Material.REDSTONE), SlimefunItems.CHAIN, new ItemStack(Material.REDSTONE),
                SlimefunItems.COPPER_WIRE, FNAmpItems.MOTOR_SWITCH, SlimefunItems.COPPER_WIRE})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.THREAD_PART, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                new ItemStack(Material.OAK_PLANKS), new ItemStack(Material.STICK), new ItemStack(Material.OAK_PLANKS),
                SlimefunItems.COPPER_WIRE, new ItemStack(Material.STICK), SlimefunItems.COPPER_WIRE,
                SlimefunItems.COPPER_WIRE, new ItemStack(Material.STICK), SlimefunItems.COPPER_WIRE})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.COMPRESSOR_PART, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                new ItemStack(Material.PISTON), new ItemStack(Material.NETHER_BRICKS), new ItemStack(Material.PISTON),
                FNAmpItems.THREAD_PART, FNAmpItems.GEAR_PART, FNAmpItems.THREAD_PART,
                SlimefunItems.COPPER_WIRE, new ItemStack(Material.NETHER_BRICKS), SlimefunItems.COPPER_WIRE})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.CONDENSER_PART, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                new ItemStack(Material.PISTON), FNAmpItems.COMPONENT_PART, new ItemStack(Material.PISTON),
                FNAmpItems.COMPRESSOR_PART, FNAmpItems.GEAR_PART, FNAmpItems.COMPRESSOR_PART,
                SlimefunItems.COPPER_WIRE, FNAmpItems.COMPONENT_PART, SlimefunItems.COPPER_WIRE})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.RECYCLER_PART, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                new ItemStack(Material.PISTON), FNAmpItems.COMPONENT_PART, new ItemStack(Material.PISTON),
                FNAmpItems.COMPRESSOR_PART, FNAmpItems.GEAR_PART, FNAmpItems.COMPRESSOR_PART,
                FNAmpItems.CONDENSER_PART, FNAmpItems.COMPONENT_PART, FNAmpItems.CONDENSER_PART})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.DOWNGRADER_PART, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                new ItemStack(Material.PISTON), FNAmpItems.THREAD_PART, new ItemStack(Material.PISTON),
                FNAmpItems.RECYCLER_PART, FNAmpItems.GEAR_PART, FNAmpItems.RECYCLER_PART,
                FNAmpItems.CONDENSER_PART, FNAmpItems.COMPRESSOR_PART, FNAmpItems.CONDENSER_PART})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.FUNNEL_PART, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.LEAD_INGOT, VERSIONED_ITEMSTACK_CALCITE_IRONINGOT, SlimefunItems.LEAD_INGOT,
                FNAmpItems.THREAD_PART, new ItemStack(Material.BARREL), FNAmpItems.THREAD_PART,
                SlimefunItems.COPPER_WIRE, VERSIONED_ITEMSTACK_CALCITE_IRONINGOT, SlimefunItems.COPPER_WIRE})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.POWER_COMPONENT, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.ELECTRIC_MOTOR, VERSIONED_ITEMSTACK_COPPER_BATTERY, SlimefunItems.ELECTRIC_MOTOR,
                VERSIONED_ITEMSTACK_COPPER, SlimefunItems.POWER_CRYSTAL, VERSIONED_ITEMSTACK_COPPER,
                SlimefunItems.COPPER_WIRE, VERSIONED_ITEMSTACK_ROD_BATTERY, SlimefunItems.COPPER_WIRE})
                .register(plugin);
        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.BRASS_PLATING, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                VERSIONED_ITEMSTACK_COPPER, VERSIONED_ITEMSTACK_AMETHYSTSHARD_IRON, VERSIONED_ITEMSTACK_COPPER,
                SlimefunItems.BRASS_INGOT, VERSIONED_ITEMSTACK_COPPER_BLOCK_INGOT, SlimefunItems.BRASS_INGOT,
                SlimefunItems.BRONZE_INGOT, VERSIONED_ITEMSTACK_AMETHYSTSHARD_IRON, SlimefunItems.BRONZE_INGOT})
                .register(plugin);
        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.REINFORCED_CASING, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.REINFORCED_PLATE, VERSIONED_ITEMSTACK_COPPER, SlimefunItems.REINFORCED_PLATE,
                SlimefunItems.REINFORCED_ALLOY_INGOT, new ItemStack(Material.IRON_BLOCK), SlimefunItems.BRASS_INGOT,
                SlimefunItems.BRONZE_INGOT, VERSIONED_ITEMSTACK_COPPER, SlimefunItems.BRONZE_INGOT})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.DIAMOND_PLATING, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.COBALT_INGOT, VERSIONED_ITEMSTACK_AMETHYSTCLUSTER_IRON, SlimefunItems.COBALT_INGOT,
                new ItemStack(Material.DIAMOND), new ItemStack(Material.DIAMOND_BLOCK), new ItemStack(Material.DIAMOND),
                SlimefunItems.BRONZE_INGOT, VERSIONED_ITEMSTACK_AMETHYSTCLUSTER_IRON, SlimefunItems.BRONZE_INGOT})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.ALUMINUM_PLATING, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.DURALUMIN_INGOT, VERSIONED_ITEMSTACK_AMETHYSTCLUSTER_IRON, SlimefunItems.DURALUMIN_INGOT,
                SlimefunItems.ALUMINUM_INGOT, VERSIONED_ITEMSTACK_AMETHYSTCLUSTER_IRON, SlimefunItems.ALUMINUM_INGOT,
                SlimefunItems.BRONZE_INGOT, VERSIONED_ITEMSTACK_AMETHYSTCLUSTER_IRON, SlimefunItems.BRONZE_INGOT})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.GOLD_PLATING, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                SlimefunItems.GOLD_8K, new ItemStack(Material.GOLD_NUGGET), SlimefunItems.GOLD_8K,
                SlimefunItems.ALUMINUM_INGOT, new ItemStack(Material.GOLD_BLOCK), SlimefunItems.ALUMINUM_INGOT,
                SlimefunItems.BRONZE_INGOT, new ItemStack(Material.GOLD_NUGGET), SlimefunItems.BRONZE_INGOT})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.BASIC_MACHINE_BLOCK, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                FNAmpItems.GEAR_PART, VERSIONED_ITEMSTACK_AMETHYSTSHARD_IRONBLOCK, FNAmpItems.GEAR_PART,
                FNAmpItems.BRASS_PLATING, FNAmpItems.MACHINE_PART, FNAmpItems.BRASS_PLATING,
                FNAmpItems.COMPONENT_PART, VERSIONED_ITEMSTACK_AMETHYSTSHARD_IRONBLOCK, FNAmpItems.COMPONENT_PART})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.HIGHTECH_MACHINE_BLOCK, RecipeType.ENHANCED_CRAFTING_TABLE, new ItemStack[]{
                FNAmpItems.GEAR_PART, FNAmpItems.BASIC_MACHINE_BLOCK, FNAmpItems.GEAR_PART,
                FNAmpItems.REINFORCED_CASING, FNAmpItems.MACHINE_PART, FNAmpItems.BRASS_PLATING,
                FNAmpItems.COMPONENT_PART, FNAmpItems.BASIC_MACHINE_BLOCK, FNAmpItems.COMPONENT_PART})
                .register(plugin);

        new UnplaceableBlock(FNAmpItems.ITEMS, FNAmpItems.FN_METAL_SCRAPS, ElectricMachineDowngrader.RECIPE_TYPE, new ItemStack[]{
                null, null, null,
                null, null, null,
                null, null, null})
                .register(plugin);

    }
}
