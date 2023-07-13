/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.components;

import javax.swing.JProgressBar;

/**
 *
 * @author carlosvargas
 */
public class Progress extends JProgressBar {

    public Progress() {
        setOpaque(false);
        setStringPainted(true);
        setUI(new ProgressCircleUI());
    }

    @Override
    public String getString() {
        return getValue() + "%";
    }
}
