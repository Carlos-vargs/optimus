/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Controllers;

import resources.components.pagination.Pagination;
import resources.components.pagination.style.PaginationItemRenderStyle1;
import app.Models.Brand;
import app.Models.Equipment;
import app.Models.EquipmentType;
import app.Models.Model;
import app.Models.State;
import app.Requests.StoreEquipmentRequest;
import app.Requests.UpdateEquipmentRequest;
import resources.views.CreateEquipmentPanel;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import raven.toast.Notifications;

/**
 *
 * @author carlosvargas
 */
public class EquipmentController {

    /**
     * Displays a listing of the resource.
     *
     * @return A two-dimensional array of type Object containing the list of
     * elements.
     */
    public static Object[][] index() {
        return Equipment.all();
    }

    /**
     *
     * Displays a list of the resource paginated.
     *
     * @param page The page of items to display.
     *
     * @param paginator The pagination object used to paginate the resource.
     * @param showingResults
     * @param limitPerPage
     *
     * @return A two-dimensional array of type Object containing the list of
     * elements.
     */
    public static Object[][] index(int page, Pagination paginator, JLabel showingResults, JComboBox<String> limitPerPage) {

        Equipment.setPerpage(limitPerPage.getSelectedItem().equals("Todos") ? Equipment.count() : Integer.parseInt(limitPerPage.getSelectedItem().toString()));

        int from = (page - 1) * Equipment.getPerPage();
        from = (from == 0) ? 1 : from;

        int to = (page - 1) * Equipment.getPerPage() + Equipment.getPerPage();

        showingResults.setText("Mostrando de " + from + " a " + to + " de " + Equipment.count() + " resultados");

        paginator.setPagegination(page, (int) Math.ceil(Equipment.count() / Equipment.getPerPage()));

        return Equipment.paginate(page);
    }

    /**
     * Shows the form to create a new resource.
     *
     * @return JPanel representing the equipment registration form.
     */
    public static JPanel create() {
        return new CreateEquipmentPanel();
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param request The StoreEquipmentRequest object containing the
     * information needed to create the equipment.
     */
    public static void store(StoreEquipmentRequest request) {

        String acquisitionDate = (String) request.getParameter("acquisitionDate");
        String lastMaintenance = (String) request.getParameter("lastMaintenance");
        String inventoryCode = (String) request.getParameter("inventoryCode");
        ArrayList<Integer> componentIds = (ArrayList) request.getParameter("componentIds");

        int typeId = EquipmentType.find((String) request.getParameter("type"));
        int brandId = Brand.find((String) request.getParameter("brand"));
        int stateId = State.find((String) request.getParameter("state"));

        Equipment equipment = new Equipment();

        equipment.setTypeId(typeId);
        equipment.setBrandId(brandId);
        equipment.setStateId(stateId);
        equipment.setAcquisitionDate(acquisitionDate);
        equipment.setLastMaintenance(lastMaintenance);
        equipment.setInventoryCode(inventoryCode);

        equipment.save();

        equipment.components().attach(componentIds);

        Notifications.getInstance().show(Notifications.Type.SUCCESS, Notifications.Location.TOP_CENTER, "El equipo se ha registrado exitosamente");
    }

    /**
     * Displays the specified resource.
     *
     * @param inventoryCode The inventory code of the resource to display.
     * @return
     */
    public static Object[][] show(String inventoryCode) {
        return Equipment.search("codigoInventario", inventoryCode);
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
     * @param request The UpdateEquipmentRequest object containing the
     * information needed to update the equipment.
     */
    public static void update(UpdateEquipmentRequest request) {
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
