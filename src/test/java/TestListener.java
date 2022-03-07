import com.github.maiky1304.auxilium.events.EventListener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.function.Consumer;

public class TestListener extends EventListener<PlayerJoinEvent> {

    public <P extends JavaPlugin> TestListener(P instance) {
        super(instance);
    }

    @Override
    public Consumer<PlayerJoinEvent> handler() {
        return event -> {
            event.getPlayer().sendMessage("Hi Maiky");
        };
    }

}
