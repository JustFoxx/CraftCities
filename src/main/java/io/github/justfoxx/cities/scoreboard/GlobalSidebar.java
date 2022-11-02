package io.github.justfoxx.cities.scoreboard;

import eu.pb4.sidebars.api.Sidebar;
import eu.pb4.sidebars.api.lines.SidebarLine;
import eu.pb4.sidebars.api.lines.SimpleSidebarLine;
import io.github.justfoxx.cities.helper.PlayerHelper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class GlobalSidebar extends SidebarBase {

    @Override
    protected Sidebar createSidebar(MinecraftServer server) {
        Sidebar sidebar = new Sidebar(Text.literal("Craft Cities").formatted(Formatting.AQUA), Sidebar.Priority.LOW);
        sidebar.setUpdateRate(20);
        int players = server.getPlayerManager().getCurrentPlayerCount();
        sidebar.addLines(SidebarLine.create(2, (player) -> Text.of("Time: "+ player.getServer().getTickTime())));
        sidebar.addLines(new SimpleSidebarLine(1, Text.of("Players: " + players)));
        sidebar.addLines(SidebarLine.create(0, (player) -> Text.of("Money: "+ PlayerHelper.of(player).getData().money)));
        return sidebar;
    }

    public void setPlayers(int players, MinecraftServer server) {
        getOrCreateSidebar(server).setLine(new SimpleSidebarLine(1, Text.of("Players: " + players)));
        getOrCreateSidebar(server).markDirty();
    }
}
