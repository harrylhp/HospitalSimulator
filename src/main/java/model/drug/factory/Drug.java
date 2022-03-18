package model.drug.factory;

public abstract class Drug implements IDrug {
    private String drugName;
    private String drugLabel;

    public Drug(String drugLabel, String drugName) {
        this.drugName = drugName;
        this.drugLabel = drugLabel;
    }

    public void setName(String drugName) {
        this.drugName = drugName;
    }

    public String getName() {
        return drugName;
    }

    public void setLabel(String drugLabel) {
        this.drugLabel = drugLabel;
    }

    public String getLabel() {
        return drugLabel;
    }
}
