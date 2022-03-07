package com.github.maiky1304.auxilium.events;

import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Predicate;

public abstract class FilteredEventListener<E extends Event> extends EventListener<E> {

    public <P extends JavaPlugin> FilteredEventListener(P instance) {
        super(instance);
    }

    @Override
    public void onEvent(E event) {
        if (filter().test(event)) {
            super.onEvent(event);
        }
    }

    public abstract Predicate<E> filter();

}
