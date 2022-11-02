package io.github.justfoxx.cities.events;

import io.github.justfoxx.cities.scoreboard.Scoreboards;
import io.github.justfoxx.cities.state.WorldState;
import io.github.justfoxx.cities.worlds.BaseWorld;
import io.github.justfoxx.cities.worlds.Worlds;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
import net.minecraft.world.TeleportTarget;
import org.jetbrains.annotations.NotNull;

public abstract class Player {
    public static void playerDisconnect(ServerPlayNetworkHandler serverPlayNetworkHandler, MinecraftServer server) {
        Scoreboards.GLOBAL_SIDEBAR.setPlayers(server.getCurrentPlayerCount(), server);
    }

    public static void playerJoin(ServerPlayNetworkHandler serverPlayNetworkHandler, PacketSender packetSender, MinecraftServer server) {
        ServerPlayerEntity player = serverPlayNetworkHandler.player;
        Scoreboards.GLOBAL_SIDEBAR.setPlayers(server.getCurrentPlayerCount(), server);
        Scoreboards.GLOBAL_SIDEBAR.getOrCreateSidebar(server).addPlayer(player);
        Worlds.LOBBY.teleportPlayer(player);
        player.changeGameMode(GameMode.ADVENTURE);
    }

    public static void playerTick(@NotNull ServerPlayerEntity player) {
        if(!player.isInvulnerable()) player.setInvulnerable(true);
    }
}
