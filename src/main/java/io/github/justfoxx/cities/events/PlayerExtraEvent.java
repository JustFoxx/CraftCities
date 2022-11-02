package io.github.justfoxx.cities.events;

import net.minecraft.server.network.ServerPlayerEntity;

import java.util.ArrayList;
import java.util.List;

public abstract class PlayerExtraEvent {

    public abstract static class TICK {
        public static final List<TickEvent> events = new ArrayList<>();
        public interface TickEvent {
            void tick(ServerPlayerEntity player);
        }
        public static void register(TickEvent event) {
            events.add(event);
        }
    }

    public abstract static class JOINED {
        public static final List<JoinedEvent> events = new ArrayList<>();
        public interface JoinedEvent {
            void joined(ServerPlayerEntity player);
        }
        public static void register(JoinedEvent event) {
            events.add(event);
        }
    }

    public abstract static class DISCONNECTED {
        public static final List<DisconnectedEvent> events = new ArrayList<>();
        public interface DisconnectedEvent {
            void disconnected(ServerPlayerEntity player);
        }
        public static void register(DisconnectedEvent event) {
            events.add(event);
        }
    }
}
