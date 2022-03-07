package com.github.maiky1304.auxilium.events;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public abstract class EventListener<E extends Event> implements Listener {

    private final JavaPlugin instance;

    public <P extends JavaPlugin> EventListener(P instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onEvent(E event) {
        handler().accept(event);
    }

    public abstract Consumer<E> handler();

    /**
     * Unregisters the listener
     */
    public void unregister() {
        HandlerList.unregisterAll(this);
    }

    /**
     * Registers the listener
     */
    public void register() {
        instance.getServer().getPluginManager().registerEvents(this, instance);
    }

}
