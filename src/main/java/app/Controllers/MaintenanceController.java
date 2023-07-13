/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Controllers;

import app.Models.Maintenance;
import app.Requests.Request;
import com.mindfusion.common.DateTime;
import com.mindfusion.scheduling.Calendar;
import com.mindfusion.scheduling.model.Appointment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author carlosvargas
 */
public class MaintenanceController {

    /**
     * Displays a listing of the resource.
     *
     * @param calendar
     * @param list
     */
    public static void index(Calendar calendar, JList<String> list) {
        /* Object[][] activities = Maintenance.activities();
        DefaultListModel<String> model = new DefaultListModel<>();

        for (Object[] activity : activities) {
            System.out.println(Arrays.toString(activity));

            Appointment newAppointment = new Appointment();
            newAppointment.setStartTime(new DateTime((Date) activity[1]));
            newAppointment.setEndTime(new DateTime((Date) activity[1]));
            newAppointment.setHeaderText((String) activity[2]);
            calendar.getSchedule().getItems().add(newAppointment);
            model.addElement((String) activity[2]);
        }

        list.setModel(model);*/
    }

    /**
     * Shows the form to create a new resource.
     *
     */
    public static void create() {
    }

    /**
     *
     * Store a newly created resource in storage.
     *
     * @param request
     */
    public static void store(Request request) {

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
