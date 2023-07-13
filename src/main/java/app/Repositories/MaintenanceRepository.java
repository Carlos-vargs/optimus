/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Repositories;

import app.Models.Model;
import config.database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 *
 * @author carlosvargas
 */
public class MaintenanceRepository extends Repository {

    public static Object[][] activities() {
        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + Model.getTable(),
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            ResultSet resultSet = statement.executeQuery();

            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            Object[][] datos = new Object[rowCount][columnCount];
            int rowIndex = 0;

            while (resultSet.next()) {
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    Object value = resultSet.getObject(columnIndex);
                    datos[rowIndex][columnIndex - 1] = value;
                }
                rowIndex++;
            }

            return datos;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

}
