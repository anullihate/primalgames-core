package dev.anullihate.primalgamescore.tasks;

import dev.anullihate.primalgamescore.PrimalGamesCore;
import cn.nukkit.Player;
import cn.nukkit.utils.TextFormat;
import de.theamychan.scoreboard.network.Scoreboard;

import java.util.List;

public class ScoreboardUpdater extends Thread {

    private PrimalGamesCore core;
    private int seq = 0;

    public ScoreboardUpdater(PrimalGamesCore core) {
        this.core = core;
        setName("ScoreboardUpdater");
    }

    @Override
    public void run() {
        List<String> titles = core.getConfig().getStringList("scoreboard.titles");

        while (true) {
            if (this.seq < titles.size()) {
                for (Player player : core.getServer().getOnlinePlayers().values()) {
                    String nameTagFormat = core.getConfig().getString("player-tag.name-tag");
                    String scoreTagFormat = (core.getConfig().getString("player-tag.score-tag")
                            .replace("%device_os%", PrimalGamesCore.coreAPI.getOS(player))
                    );

                    String nameTag = PrimalGamesCore.placeholderAPI.translateString(nameTagFormat, player);
                    String scoreTag = PrimalGamesCore.placeholderAPI.translateString(scoreTagFormat, player);

                    player.setNameTag(TextFormat.colorize(nameTag));
                    if (!player.getScoreTag().equals(scoreTag)) {
                        player.setScoreTag(TextFormat.colorize(scoreTag));
                    }

                    try {
                        PrimalGamesCore.coreAPI.scoreboardMap.get(player).hideFor(player);
                    } catch (Exception e) {}
                    Scoreboard scoreboard = PrimalGamesCore.coreAPI.processPlayerScoreboard(player, titles.get(this.seq));

                    scoreboard.showFor(player);
                    PrimalGamesCore.coreAPI.scoreboardMap.put(player, scoreboard);
                }
                this.seq++;
                break;
            } else {
                this.seq = 0;
            }
        }
    }
}
