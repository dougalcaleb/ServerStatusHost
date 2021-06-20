package io.github.dougalcaleb.serverstatushost.loader;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    private final FileConfiguration config;

    public Config(FileConfiguration config) {
        this.config = config;
        config.addDefault("webhookURL", "");
        config.options().copyDefaults(true);
    }

    public String getURL() {
        return config.getString("webhookURL");
    }
}
