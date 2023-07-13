/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Models;

import app.Repositories.Repository;
import java.awt.Component;

/**
 *
 * @author carlosvargas
 */
public abstract class Model {

    /**
     * The repository associated with the model.
     */
    protected static Repository repository;

    /**
     * The table associated with the model.
     */
    protected static String table;

    /**
     * The number of models to return for pagination.
     *
     */
    protected static int perPage = 15;

    public static Repository getRepository() {
        return repository;
    }

    public static void setRepository(Repository repository) {
        Model.repository = repository;
    }

    public static String getTable() {
        return table;
    }

    public static void setTable(String table) {
        Model.table = table;
    }

    public static int getPerPage() {
        return perPage;
    }

    public static void setPerpage(int limit) {
        Model.perPage = limit;
    }

    public static int find(String name) {
        return Repository.find(name);
    }

    public static int count() {
        return Repository.count();
    }

    public static Object[][] all() {
        return Repository.all("*", true);
    }

    public static Object[][] all(boolean formatForTable) {
        return Repository.all("*", formatForTable);
    }

    public static Object[][] report() {
        return Repository.report("*");
    }

    public static Object[][] all(String columns) {
        return Repository.all(columns, true);
    }

    public static void all(String columns, Component component) {
        Repository.all(columns, component);
    }

    public static Object[][] paginate(String columns, int page) {
        return Repository.paginate(columns, (page - 1) * perPage);
    }

    public static Object[][] search(String columns, String column, String key) {
        return Repository.search(columns, column, key);
    }

    public static Object[][] activities() {
        return Repository.activities();
    }

}
