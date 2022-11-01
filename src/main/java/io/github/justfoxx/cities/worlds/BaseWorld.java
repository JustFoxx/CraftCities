package io.github.justfoxx.cities.worlds;

import io.github.justfoxx.cities.events.Player;
import io.github.justfoxx.cities.misc.ServerMisc;
import net.fabricmc.fabric.api.dimension.v1.FabricDimensions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
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

    protected abstract RuntimeWorldConfig createWorldConfig(MinecraftServer server);

    public RuntimeWorldConfig getOrCreateWorldConfig(MinecraftServer server) {
        if(worldConfig == null) {
            worldConfig = createWorldConfig(server);
        }
        return worldConfig;
    }

    public RuntimeWorldHandle getOrCreateWorld(MinecraftServer server) {
        return ServerMisc.getOrCreateFantasy(server).getOrOpenPersistentWorld(getOrCreateIdentifier(),getOrCreateWorldConfig(server));
    }

    public void teleportPlayer(ServerPlayerEntity player) {
        FabricDimensions.teleport(player, Worlds.LOBBY.getOrCreateWorld(player.getServer()).asWorld(), Player.getOrCreateSpawnTarget(player.getServer(),this));
    }
}
