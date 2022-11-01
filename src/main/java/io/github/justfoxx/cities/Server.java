package io.github.justfoxx.cities;

import io.github.justfoxx.cities.events.Events;
import net.fabricmc.api.DedicatedServerModInitializer;

public class Server implements DedicatedServerModInitializer {

    @Override
    public void onInitializeServer() {
        Events.init();
        Global.logger.info("Loaded mod on server!");
    }
}