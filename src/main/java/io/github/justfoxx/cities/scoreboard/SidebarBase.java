package io.github.justfoxx.cities.scoreboard;

import eu.pb4.sidebars.api.Sidebar;
import net.minecraft.server.MinecraftServer;

public abstract class SidebarBase {
    private Sidebar sidebar;

    protected abstract Sidebar createSidebar(MinecraftServer server);

    public Sidebar getOrCreateSidebar(MinecraftServer server) {
        if (sidebar == null) {
            sidebar = createSidebar(server);
        }
        return sidebar;
    }

    public void showSidebar(MinecraftServer server) {
        getOrCreateSidebar(server).show();
    }
}
