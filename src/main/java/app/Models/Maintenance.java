/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Models;

import app.Repositories.MaintenanceRepository;
import app.Repositories.Repository;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author carlosvargas
 */
public class Maintenance extends Model {

    private int id;
    private int equipmentId;
    private int maintenanceTypeId;
    private String description;
    private String solutions;
    private Date createdAt;
    private Date updatedAt;
    private String ticket;
    private String title;

    public Maintenance() {
        Model.setRepository(new MaintenanceRepository());
        Model.setTable("Mantenimientos");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getMaintenanceTypeId() {
        return maintenanceTypeId;
    }

    public void setMaintenanceTypeId(int maintenanceTypeId) {
        this.maintenanceTypeId = maintenanceTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSolutions() {
        return solutions;
    }

    public void setSolutions(String solutions) {
        this.solutions = solutions;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * Counts the number of records in the specified Model.
     *
     * @return the count of records in the Model
     *
     */
    public static int count() {
        Model.setRepository(new MaintenanceRepository());
        Model.setTable("Mantenimientos");
        return Model.count();
    }

    public static Object[][] activities() {
        Model.setRepository(new MaintenanceRepository());
        Model.setTable("VistaActividades");
        return Model.activities();
    }

}
