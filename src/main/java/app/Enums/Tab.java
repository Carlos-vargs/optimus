/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package app.Enums;

import resources.views.EquipmentPanel;
import resources.views.HomePanel;
import resources.views.ReportsPanel;
import resources.views.SchedulePanel;

import javax.swing.JPanel;

/**
 *
 * @author carlosvargas
 */
public enum Tab {
    HOME("Home", "HomeIcon.png", "SelectedHomeIcon.png", new HomePanel()),
    SCHEDULE("Schedule", "ScheduleIcon.png", "SelectedScheduleIcon.png", new SchedulePanel()),
    SETTINGS("Setting", "SettingIcon.png", "SelectedSettingIcon.png", new HomePanel()),
    REPORTS("Reports", "ReportsIcon.png", "SelectedReportsIcon.png", new ReportsPanel()),
    EQUIPMENT("Equipment", "EquipmentIcon.png", "SelectedEquipmentIcon.png", new EquipmentPanel());

    public final String tabName;
    public final String iconPath;
    public final String selectedIconPath;
    public JPanel panel;

    Tab(String tabName, String iconPath, String selectedIconPath, JPanel panel) {
        this.tabName = tabName;
        this.iconPath = iconPath;
        this.selectedIconPath = selectedIconPath;
        this.panel = panel;
    }
}
