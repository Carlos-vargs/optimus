/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Controllers;

import resources.components.reports.Report;
import app.Enums.ExportFiles;
import app.Models.Equipment;
import resources.views.ReportsPanel;
import com.itextpdf.text.BadElementException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import raven.toast.Notifications;

/**
 *
 * @author carlosvargas
 */
public class ReportController {

    public static Object[][] index() {
        return Equipment.report();
    }

    public static void general(DefaultTableModel model, ExportFiles type) {
        try {
            switch (type) {
                case PDF:
                    System.out.println("Exporting... to PDF");
                    Report report = new Report(model);
                    report.exportPDF();
                    break;
                case EXCEL:
                    System.out.println("Exporting... to EXCEL");
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "En estos momentos esta funcion no esta disponible");
                    break;
                case WORD:
                    System.out.println("Exporting... to WORD");
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "En estos momentos esta funcion no esta disponible");
                    break;
                default:
                    Notifications.getInstance().show(Notifications.Type.ERROR, Notifications.Location.TOP_CENTER, "Ha ocurrido un error inesperado. Por favor, inténtalo de nuevo");
                    System.out.println("Tipo de archivo no válido.");
                    break;
            }
        } catch (BadElementException | IOException ex) {
            Logger.getLogger(ReportsPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
