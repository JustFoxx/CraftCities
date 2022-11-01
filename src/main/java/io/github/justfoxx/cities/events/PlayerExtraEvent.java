package io.github.justfoxx.cities.events;

import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerExtraEvent {

    public abstract static class TICK {
        public static List<TickEvent> events = new ArrayList<>();
        public interface TickEvent {
            void tick(ServerPlayerEntity player);
        }
        public static void register(TickEvent event) {
            events.add(event);
        }
    }

}
