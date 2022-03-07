package com.github.maiky1304.auxilium.plugin;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public abstract class ExtendedPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        this.enable();
    }

    @Override
    public void onDisable() {
        this.disable();
    }

    public abstract void enable();
    public abstract void disable();

    /**
     * Registers a Bukkit listener without having to provide the
     * plugin variable as well.
     *
     * Also @SafeVarargs to prevent heap pollution
     * @param listeners
     * @param <L>
     */
    @SafeVarargs
    public final <L extends Listener> void registerListeners(L... listeners) {
        for (L listener : listeners) {
            getServer().getPluginManager().registerEvents(listener, this);
        }
    }

    /**
     * Registers a command easily without having to provide everything
     * yourself.
     * @param command
     * @param <P>
     */
    public <P extends CommandExecutor> void registerCommands(String name, P command) {
        Objects.requireNonNull(getCommand(name), String.format("It looks like you haven't registered the command /%s in your plugin.yml yet!",
                name));
        getCommand(name).setExecutor(command);
    }

}
