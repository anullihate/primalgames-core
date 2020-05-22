package dev.anullihate.primalgamescore.apis;

import dev.anullihate.primalgamescore.PrimalGamesCore;
import cn.nukkit.Player;
import de.theamychan.scoreboard.api.ScoreboardAPI;
import de.theamychan.scoreboard.network.DisplaySlot;
import de.theamychan.scoreboard.network.Scoreboard;
import de.theamychan.scoreboard.network.ScoreboardDisplay;

import java.util.HashMap;
import java.util.Map;

public class CoreAPI {

    private PrimalGamesCore core;

    public Map<Player, Scoreboard> scoreboardMap = new HashMap<>();
    private int line = 0;

    public CoreAPI(PrimalGamesCore core) {
        this.core = core;
    }

    public Scoreboard processPlayerScoreboard(Player player, String titleSequence) {
        Scoreboard scoreboard = ScoreboardAPI.createScoreboard();


        ScoreboardDisplay scoreboardDisplay = scoreboard.addDisplay(DisplaySlot.SIDEBAR, "dummy", titleSequence);

        for (String text : this.core.getConfig().getStringList("scoreboard.lines")) {
            String txt = text
                    .replace("{ping}", String.valueOf(player.getPing()));
            scoreboardDisplay.addLine(PrimalGamesCore.placeholderAPI.translateString(txt, player), this.line++);
        }

        this.line = 0;
        return scoreboard;
    }

    public String getOS(Player p) {
        switch(p.getLoginChainData().getDeviceOS()) {
            case 1:
                return "Android";
            case 2:
                return "iOS";
            case 3:
                return "Mac";
            case 4:
                return "Fire";
            case 5:
                return "Gear VR";
            case 6:
                return "HoloLens";
            case 7:
                return "Windows 10";
            case 8:
                return "Windows";
            case 9:
                return "Dedicated";
            case 10:
                return "tvOS";
            case 11:
                return "PlayStation";
            case 12:
                return "NX";
            case 13:
                return "Xbox";
            default:
                return "Unknown";
        }
    }
}
