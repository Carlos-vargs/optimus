/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Models;

import app.Repositories.EquipmentTypeRepository;
import java.awt.Component;

/**
 *
 * @author carlosvargas
 */
public class EquipmentType extends Model {

    private int id;
    private String name;

    public EquipmentType() {
        Model.setRepository(new EquipmentTypeRepository());
        Model.setTable("TipoEquipos");
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void all(String columns, Component component) {
        Model.setRepository(new EquipmentTypeRepository());
        Model.setTable("TipoEquipos");
        Model.all(columns, component);
    }

    public static int find(String name) {
        Model.setRepository(new EquipmentTypeRepository());
        Model.setTable("TipoEquipos");
        return Model.find(name);
    }

}
