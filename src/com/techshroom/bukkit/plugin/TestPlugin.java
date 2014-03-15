package com.techshroom.bukkit.plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TestPlugin extends JavaPlugin implements Listener {
    private boolean cancel = false;

    public void onEnable() {
        getLogger().info("Loading test plugin for Bukkit-1559...");
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("Loaded.");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        cancel = !cancel;
        sender.sendMessage("Cancel is now " + cancel);
        return true;
    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntitySpawn(EntitySpawnEvent ese) {
        ese.setCancelled(cancel);
        getLogger().info("Entity of type " + ese.getEntityType() + " being spawned at " + ese.getLocation() + ". It will " + (cancel ? "not " : "") + "be spawned.");
    }

    public void onDisable() {
        getLogger().info("Unloading test plugin for Bukkit-1559...");
        getLogger().info("Unloaded.");
    }
}
