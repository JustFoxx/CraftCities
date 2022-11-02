package io.github.justfoxx.cities.worlds;

import com.mojang.serialization.Lifecycle;
import io.github.justfoxx.cities.Global;
import net.minecraft.block.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.SimpleRegistry;
import net.minecraft.world.Difficulty;
import net.minecraft.world.gen.chunk.*;
import xyz.nucleoid.fantasy.RuntimeWorldConfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AfkWorld extends BaseWorld{
    private static final Registry<StructureSet> EMPTY_STRUCTURE_REGISTRY = new SimpleRegistry<>(Registry.STRUCTURE_SET_KEY, Lifecycle.stable(), (x) -> null).freeze();
    private static FlatChunkGeneratorConfig generator;
    private static FlatChunkGeneratorConfig getOrCreateGenerator(MinecraftServer server) {
        if (generator == null) {
            generator = new FlatChunkGeneratorConfig(Optional.empty(), server.getRegistryManager().get(Registry.BIOME_KEY))
                    .withLayers(new ArrayList<>(List.of(
                            new FlatChunkGeneratorLayer(1, Blocks.BEDROCK),
                            new FlatChunkGeneratorLayer(1, Blocks.GRASS_BLOCK)
                    )), Optional.empty());
        }
        return generator;
    }
    @Override
    protected Identifier createIdentifier() {
        return Global.id("afk");
    }

    @Override
    public void tick(ServerPlayerEntity player) {
        if(!isPlayerInWorld(player)) return;
    }

    @Override
    protected RuntimeWorldConfig createWorldConfig(MinecraftServer server) {
        return  new RuntimeWorldConfig()
                .setShouldTickTime(true)
                .setDifficulty(Difficulty.PEACEFUL)
                .setSeed(0)
                .setGenerator(new FlatChunkGenerator(EMPTY_STRUCTURE_REGISTRY, getOrCreateGenerator(server)));
    }
}
