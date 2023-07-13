/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Requests;

/**
 *
 * @author carlosvargas
 */
public class StoreEquipmentRequest extends Request {

    public StoreEquipmentRequest() {
        super();
    }

    @Override
    public boolean validate() {
        boolean isValid = true;

        String type = (String) getParameter("type");
        if (type == null || type.isEmpty() || type.equals("Seleccionar")) {
            super.setError("type", "El campo 'Tipo de Equipo' es requerido.");
            isValid = false;
        }

        String brand = (String) getParameter("brand");
        if (brand == null || brand.isEmpty() || brand.equals("Seleccionar")) {
            super.setError("brand", "El campo 'Marca' es requerido.");
            isValid = false;
        }

        String state = (String) getParameter("state");
        if (state == null || state.isEmpty() || state.equals("Seleccionar")) {
            super.setError("state", "El campo 'Estado' es requerido.");
            isValid = false;
        }

        String acquisitionDate = (String) getParameter("acquisitionDate");
        if (acquisitionDate == null || acquisitionDate.isEmpty()) {
            super.setError("acquisitionDate", "El campo 'Fecha de Aquisicion' es requerido.");
            isValid = false;
        }

        String lastMaintenance = (String) getParameter("lastMaintenance");
        if (lastMaintenance == null || lastMaintenance.isEmpty()) {
            super.setError("lastMaintenance", "El campo 'Ultimo Mantenimiento' es requerido.");
            isValid = false;
        }

        String inventoryCode = (String) getParameter("inventoryCode");
        if (inventoryCode == null || inventoryCode.isEmpty()) {
            super.setError("inventoryCode", "El campo 'Codigo de Inventario' es requerido.");
            isValid = false;
        }

        return isValid;
    }

}
