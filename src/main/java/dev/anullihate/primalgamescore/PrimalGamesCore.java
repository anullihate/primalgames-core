package dev.anullihate.primalgamescore;

import dev.anullihate.primalgamescore.apis.CoreAPI;
import dev.anullihate.primalgamescore.generator.Empty;
import dev.anullihate.primalgamescore.tasks.ScoreboardUpdater;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;
import com.creeperface.nukkit.placeholderapi.api.PlaceholderAPI;

public class PrimalGamesCore extends PluginBase {

    public static Config config;

    // API
    public static CoreAPI coreAPI;
    public static PlaceholderAPI placeholderAPI;

    @Override
    public void onLoad() {
        Generator.addGenerator(Empty.class, "empty", Generator.TYPE_INFINITE);
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        config = getConfig();

        loadAPI();

        getServer().getPluginManager().registerEvents(new EventListener(this), this);
        getServer().getScheduler().scheduleDelayedRepeatingTask(this, new ScoreboardUpdater(this), 20, 20, true);
    }

    @Override
    public void onDisable() {

    }

    private void loadAPI() {
        coreAPI = new CoreAPI(this);
        placeholderAPI = PlaceholderAPI.getInstance();
    }
}
