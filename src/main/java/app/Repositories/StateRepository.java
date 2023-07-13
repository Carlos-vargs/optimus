/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Repositories;

import config.database;
import app.Enums.StateEnum;
import app.Models.Model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author carlosvargas
 */
public class StateRepository extends Repository {

    /**
     *
     * Retrieves the percentage value based on the given state.
     *
     * @param state The state of the equipment.
     *
     * @return The percentage value associated with the given status, or 0 if
     * not found.
     */
    public static int getPercentageByState(StateEnum state) {
        String query = "SELECT porcentaje FROM VistaEstadoEquipos WHERE estado = ?";

        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, state.getName());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt("porcentaje");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
