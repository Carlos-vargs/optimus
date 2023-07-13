/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Repositories;

import config.database;
import app.Models.Equipment;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

/**
 *
 * @author carlosvargas
 */
public class EquipmentRepository extends Repository {

    /**
     * Saves an equipment to the database.
     *
     * @param equipment the equipment to be saved
     */
    @Override
    public void save(Equipment equipment) {
        try (Connection connection = database.getConnection(); CallableStatement statement = connection.prepareCall("{CALL save_equipment(?, ?, ?, ?, ?, ?, ?, ?)}")) {

            statement.setInt(1, equipment.getTypeId());
            statement.setInt(2, equipment.getBrandId());
            statement.setInt(3, equipment.getStateId());
            statement.setString(4, formatDate(equipment.getAcquisitionDate()));
            statement.setString(5, formatDate(equipment.getLastMaintenance()));
            statement.setString(6, formatDate(equipment.getNextMaintenance()));
            statement.setString(7, equipment.getInventoryCode());
            statement.registerOutParameter(8, Types.INTEGER);

            statement.execute();

            equipment.setId(statement.getInt(8));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
