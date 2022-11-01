package io.github.justfoxx.cities.mixininter;

import net.minecraft.nbt.NbtCompound;

public interface IDataSaver {
    NbtCompound getPersistentData();
}
