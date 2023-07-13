/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app.Requests;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author carlosvargas
 */
public abstract class Request {

    private final Map<String, Object> parameters;
    private final Map<String, String> errors;

    public Request() {
        parameters = new HashMap<>();
        errors = new HashMap<>();
    }

    public void setParameter(String key, Object value) {
        parameters.put(key, value);
    }

    public Object getParameter(String key) {
        return parameters.get(key);
    }

    public void setError(String key, String value) {
        errors.put(key, value);
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public abstract boolean validate();

}
