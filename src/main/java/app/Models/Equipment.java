/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Models;

import app.Repositories.EquipmentRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author carlosvargas
 */
public class Equipment extends Model {

    private int id;
    private int typeId;
    private int brandId;
    private int stateId;
    private String acquisitionDate;
    private String lastMaintenance;
    private String nextMaintenance;
    private String inventoryCode;

    public Equipment() {
        Model.setRepository(new EquipmentRepository());
        Model.setTable("Equipos");
    }

    public int getTypeId() {
        return typeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
    }

    public String getLastMaintenance() {
        return lastMaintenance;
    }

    public void setLastMaintenance(String lastMaintenance) {
        this.lastMaintenance = lastMaintenance;
    }

    public void setNextMaintenance(String nextMaintenance) {
        this.nextMaintenance = nextMaintenance;
    }

    public String getInventoryCode() {
        return inventoryCode;
    }

    public void setInventoryCode(String inventoryCode) {
        this.inventoryCode = inventoryCode;
    }

    public String getNextMaintenance() {
        calculateNextMaintenance();
        return nextMaintenance;
    }

    private void calculateNextMaintenance() {
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        Calendar calendar = Calendar.getInstance();
        try {
            Date lastMaintenanceDate = df.parse(lastMaintenance);
            calendar.setTime(lastMaintenanceDate);
            calendar.add(Calendar.MONTH, 3);
            Date nextMaintenanceDate = calendar.getTime();
            nextMaintenance = df.format(nextMaintenanceDate);
        } catch (ParseException e) {
            nextMaintenance = null;
        }
    }

    /**
     *
     * Saves the specified equipment object.
     *
     */
    public void save() {
        repository.save(this);
    }

    /**
     *
     * Counts the number of records in the specified Model.
     *
     * @return the count of records in the Model
     *
     */
    public static int count() {
        Model.setRepository(new EquipmentRepository());
        Model.setTable("VistaEquipos");
        return Model.count();
    }

    public static Object[][] all() {
        Model.setRepository(new EquipmentRepository());
        Model.setTable("VistaEquipos");
        return Model.all();
    }

    public static Object[][] report() {
        Model.setRepository(new EquipmentRepository());
        Model.setTable("VistaSimpleReportes");
        return Model.report();
    }

    public static Object[][] paginate(String columns, int page) {
        Model.setRepository(new EquipmentRepository());
        Model.setTable("VistaEquipos");
        return Model.paginate(columns, page);
    }

    public static Object[][] paginate(int page) {
        Model.setRepository(new EquipmentRepository());
        Model.setTable("VistaEquipos");
        return Model.paginate("*", page);
    }

    public static Object[][] search(String columns, String column, String key) {
        Model.setRepository(new EquipmentRepository());
        Model.setTable("VistaEquipos");
        return Model.search(columns, column, key);
    }

    public static Object[][] search(String column, String key) {
        Model.setRepository(new EquipmentRepository());
        Model.setTable("VistaEquipos");
        return Model.search("*", column, key);
    }

    /**
     * The components that belong to the Equipment
     *
     * @return The relationship to the components
     */
    public Component components() {
        return new Component("Equipo_Componente", this.id);
    }

}
