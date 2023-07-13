/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Models;

import app.Enums.StateEnum;
import app.Repositories.Repository;
import app.Repositories.StateRepository;
import java.awt.Component;

/**
 *
 * @author carlosvargas
 */
public class State extends Model {

    private int id;
    private String name;

    public State() {
        Model.setRepository(new StateRepository());
        Model.setTable("Estados");

    }

    public void setId(int id) {
        this.id = id;
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
        return StateRepository.getPercentageByState(state);
    }

    public static void all(String columns, Component component) {
        Model.setRepository(new StateRepository());
        Model.setTable("Estados");
        Model.all(columns, component);
    }

    public static int find(String name) {
        Model.setRepository(new StateRepository());
        Model.setTable("Estados");
        return Model.find(name);
    }

}
