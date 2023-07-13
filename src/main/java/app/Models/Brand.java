/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Models;

import app.Repositories.BrandRepository;
import java.awt.Component;

/**
 *
 * @author carlosvargas
 *
 */
public class Brand extends Model {

    private int id;
    private String name;

    public Brand() {
        Model.setRepository(new BrandRepository());
        Model.setTable("Marcas");
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int find(String name) {
        Model.setRepository(new BrandRepository());
        Model.setTable("Marcas");
        return Model.find(name);
    }

    public static void all(String columns, Component component) {
        Model.setRepository(new BrandRepository());
        Model.setTable("Marcas");
        Model.all(columns, component);
    }
}
