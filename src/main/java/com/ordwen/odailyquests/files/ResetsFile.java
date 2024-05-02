package com.ordwen.odailyquests.files;

import com.ordwen.odailyquests.ODailyQuests;
import com.ordwen.odailyquests.tools.PluginLogger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ResetsFile {

    /**
     * Getting instance of main class.
     */
    private final ODailyQuests oDailyQuests;

    /**
     * Main class instance constructor.
     * @param oDailyQuests main class.
     */
    public ResetsFile(ODailyQuests oDailyQuests) {
        this.oDailyQuests = oDailyQuests;
    }

    private static FileConfiguration resetConfiguration;
    private static File resetConfigurationFile;
    /**
     * Get the player interface file configuration.
     * @return player interface file config
     */
    public static FileConfiguration getResetConfiguration() {
        return resetConfiguration;
    }

    /**
     * Get the player interface file.
     * @return player interface file
     */
    public static File getResetConfigurationFile() {
        return resetConfigurationFile;
    }
    /**
     * Init progression file.
     */
    public void loadResetConfigurationFile() {
        resetConfigurationFile = new File(oDailyQuests.getDataFolder(), "resets.yml");

        if (!resetConfigurationFile.exists()) {
            oDailyQuests.saveResource("resets.yml", false);
            PluginLogger.warn("Player interface file created (YAML).");
        }

        resetConfiguration = new YamlConfiguration();

        try {
            resetConfiguration.load(resetConfigurationFile);
        } catch (InvalidConfigurationException | IOException e) {
            PluginLogger.error("An error occurred on the load of the player interface file.");
            PluginLogger.error("Please inform the developer.");
            PluginLogger.error(e.getMessage());
        }

        PluginLogger.fine("Player interface file successfully loaded (YAML).");
    }

}
