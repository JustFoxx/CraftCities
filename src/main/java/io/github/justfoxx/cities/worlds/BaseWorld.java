package io.github.justfoxx.cities.worlds;

import io.github.justfoxx.cities.misc.ServerMisc;
import io.github.justfoxx.cities.state.WorldState;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.TeleportTarget;
import xyz.nucleoid.fantasy.RuntimeWorldConfig;
import xyz.nucleoid.fantasy.RuntimeWorldHandle;

public abstract class BaseWorld {
    private RuntimeWorldConfig worldConfig;
    private Identifier identifier;

    public Identifier getOrCreateIdentifier() {
        if (identifier == null) {
            identifier = createIdentifier();
        }
        return identifier;
    }

    protected abstract Identifier createIdentifier();

    public abstract void tick(ServerPlayerEntity player);

    protected abstract RuntimeWorldConfig createWorldConfig(MinecraftServer server);
    public static TeleportTarget centerTarget;
    public TeleportTarget getOrCreateCenterTarget(MinecraftServer server) {
        if(centerTarget == null) {
            centerTarget = getOrCreateCenterTarget(server, Vec3d.ZERO, 0, 0);
        }
        return centerTarget;
    }

    public TeleportTarget getOrCreateCenterTarget(MinecraftServer server, Vec3d velocity, float yaw, float pitch) {
        if(centerTarget == null) {
            WorldState lobbyState = WorldState.get(this.getOrCreateWorld(server).asWorld());
            centerTarget = new TeleportTarget(
                    new Vec3d(lobbyState.getCenter()[0], lobbyState.getCenter()[1], lobbyState.getCenter()[2]),
                    velocity,
                    yaw,
                    pitch
            );
        }
        return centerTarget;
    }

    public RuntimeWorldConfig getOrCreateWorldConfig(MinecraftServer server) {
        if (worldConfig == null) {
            worldConfig = createWorldConfig(server);
        }
        return worldConfig;
    }

    public RuntimeWorldHandle getOrCreateWorld(MinecraftServer server) {
        return ServerMisc.getOrCreateFantasy(server).getOrOpenPersistentWorld(getOrCreateIdentifier(), getOrCreateWorldConfig(server));
    }

    public void teleportPlayer(ServerPlayerEntity player) {
        FabricDimensions.teleport(player, Worlds.LOBBY.getOrCreateWorld(player.getServer()).asWorld(), getOrCreateCenterTarget(player.getServer(), player.getVelocity(), player.getYaw(), player.getPitch()));
    }

    public boolean isPlayerInWorld(ServerPlayerEntity player) {
        return player.getWorld() == getOrCreateWorld(player.getServer()).asWorld();
    }

    public boolean isInCenter(ServerPlayerEntity player) {
        return player.getBlockPos().isWithinDistance(getOrCreateCenterTarget(player.getServer()).position, 0);
    }
}