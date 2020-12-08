package pl.pas.controllers;

import lombok.Data;

import javax.enterprise.inject.Model;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Data
@Named
@SessionScoped
public class UniversalController implements Serializable {
    private String currentForm;

    public UniversalController() {
        currentForm = "";
    }

    public void updateCurrentForm(String string) {
        setCurrentForm(string);
    }

}
