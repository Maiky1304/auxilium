package com.github.maiky1304.auxilium.events;

import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public abstract class BiEventListener<E extends Event, E2 extends Event> implements Listener {

    private final JavaPlugin instance;

    public <P extends JavaPlugin> BiEventListener(P instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onEvent(E event) {
        eventOne().accept(event);
    }

    public void onEventTwo(E2 event) {
        eventTwo().accept(event);
    }

    public abstract Consumer<E> eventOne();
    public abstract Consumer<E2> eventTwo();

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
