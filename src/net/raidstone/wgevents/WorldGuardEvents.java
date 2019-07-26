package net.raidstone.wgevents;

import com.sk89q.worldguard.WorldGuard;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author weby@we-bb.com [Nicolas Glassey]
 * @version 1.14.4-R0
 * @since 2/24/19
 */
public class WorldGuardEvents extends JavaPlugin implements Listener {
    Listeners listeners = null;
    public void onEnable() {
        String version = Bukkit.getPluginManager().getPlugin("WorldGuard").getDescription().getVersion();
        
        if(version == null || version.isEmpty()) {
            Bukkit.getLogger().severe("[WorldGuardEvents] WorldGuard's version not detected. Are you sure it's installed properly ?");
            Bukkit.getLogger().severe("[WorldGuardEvents] Disabling WorldGuardEvents.");
            return;
        }
        
        if(!version.startsWith("7.0."))
        {
            Bukkit.getLogger().warning("[WorldGuardEvents] Detected WorldGuard version \"" + version + "\".");
            Bukkit.getLogger().warning("[WorldGuardEvents] This plugin is meant to work with WorldGuard version \"7.0.0-beta-03;e51a220\" or higher,");
            Bukkit.getLogger().warning("[WorldGuardEvents] and may not work properly with a lower version.");
            Bukkit.getLogger().warning("[WorldGuardEvents] Please update WorldGuard if your version is below \"7.0.0-beta-03;e51a220\".");
        }
        listeners = new Listeners();
        Entry.setListeners(listeners);
        Bukkit.getPluginManager().registerEvents(listeners, this);
        if(!WorldGuard.getInstance().getPlatform().getSessionManager().registerHandler(Entry.factory, null)) {
            Bukkit.getLogger().severe("[WorldGuardEvents] Could not register the entry handler !");
            Bukkit.getLogger().severe("[WorldGuardEvents] Please report this error. The plugin will now be disabled.");
        }
        
    }
}
