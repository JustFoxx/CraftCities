package io.github.justfoxx.cities.events;

import io.github.justfoxx.cities.state.SpawnState;
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
    public static TeleportTarget spawnTarget;
    public static TeleportTarget getOrCreateSpawnTarget(MinecraftServer server, BaseWorld world) {
        if(spawnTarget == null) {
            SpawnState lobbyState = SpawnState.get(world.getOrCreateWorld(server).asWorld());
            spawnTarget = new TeleportTarget(
                    new Vec3d(lobbyState.getSpawn()[0], lobbyState.getSpawn()[1], lobbyState.getSpawn()[2]),
                    Vec3d.ZERO,
                    0,
                    0
            );
        }
        return spawnTarget;
    }
    public static void playerDisconnect(ServerPlayNetworkHandler serverPlayNetworkHandler, MinecraftServer server) {
    }

    public static void playerJoin(ServerPlayNetworkHandler serverPlayNetworkHandler, PacketSender packetSender, MinecraftServer server) {
        ServerPlayerEntity player = serverPlayNetworkHandler.player;
        FabricDimensions.teleport(player, Worlds.LOBBY.getOrCreateWorld(server).asWorld(), getOrCreateSpawnTarget(server,Worlds.LOBBY));
        player.changeGameMode(GameMode.ADVENTURE);
    }

    public static void playerTick(@NotNull ServerPlayerEntity player) {
//        if(player.getLastActionTime() > 1000 && player.getWorld() != Worlds.AFK.getOrCreateWorld(player.getServer()).asWorld()) {
//            Worlds.AFK.teleportPlayer(player);
//            return;
//        } else if (player.getWorld() == Worlds.AFK.getOrCreateWorld(player.getServer()).asWorld()) {
//            Worlds.LOBBY.teleportPlayer(player);
//        }
        if(!player.isInvulnerable()) player.setInvulnerable(true);
        if(player.getWorld() != Worlds.LOBBY.getOrCreateWorld(player.getServer()).asWorld()) {
            if(player.isInvisible()) player.setInvisible(false);
            return;
        }
        player.sendMessageToClient(Text.of("Welcome to server!"), true);
        if(getOrCreateSpawnTarget(player.getServer(), Worlds.LOBBY).position == player.getPos()) return;
        if(!player.isInvisible()) player.setInvisible(true);
        Worlds.LOBBY.teleportPlayer(player);
    }
}
