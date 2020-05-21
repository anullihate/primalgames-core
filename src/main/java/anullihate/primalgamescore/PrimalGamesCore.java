package anullihate.primalgamescore;

import anullihate.primalgamescore.generator.Empty;
import cn.nukkit.level.generator.Generator;
import cn.nukkit.plugin.PluginBase;

public class PrimalGamesCore extends PluginBase {

    @Override
    public void onLoad() {
        Generator.addGenerator(Empty.class, "empty", Generator.TYPE_INFINITE);
    }

    @Override
    public void onEnable() {
        //
    }

    @Override
    public void onDisable() {

    }
}
