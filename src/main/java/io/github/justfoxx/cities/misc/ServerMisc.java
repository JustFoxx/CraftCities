package io.github.justfoxx.cities.misc;

import net.minecraft.server.MinecraftServer;
import xyz.nucleoid.fantasy.Fantasy;

public class ServerMisc {
    private static Fantasy fantasy;
    public static Fantasy getOrCreateFantasy(MinecraftServer server) {
        if (fantasy == null) {
            fantasy = Fantasy.get(server);
        }
        return fantasy;
    }
}
