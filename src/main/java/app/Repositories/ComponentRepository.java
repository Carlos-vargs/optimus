/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Repositories;

import config.database;
import app.Models.Component;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

/**
 *
 * @author carlosvargas
 */
public class ComponentRepository extends Repository {

    /**
     * Saves an component to the database.
     *
     * @param component the component to be saved
     */
    @Override
    public void save(Component component) {
        try (Connection connection = database.getConnection(); CallableStatement statement = connection.prepareCall("{CALL save_component(?, ?, ?, ?, ?)}")) {
            statement.setInt(1, component.getComponentTypeId());
            statement.setInt(2, component.getBrandId());
            statement.setString(3, component.getInventoryCode());
            statement.setString(4, formatDate(component.getAcquisitionDate()));
            statement.registerOutParameter(5, Types.INTEGER);

            statement.execute();

            component.setId(statement.getInt(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Attaches the specified component object.
     *
     * @param equipmentId The ID of the equipment to which the component will be
     * attached.
     * @param array The list of IDs of the components to be attached.
     */
    @Override
    public void attach(int equipmentId, ArrayList<Integer> array) {
        try (Connection connection = database.getConnection(); CallableStatement statement = connection.prepareCall("{CALL attach_component(?, ?, ?)}")) {

            for (Integer id : array) {
                statement.setInt(1, equipmentId);
                statement.setInt(2, id);
                statement.setString(3, null);
                statement.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
