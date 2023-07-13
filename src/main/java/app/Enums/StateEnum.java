/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package app.Enums;

/**
 *
 * @author carlosvargas
 */
public enum StateEnum {
    GOOD("Bueno"),
    MAINTENANCE("En Mantenimiento"),
    DAMAGED("Dañado");

    private final String name;

    StateEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
