package io.github.justfoxx.cities.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;

public class Events {

    public static void init() {
        ServerLifecycleEvents.SERVER_STARTED.register(Server::serverStarted);
        ServerLifecycleEvents.SERVER_STARTING.register(Server::serverStarting);
        ServerLifecycleEvents.SERVER_STOPPING.register(Server::serverStopping);
        ServerLifecycleEvents.SERVER_STOPPED.register(Server::serverStopped);

        PlayerExtraEvent.DISCONNECTED.register(Player::playerDisconnect);
        PlayerExtraEvent.JOINED.register(Player::playerJoin);
        PlayerExtraEvent.TICK.register(Player::playerTick);
    }
}
