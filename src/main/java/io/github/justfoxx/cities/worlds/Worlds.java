package io.github.justfoxx.cities.worlds;

import java.util.ArrayList;
import java.util.List;

public class Worlds {
    public static final List<BaseWorld> worlds = new ArrayList<>();
    public static final LobbyWorld LOBBY = (LobbyWorld) register(new LobbyWorld());
    public static final AfkWorld AFK = (AfkWorld) register(new AfkWorld());
    private static BaseWorld register(BaseWorld world) {
        worlds.add(world);
        return world;
    }
}
