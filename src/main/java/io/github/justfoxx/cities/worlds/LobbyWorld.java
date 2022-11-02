package io.github.justfoxx.cities.worlds;

import io.github.justfoxx.cities.Global;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Difficulty;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.dimension.DimensionTypes;
import xyz.nucleoid.fantasy.RuntimeWorldConfig;
import xyz.nucleoid.fantasy.util.VoidChunkGenerator;

public class LobbyWorld extends BaseWorld {

    @Override
    protected Identifier createIdentifier() {
        return Global.id("lobby");
    }

    @Override
    public void tick(ServerPlayerEntity player) {
        if(!isPlayerInWorld(player))  {
            if(player.isInvisible()) player.setInvisible(false);
            return;
        }
        player.sendMessageToClient(Text.of("Welcome to server!"), true);
        if(isInCenter(player)) return;
        if(!player.isInvisible()) player.setInvisible(true);
        Worlds.LOBBY.teleportPlayer(player);
    }

    @Override
    protected RuntimeWorldConfig createWorldConfig(MinecraftServer server) {
        return new RuntimeWorldConfig()
                .setDifficulty(Difficulty.PEACEFUL)
                .setDimensionType(DimensionTypes.THE_END)
                .setShouldTickTime(true)
                .setGenerator(new VoidChunkGenerator(server.getRegistryManager().get(Registry.BIOME_KEY).entryOf(BiomeKeys.THE_VOID)));
    }
}
