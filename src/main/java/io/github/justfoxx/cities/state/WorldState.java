package io.github.justfoxx.cities.state;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

public class WorldState extends PersistentState {
    private int[] center = new int[]{0,60,0};

    public WorldState() {}

    public WorldState(NbtCompound nbt) {
        center = nbt.getIntArray("center");
    }
    public static WorldState get(ServerWorld serverWorld) {
        return serverWorld.getPersistentStateManager().getOrCreate(WorldState::new, WorldState::new, "world");
    }

    public int[] getCenter() {
        return center;
    }

    public int[] setCenter(int[] coords) {
        center = coords;
        markDirty();
        return center;
    }
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putIntArray("center", center);
        return nbt;
    }
}
