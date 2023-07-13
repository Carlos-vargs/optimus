/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Models;

import app.Repositories.ComponentRepository;
import java.util.ArrayList;

/**
 *
 * @author carlosvargas
 */
public class Component extends Model {

    private int equipmentId;
    private int id;
    private int componentTypeId;
    private int brandId;
    private String inventoryCode;
    private String acquisitionDate;

    public Component() {
        Model.setRepository(new ComponentRepository());
        Model.setTable("Componentes");
    }

    public Component(String table, int equipmentId) {
        Model.setRepository(new ComponentRepository());
        Model.setTable(table);
        this.equipmentId = equipmentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getComponentTypeId() {
        return componentTypeId;
    }

    public void setComponentTypeId(int componentTypeId) {
        this.componentTypeId = componentTypeId;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getInventoryCode() {
        return inventoryCode;
    }

    public void setInventoryCode(String inventoryCode) {
        this.inventoryCode = inventoryCode;
    }

    public String getAcquisitionDate() {
        return acquisitionDate;
    }

    public void setAcquisitionDate(String acquisitionDate) {
        this.acquisitionDate = acquisitionDate;
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
     * Attaches the specified component object.
     *
     * @param array The list of IDs of the components to be attached.
     */
    public void attach(ArrayList<Integer> array) {
        repository.attach(this.equipmentId, array);
    }

    /**
     *
     * Counts the number of records in the specified Model.
     *
     * @return the count of records in the Model
     *
     */
    public static int count() {
        Model.setRepository(new ComponentRepository());
        Model.setTable("Componentes");
        return Model.count();
    }
}
