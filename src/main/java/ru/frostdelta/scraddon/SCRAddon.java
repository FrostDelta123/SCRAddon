package ru.frostdelta.scraddon;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

public final class SCRAddon extends JavaPlugin {

    public static FileConfiguration SCRConfig;

    @Override
    public void onEnable() {
        getLogger().info("Developed by FrostDelta123");
        getCommand("dcart").setExecutor(new DputCommand());
        SCRConfig = Bukkit.getPluginManager().getPlugin("ShoppingCartReloaded").getConfig();
        loadSCRConfigNetwork(SCRConfig);
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            try {
                Network.openConnection();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private static void loadSCRConfigNetwork(FileConfiguration configuration){
        Network.url = configuration.getString("db.url");
        Network.username = configuration.getString("db.username");
        Network.password = configuration.getString("db.password");
    }

    @Override
    public void onDisable() {

    }
}
