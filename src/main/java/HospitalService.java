import model.drug.factory.FactoryDrug;
import model.patient.Patient;
import model.patient.PatientState;

import java.util.*;

public class HospitalService {
    //For list of Patients
    private final ArrayList<Patient> patientsList = new ArrayList<>();

    //Use hashmap to track number of Patients for each State
    private final HashMap<PatientState, Integer> mStates_Counts = new HashMap<>();

    //Use hashset (to remove duplicates drugName) for drugsList as we only need to know how many type of drugs available
    private final HashSet<String> drugsList = new HashSet<>();

    //For result string F:#P,H:#P,D:#P,T:#P,X:#P
    private String sFinalResultPatientList = "";

    public void performHospitalAction(String patientStateList, String drugAvailableList){
        countPatients(processInput(patientStateList));
        checkAvailableDrugList(processInput(drugAvailableList));
        patientsTreatment();
        countResultPatientsEachState();

        //Print out the result String
        System.out.println(buildResultPatientString());

    }
    /*
        Process the input for patient state & drug to produce the String[] of states
     */
    public String [] processInput(String str){
        if (str == null)
            return null;

        //Remove whitespace if there is any
        return str.trim().split(",");
    }

    /*
        Get the list of Patients from input argument
     */
    public void countPatients(String [] patientsStateList){
        if (patientsStateList == null)
            return;

        for (String s : patientsStateList) {
            PatientState state = PatientState.getState(s.toUpperCase());
            //Only when the state is inside the defined list of state then add the patient into List
            if (state != null)
                patientsList.add(new Patient(state));
        }
    }

    /*
        1.Get the list of available drugs from input argument.
        2. Remove duplicate drugName as we only care about how many type of drugs
     */
    public void checkAvailableDrugList(String [] listOfDrugs){
        if(listOfDrugs == null)
            return;

        //Remove duplicate drugs from input
        HashSet<String> setOfDrugs = new HashSet<>(Arrays.asList(listOfDrugs));

        for(String str : setOfDrugs){
            //Only when the drug is known then add into the list
            if (FactoryDrug.getDrugType(str) != null)
                drugsList.add(FactoryDrug.getDrugType(str).getLabel());
        }
    }

    /*
        Count the number of patients each state
        F:#P,H:#P,D:#P,T:#P,X:#P
     */
    public void countResultPatientsEachState(){
        initializeResultPatients();

        if (patientsList.size() == 0)
            return;

        for (Patient patient : patientsList) {
            //Add to patientsList
            PatientState state = patient.getPatientState();

            switch (state) {
                case FEVER:
                    mStates_Counts.put(PatientState.FEVER, mStates_Counts.getOrDefault(PatientState.FEVER, 0) + 1);
                    break;
                case HEALTHY:
                    mStates_Counts.put(PatientState.HEALTHY, mStates_Counts.getOrDefault(PatientState.HEALTHY, 0) + 1);
                    break;
                case DIABETES:
                    mStates_Counts.put(PatientState.DIABETES, mStates_Counts.getOrDefault(PatientState.DIABETES, 0) + 1);
                    break;
                case TUBERCULOSIS:
                    mStates_Counts.put(PatientState.TUBERCULOSIS, mStates_Counts.getOrDefault(PatientState.TUBERCULOSIS, 0) + 1);
                    break;
                case DEAD:
                    mStates_Counts.put(PatientState.DEAD, mStates_Counts.getOrDefault(PatientState.DEAD, 0) + 1);
                    break;
            }
        }
    }

    /*
        Initialize the hashmap for (State, Count)
     */
    public void initializeResultPatients(){
        mStates_Counts.put(PatientState.FEVER, 0);
        mStates_Counts.put(PatientState.HEALTHY, 0);
        mStates_Counts.put(PatientState.DIABETES, 0);
        mStates_Counts.put(PatientState.TUBERCULOSIS, 0);
        mStates_Counts.put(PatientState.DEAD, 0);
    }

    public String buildResultPatientString(){
        StringBuilder str = new StringBuilder();
        for (PatientState state : PatientState.values()){
            str.append(state.getStateShortCode()).append(":").append(mStates_Counts.get(state)).append(",");
        }
        str = new StringBuilder(str.substring(0, str.length() - 1));
        return sFinalResultPatientList = str.toString();
    }

    /*
        Perform treatment with List of available drugs for each patient
     */
    public void patientsTreatment(){
        for (Patient patient : patientsList){
            patient.receiveMedication(drugsList);
        }
    }

    /*
       To get the final output String which could be useful for comparison in JUnit Test
    */
    public String getFinalResultPatientList(){
        return sFinalResultPatientList;
    }

    /*
       To get the patient List for comparison in JUnit Test
    */
    public ArrayList<Patient> getPatientsList(){
        return patientsList;
    }

}
