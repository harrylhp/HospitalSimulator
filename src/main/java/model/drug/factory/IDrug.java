package model.drug.factory;

public interface IDrug {
    /**
     * For Drug Name as "Aspirin, Insulin.."
     */
    void setName(String name);

    String getName();

    /**
     * For DrugLabel as "As (Aspirin), I(Insulin).."
     */
    void setLabel(String label);

    String getLabel();
}
