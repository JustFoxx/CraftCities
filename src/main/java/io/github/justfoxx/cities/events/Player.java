package io.github.justfoxx.cities.events;

import io.github.justfoxx.cities.helper.PlayerHelper;
import io.github.justfoxx.cities.scoreboard.Scoreboards;
import io.github.justfoxx.cities.worlds.Worlds;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.GameMode;
import org.jetbrains.annotations.NotNull;

public abstract class Player {
    public static void playerDisconnect(ServerPlayerEntity player) {
        Scoreboards.GLOBAL_SIDEBAR.setPlayers(player.getServer().getPlayerNames().length, player.getServer());
    }

    public static void playerJoin(ServerPlayerEntity player) {
        Scoreboards.GLOBAL_SIDEBAR.getOrCreateSidebar(player.getServer()).addPlayer(player);
        Worlds.LOBBY.teleportPlayer(player);
        player.changeGameMode(GameMode.ADVENTURE);
        Scoreboards.GLOBAL_SIDEBAR.setPlayers(player.getServer().getPlayerNames().length, player.getServer());
        var data = PlayerHelper.of(player).getData();
        data.money+=1000;
        PlayerHelper.of(player).writeData(data);
    }

    public static void playerTick(@NotNull ServerPlayerEntity player) {
        if(!player.isInvulnerable()) player.setInvulnerable(true);
    }
}
