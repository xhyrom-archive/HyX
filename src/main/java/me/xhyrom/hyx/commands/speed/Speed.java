package me.xhyrom.hyx.commands.speed;

import dev.jorel.commandapi.annotations.Command;
import dev.jorel.commandapi.annotations.Default;
import dev.jorel.commandapi.annotations.Permission;
import dev.jorel.commandapi.annotations.arguments.AIntegerArgument;
import me.xhyrom.hyx.HyX;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import org.bukkit.entity.Player;

@Command("speed")
@Permission("hyx.command.speed")
public class Speed {
    @Default
    public static void speed(
            Player player,
            @AIntegerArgument(min = 0, max = 10) int speed
    ) {
        if (player.isFlying()) {
            if (!player.hasPermission("hyx.command.speed.fly")) {
                player.sendMessage(MiniMessage.miniMessage().deserialize(
                        HyX.Companion.getInstanceUnsafe().lang().getString("commands.speed.fly.fail.message").get(),
                        Placeholder.unparsed("speed", String.valueOf(speed))
                ));
                return;
            }

            player.setFlySpeed(speed / 10f);
            player.sendMessage(MiniMessage.miniMessage().deserialize(
                    HyX.Companion.getInstanceUnsafe().lang().getString("commands.speed.fly.success.message").get(),
                    Placeholder.unparsed("speed", String.valueOf(speed))
            ));
        } else if (player.hasPermission("hyx.command.speed.walk")) {
            player.setWalkSpeed(speed / 10f);
            player.sendMessage(MiniMessage.miniMessage().deserialize(
                    HyX.Companion.getInstanceUnsafe().lang().getString("commands.speed.walk.success.message").get(),
                    Placeholder.unparsed("speed", String.valueOf(speed))
            ));
        } else {
            player.sendMessage(MiniMessage.miniMessage().deserialize(
                    HyX.Companion.getInstanceUnsafe().lang().getString("commands.speed.walk.fail.message").get(),
                    Placeholder.unparsed("speed", String.valueOf(speed))
            ));
        }
    }
}
