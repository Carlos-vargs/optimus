/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author carlosvargas
 */
public class database {

    private static final String BASE_DIR = System.getProperty("user.dir");
    private static final String CONFIG_PATH = BASE_DIR + "/src/main/java/config/";
    private static final String CONFIG_FILE = CONFIG_PATH + "config.txt";

    public static Connection getConnection() throws SQLException {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String url = properties.getProperty("DB_CONNECTION") + "://"
                + properties.getProperty("DB_HOST") + ":"
                + properties.getProperty("DB_PORT") + "/"
                + properties.getProperty("DB_DATABASE");

        String username = properties.getProperty("DB_USERNAME");
        String password = properties.getProperty("DB_PASSWORD");

        return DriverManager.getConnection(url, username, password);
    }
}
