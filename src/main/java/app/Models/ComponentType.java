/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Models;

import app.Repositories.ComponentTypeRepository;
import app.Repositories.Repository;

/**
 *
 * @author carlosvargas
 */
public class ComponentType extends Model {

    private int id;
    private String nombre;

    public ComponentType() {
        Model.setRepository(new ComponentTypeRepository());
        Model.setTable("TipoComponentes");
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public static void all(String columns, java.awt.Component component) {
        Model.setRepository(new ComponentTypeRepository());
        Model.setTable("TipoComponentes");
        Model.all(columns, component);
    }

    public static int find(String name) {
        Model.setRepository(new ComponentTypeRepository());
        Model.setTable("TipoComponentes");
        return Model.find(name);
    }

}
