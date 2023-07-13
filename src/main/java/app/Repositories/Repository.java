/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Repositories;

import config.database;
import app.Models.Equipment;
import app.Models.Maintenance;
import app.Models.Model;
import java.awt.Component;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;

/**
 *
 * @author carlosvargas
 */
public abstract class Repository {

    protected static Model model;

    public Model getModel() {
        return model;
    }

    /**
     * Sets the model associated with the repository.
     *
     * @param model the model instance
     */
    public void setModel(Model model) {
        Repository.model = model;
    }

    /**
     *
     * Formats the given date string.
     *
     * @param date The date string to be formatted in the format "dd-MM-yyyy".
     *
     * @return The formatted date string in the format "yyyy-MM-dd".
     */
    protected String formatDate(String date) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(date, inputFormatter);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return localDate.format(outputFormatter);
    }

    /**
     * Finds a record in the database by name and returns its identifier.
     *
     * @param name the name to search for
     *
     * @return the identifier of the record if found, 0 if not found or there is
     * an error
     */
    public static int find(String name) {
        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT id FROM " + Model.getTable() + " WHERE nombre = ?")) {
            statement.setString(1, name);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }

    /**
     *
     * Counts the number of records in the specified table.
     *
     * @return the count of records in the table
     *
     */
    public static int count() {
        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT COUNT(*) FROM " + Model.getTable())) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            System.err.println(e);
        }
        return 0;
    }

    public static Object[][] all(String columns, boolean formatForTable) {
        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT " + columns + " FROM " + Model.getTable(),
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            ResultSet resultSet = statement.executeQuery();

            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount() + (formatForTable ? 1 : 0);

            return processResultSet(resultSet, rowCount, columnCount, formatForTable);

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static void all(String columns, Component component) {
        Object[][] datos = all(columns, true);
        if (datos != null) {
            switch (component.getClass().getSimpleName()) {
                case "JComboBox":
                    JComboBox<Object> comboBox = (JComboBox<Object>) component;
                    DefaultComboBoxModel<Object> comboBoxModel = new DefaultComboBoxModel<>();
                    comboBoxModel.addElement("Seleccionar");
                    for (Object[] row : datos) {
                        comboBoxModel.addElement(row[1]);
                    }
                    comboBox.setModel(comboBoxModel);
                    break;
                case "JLabel":
                    JLabel label = (JLabel) component;
                    if (datos.length > 0) {
                        label.setText(datos[0][1].toString());
                    }
                    break;
                default:
                    System.out.println("Unsupported component type: " + component.getClass().getSimpleName());
                    break;
            }
        }
    }

    public static Object[][] paginate(String columns, int page) {
        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT " + columns + " FROM " + Model.getTable() + " LIMIT ?," + Model.getPerPage(),
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            statement.setInt(1, page);
            ResultSet resultSet = statement.executeQuery();

            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount() + 1;

            return processResultSet(resultSet, rowCount, columnCount, true);

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static Object[][] report(String columns) {
        try (Connection connection = database.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT " + columns + " FROM " + Model.getTable(),
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            ResultSet resultSet = statement.executeQuery();

            resultSet.last();
            int rowCount = resultSet.getRow();
            resultSet.beforeFirst();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            Object[][] datos = new Object[rowCount][columnCount];
            int rowIndex = 0;

            while (resultSet.next()) {
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    Object value = resultSet.getObject(columnIndex);

                    if (value instanceof Date) {
                        SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
                        String formattedDate = dateFormat.format((Date) value);
                        datos[rowIndex][columnIndex - 1] = formattedDate;
                    } else if (value == null) {
                        datos[rowIndex][columnIndex - 1] = "Aun no se ha programado";
                    } else {
                        datos[rowIndex][columnIndex - 1] = value;
                    }
                }
                rowIndex++;
            }

            return datos;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    /**
     *
     * Saves the specified equipment object.
     *
     * @param equipment the equipment object to be saved
     */
    public void save(Equipment equipment) {

    }

    /**
     *
     * Saves the specified component object.
     *
     * @param component the component object to be saved
     */
    public void save(app.Models.Component component) {

    }

    /**
     *
     * Attaches the specified component object.
     *
     * @param parentId The ID of the parent to which it will be assigned
     * @param array The list of IDs of the elements to be attached.
     */
    public void attach(int parentId, ArrayList<Integer> array) {

    }

    /**
     * Executes a search operation using the specified parameters and returns a
     * two-dimensional array.
     *
     * @param columns the names of the columns to select in the query
     * @param column the name of the column to search in
     * @param key the value to search for in the specified column
     * @return a two-dimensional array containing the search results
     */
    public static Object[][] search(String columns, String column, String key) {
        try (Connection connection = database.getConnection(); CallableStatement statement = connection.prepareCall("{CALL search(?, ?, ?, ?)}", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {

            statement.setString(1, columns);
            statement.setString(2, Model.getTable());
            statement.setString(3, column);
            statement.setString(4, key);

            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.last();
                int rowCount = resultSet.getRow();
                resultSet.beforeFirst();

                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount() + 1;

                return processResultSet(resultSet, rowCount, columnCount, true);
            }

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        }
    }

    public static ArrayList<Maintenance> list() {
        return null;
    }

    private static Object[][] processResultSet(ResultSet resultSet, int rowCount, int columnCount, boolean formatForTable) throws SQLException {
        Object[][] datos = new Object[rowCount][columnCount];
        int rowIndex = 0;

        while (resultSet.next()) {
            for (int columnIndex = (formatForTable ? 1 : 0); columnIndex < columnCount; columnIndex++) {
                if (formatForTable) {
                    if (columnIndex == 0) {
                        datos[rowIndex][columnIndex] = false;
                    } else {
                        Object value = resultSet.getObject(columnIndex);
                        if (value instanceof Date) {
                            SimpleDateFormat dateFormat = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("es"));
                            String formattedDate = dateFormat.format((Date) value);
                            datos[rowIndex][columnIndex] = formattedDate;
                        } else if (value == null) {
                            datos[rowIndex][columnIndex] = "Aun no se ha programado";
                        } else {
                            datos[rowIndex][columnIndex] = value;
                        }
                    }
                } else {
                    Object value = resultSet.getObject(columnIndex);
                    datos[rowIndex][columnIndex] = value;
                }
            }
            rowIndex++;
        }

        return datos;
    }

    public static Object[][] activities() {
        return null;
    }

}
