package me.xhyrom.hyx.commands;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import me.xhyrom.hyx.utils.Vanish;
import org.bukkit.entity.Player;

@Command("vanish")
@Permission("hyx.command.vanish")
public class VanishCommand {
    @Default
    public static void onDefault(
            Player player
    ) {
        Vanish.INSTANCE.setVanished(player, !Vanish.INSTANCE.isVanished(player), true);
    }
}
