package me.xhyrom.hyx.commands.gamemodes;

import dev.jorel.commandapi.annotations.Alias;
import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AMultiLiteralArgument;
import org.bukkit.entity.Player;

@Command("gamemode")
@Alias("gm")
@Permission("hyx.command.gamemode")
public class Gamemode {
    @Default
    public static void gamemode(
            Player player,
            @AMultiLiteralArgument({
                    "survival",
                    "s",
                    "creative",
                    "c",
                    "adventure",
                    "a",
                    "spectator",
                    "sp"
            }) String mode
    ) {
        switch (mode) {
            case "survival", "s" -> player.performCommand("gms");
            case "creative", "c" -> player.performCommand("gmc");
            case "adventure", "a" -> player.performCommand("gma");
            case "spectator", "sp" -> player.performCommand("gmsp");
        }
    }
}
