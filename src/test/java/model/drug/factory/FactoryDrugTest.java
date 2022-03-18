package model.drug.factory;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FactoryDrugTest {
    @Test
    public void testFactory() {
        String[] drugLabels = new String[]{"As", "An", "I", "P", "Unknown", "As ", "An ", "I ", "P "};

        for (String label : drugLabels) {
            if (label.equalsIgnoreCase("As")) {
                assertEquals("Aspirin", FactoryDrug.getDrugType(label).getName());
                assertEquals("As", FactoryDrug.getDrugType(label).getLabel());
            } else if (label.equalsIgnoreCase("An")) {
                assertEquals("Antibiotic", FactoryDrug.getDrugType(label).getName());
                assertEquals("An", FactoryDrug.getDrugType(label).getLabel());
            } else if (label.equalsIgnoreCase("I")) {
                assertEquals("Insulin", FactoryDrug.getDrugType(label).getName());
                assertEquals("I", FactoryDrug.getDrugType(label).getLabel());
            } else if (label.equalsIgnoreCase("P")) {
                assertEquals("Paracetamol", FactoryDrug.getDrugType(label).getName());
                assertEquals("P", FactoryDrug.getDrugType(label).getLabel());
            } else if (label.equalsIgnoreCase("Unknown")) {
                assertNull(FactoryDrug.getDrugType(label));
            } else {
                assertNull(FactoryDrug.getDrugType(label));
            }
        }
        System.out.println("TEST PASSED!");
    }

}