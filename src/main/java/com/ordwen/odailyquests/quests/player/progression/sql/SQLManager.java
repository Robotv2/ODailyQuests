package com.ordwen.odailyquests.quests.player.progression.sql;

import com.ordwen.odailyquests.files.ConfigurationFiles;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.PluginLogger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

public class SQLManager {

    /* init variables */
    private String host;
    private String password;
    private String user;
    private String port;

    private final String dbName;
    private final int poolSize;

    private final ConfigurationFiles configurationFiles;
    private HikariDataSource hikariDataSource;

    /* init variables */
    private static final Logger logger = PluginLogger.getLogger("O'DailyQuests");

    /**
     * Constructor.
     * @param configurationFiles class.
     */
    public SQLManager(ConfigurationFiles configurationFiles, String database, int poolSize) {
        this.configurationFiles = configurationFiles;
        this.dbName = database;
        this.poolSize = poolSize;
    }

    /**
     * Load identifiers for database connection.
     */
    public void initCredentials() {

        ConfigurationSection sqlSection= configurationFiles.getConfigFile().getConfigurationSection("database");

        host = sqlSection.getString("host");
        password = sqlSection.getString("password");
        user = sqlSection.getString("user");
        port = sqlSection.getString("port");
    }

    public void testConnection() throws SQLException {
        Connection con = getConnection();
        if (con.isValid(1)) {
            logger.info(ChatColor.BLUE + "CONNECTION TO DATABASE ESTABLISHED");
        } else logger.info(ChatColor.DARK_RED + "IMPOSSIBLE TO CONNECT TO DATABASE");
    }

    /**
     * Connect to database.
     */
    public void initHikariCP(){

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setMaximumPoolSize(this.poolSize);
        hikariConfig.setJdbcUrl(this.toUri());
        hikariConfig.setUsername(user);
        hikariConfig.setPassword(password);
        hikariConfig.setIdleTimeout(300000L);
        hikariConfig.setMaxLifetime(300000L);
        hikariConfig.setLeakDetectionThreshold(3000L);
        hikariConfig.setConnectionTimeout(10000L);

        this.hikariDataSource = new HikariDataSource(hikariConfig);
    }

    /**
     * Close database connection.
     */
    public void close(){
        this.hikariDataSource.close();
    }

    /**
     * Get database connection.
     * @return database Connection.
     */
    public Connection getConnection() {
        if(this.hikariDataSource != null && !this.hikariDataSource.isClosed()){
            try {
                return this.hikariDataSource.getConnection();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Setup JdbcUrl.
     * @return JdcbUrl.
     */
    private String toUri(){
        return "jdbc:mysql://" + host + ":" + port + "/" + this.dbName;
    }
}