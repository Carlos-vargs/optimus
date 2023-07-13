/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Controllers;

import app.Models.Brand;
import app.Models.Component;
import app.Models.ComponentType;
import app.Requests.StoreComponentRequest;
import resources.views.CreateComponentPanel;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author carlosvargas
 */
public class ComponentController {

    /**
     * Displays a listing of the resource.
     */
    public static void index() {

    }

    /**
     * Shows the form to create a new resource.
     *
     * @return JPanel representing the component registration form.
     */
    public static JPanel create() {
        return new CreateComponentPanel();
    }

    /**
     *
     * Store a newly created resource in storage.
     *
     * @param request
     */
    public static void store(StoreComponentRequest request) {

        JPanel panel = (JPanel) request.getParameter("panel");
        ArrayList<Integer> array = (ArrayList) request.getParameter("array");

        for (java.awt.Component componentPanel : panel.getComponents()) {
            if (componentPanel instanceof CreateComponentPanel) {
                CreateComponentPanel componentDataPanel = (CreateComponentPanel) componentPanel;

                int componentTypeId = ComponentType.find(componentDataPanel.jComboBoxComponentType.getSelectedItem().toString());
                int brandId = Brand.find(componentDataPanel.jComboBoxBrands.getSelectedItem().toString());

                Component component = new Component();

                component.setComponentTypeId(componentTypeId);
                component.setBrandId(brandId);
                component.setInventoryCode(componentDataPanel.jTextFieldInvCodeComponent.getText());
                component.setAcquisitionDate(componentDataPanel.jDateAcquisitionComponent.getText());

                component.save();

                array.add(component.getId());
            }
        }

    }

    /**
     * Displays the specified resource.
     *
     * @param id The ID of the resource to display.
     */
    public static void show(int id) {
        // TODO: Implement code to display the specified resource.
    }

    /**
     *
     * Shows the form for editing the specified resource.
     *
     * @param id The ID of the resource to edit.
     */
    public static void edit(int id) {
        // TODO: Implement code to show the form for editing the specified resource.
    }

    /**
     * Updates the specified resource in storage.
     *
     *
     */
    public static void update() {
        // TODO: Implement code to update the specified resource in storage.
    }

    /**
     * Removes the specified resource from storage.
     *
     * @param id The ID of the resource to remove.
     */
    public static void destroy(int id) {
        // TODO: Implement code to remove the specified resource from storage.
    }

}
