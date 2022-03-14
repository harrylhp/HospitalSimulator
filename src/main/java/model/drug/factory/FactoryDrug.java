package model.drug.factory;

import model.drug.*;

public class FactoryDrug {

    public static Drug getDrugType(String name) {
        if (name.equalsIgnoreCase("As")) {
            return new Aspirin();
        } else if (name.equalsIgnoreCase("An")) {
            return new Antibiotic();
        } else if (name.equalsIgnoreCase("I")) {
            return new Insulin();
        } else if (name.equalsIgnoreCase("P")) {
            return new Paracetamol();
        }

        //Default Not Found Drug
        return null;
    }
}
