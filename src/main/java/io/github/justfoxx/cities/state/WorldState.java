package io.github.justfoxx.cities.state;

import io.github.justfoxx.cities.Global;
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
        Global.logger.info("Center: " + center[0] + " " + center[1] + " " + center[2]);
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
