package it.frafol.instantkick;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class InstantKick extends JavaPlugin implements Listener {

    private final List<UUID> players = new ArrayList<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        getServer().getScheduler().runTaskLater(this, () -> {
            players.add(event.getPlayer().getUniqueId());
        }, 20L);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();

        if (!players.contains(player.getUniqueId())) {
            return;
        }

        event.getPlayer().kickPlayer("");
        players.remove(player.getUniqueId());
    }
}
