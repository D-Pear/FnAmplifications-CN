package ne.fnfal113.fnamplifications;

import javax.annotation.Nonnull;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.libraries.dough.updater.GitHubBuildsUpdater;

import ne.fnfal113.fnamplifications.Gears.Listeners.GearListener;
import ne.fnfal113.fnamplifications.Gears.Runnables.ArmorEquipRunnable;
import ne.fnfal113.fnamplifications.MysteriousItems.Listeners.MysteryStickListener;
import ne.fnfal113.fnamplifications.Quiver.Listener.QuiverListener;
import ne.fnfal113.fnamplifications.Staffs.Listener.StaffListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import ne.fnfal113.fnamplifications.Items.FNAmpItemSetup;

public final class FNAmplifications extends JavaPlugin implements SlimefunAddon {

    private static FNAmplifications instance;

    @Override
    public void onEnable() {
        setInstance(this);
        new Metrics(this, 13219);

        getLogger().info("************************************************************");
        getLogger().info("              FN Amplifications - FN 科技                   ");
        getLogger().info("           作者: FN_FAL113  汉化: haiman233, ybw0014         ");
        getLogger().info("    感谢 Jeff(LiteXpansion) 与 Walshy(Slimefun) 提供的工具包   ");
        getLogger().info("               如有任何问题，请前往问题追踪器汇报                 ");
        getLogger().info("                作者 Discord: FN_FAL#7779                    ");
        getLogger().info("************************************************************");

        FNAmpItemSetup.INSTANCE.init();

        getServer().getPluginManager().registerEvents(new MysteryStickListener(), this);
        getServer().getPluginManager().registerEvents(new GearListener(), this);
        getServer().getPluginManager().registerEvents(new StaffListener(), this);
        getServer().getPluginManager().registerEvents(new QuiverListener(), this);
        getServer().getScheduler().runTaskTimerAsynchronously(this, new ArmorEquipRunnable(), 0L, getConfig().getInt("armor-update-period") * 20L);

        getConfig().options().copyDefaults();
        saveDefaultConfig();

        if (getConfig().getBoolean("auto-update", true) && getDescription().getVersion().startsWith("DEV - ")) {
            new GitHubBuildsUpdater(this, getFile(), "FN-FAL113/FN-FAL-s-Amplifications/main").start();
        }
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/ybw0014/FNAmplifications/issues";
    }

    private static void setInstance(FNAmplifications ins) {
        instance = ins;
    }

    public static FNAmplifications getInstance() {
        return instance;
    }
}
