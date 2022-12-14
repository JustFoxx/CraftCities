package io.github.justfoxx.cities.events;

import io.github.justfoxx.cities.helper.PlayerHelper;
import io.github.justfoxx.cities.misc.ServerMisc;
import io.github.justfoxx.cities.scoreboard.Scoreboards;
import io.github.justfoxx.cities.worlds.Worlds;
import net.minecraft.server.MinecraftServer;

abstract class Server {

    static void serverStarting(MinecraftServer server) {
    }

    static void serverStopped(MinecraftServer server) {
    }

    static void serverStopping(MinecraftServer server) {
    }

    static void serverStarted(MinecraftServer server) {
        ServerMisc.getOrCreateFantasy(server);
        Worlds.LOBBY.getOrCreateWorld(server);
        Worlds.AFK.getOrCreateWorld(server);
        Scoreboards.GLOBAL_SIDEBAR.getOrCreateSidebar(server);
        Scoreboards.GLOBAL_SIDEBAR.showSidebar(server);
        PlayerHelper.register();
    }
}
