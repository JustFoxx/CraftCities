package io.github.justfoxx.cities.scoreboard;

import eu.pb4.sidebars.api.Sidebar;
import eu.pb4.sidebars.api.lines.SidebarLine;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class GlobalSidebar extends SidebarBase {

    @Override
    protected Sidebar createSidebar(MinecraftServer server) {
        Sidebar sidebar = new Sidebar(Text.literal("Craft Cities").formatted(Formatting.AQUA), Sidebar.Priority.LOW);
        sidebar.setUpdateRate(20);
        sidebar.addLines(SidebarLine.create(1,  Text.of("Players: " + server.getPlayerManager().getCurrentPlayerCount())));
        return sidebar;
    }

    public void setPlayers(int players, MinecraftServer server) {
        getOrCreateSidebar(server).setLine(1, Text.of("Players: " + players));
    }
}
