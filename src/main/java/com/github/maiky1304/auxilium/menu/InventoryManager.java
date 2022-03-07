package com.github.maiky1304.auxilium.menu;

import com.github.maiky1304.auxilium.events.BiEventListener;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class InventoryManager extends BiEventListener<InventoryClickEvent, InventoryCloseEvent> {

    private final List<Menu> activeMenus = new ArrayList<>();

    public <P extends JavaPlugin> InventoryManager(P instance) {
        super(instance);
    }

    /**
     * Registers the menu by adding it to the cache
     * @param menu
     */
    public void registerMenu(Menu menu) {
        this.activeMenus.add(menu);
    }

    /**
     * Destroys the menu by removing it from the cache
     * @param menu
     */
    public void destroyMenu(Menu menu) {
        this.activeMenus.remove(menu);
    }

    @Override
    public Consumer<InventoryClickEvent> eventOne() {
        return event -> {
            Inventory inventory = event.getWhoClicked().getOpenInventory().getTopInventory();
            if (this.activeMenus.stream().noneMatch(menu -> menu.getInventory()
                    .equals(inventory))) return;

            event.setCancelled(true);

            Menu menu = this.activeMenus.stream().filter(m -> m.getInventory()
                    .equals(inventory)).findFirst().orElse(null);
            assert menu != null;
            menu.handleClick(new ClickContext(event));
        };
    }

    @Override
    public Consumer<InventoryCloseEvent> eventTwo() {
        return event -> {
            if (this.activeMenus.stream().noneMatch(menu -> menu.getInventory()
                    .equals(event.getInventory()))) return;

            Menu menu = this.activeMenus.stream().filter(m -> m.getInventory()
                    .equals(event.getInventory())).findFirst().orElse(null);
            assert menu != null;

            if (ArrayUtils.contains(menu.flags, MenuFlag.DESTROY_ON_CLOSE)) {
                destroyMenu(menu);
            }
        };
    }

}
