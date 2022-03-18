package model.patient;

public enum PatientState {
    FEVER("F"),
    HEALTHY("H"),
    DIABETES("D"),
    TUBERCULOSIS("T"),
    DEAD("X");

    private final String stateShortCode;

    /**
     * Constructor for Patient State
     */
    PatientState(String stateShortCode) {
        this.stateShortCode = stateShortCode;
    }

    public final String getStateShortCode() {
        return stateShortCode;
    }

    public static PatientState getState(String stateShortCode) {
        for (PatientState patientState : PatientState.values()) {
            if (patientState.getStateShortCode().equals(stateShortCode))
                return patientState;
        }
        return null;
    }
}
