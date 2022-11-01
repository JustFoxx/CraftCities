package io.github.justfoxx.cities.state;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.PersistentState;

public class SpawnState extends PersistentState {
    private int[] spawn = new int[]{0,60,0};

    public SpawnState() {}

    public SpawnState(NbtCompound nbt) {
        spawn = nbt.getIntArray("spawn");
    }
    public static SpawnState get(ServerWorld serverWorld) {
        return serverWorld.getPersistentStateManager().getOrCreate(SpawnState::new, SpawnState::new, "lobby");
    }

    public int[] getSpawn() {
        return spawn;
    }

    public int[] setSpawn(int[] spawnCoords) {
        spawn = spawnCoords;
        markDirty();
        return spawn;
    }
    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        nbt.putIntArray("spawn", spawn);
        return nbt;
    }
}
