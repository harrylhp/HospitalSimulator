package model.patient;

import model.drug.Antibiotic;
import model.drug.Aspirin;
import model.drug.Insulin;
import model.drug.Paracetamol;
import java.util.HashSet;
import java.util.Random;

public class Patient {
    private PatientState patientState;

    public Patient(PatientState patientState){
        this.patientState = patientState;
    }

    public void setPatientState(PatientState patientState){
        this.patientState = patientState;
    }

    public PatientState getPatientState() {
        return patientState;
    }

    public void receiveMedication(HashSet<String> drugsList){
        //Check any wrong prescription before proceeding with treatment
        //1. DrugList contains: Aspirin + Paracetamol => PatientState = DEAD, no necessary to go through treatment to reduce runtime
        //2. DrugList contains: Insulin + Antibiotic => PatientState = FEVER, will be treated again with either Aspirin or Paracetamol
        receiveUnProperMedication(drugsList);

        switch(patientState){
            case FEVER:
                if (drugsList.contains(new Aspirin().getLabel()) || drugsList.contains(new Paracetamol().getLabel()))
                    patientState = PatientState.HEALTHY;
                break;
            case HEALTHY:
                break;
            case DIABETES:
                //For Patient with Diabetes if no Insulin, state = DEAD.
                //With Insulin, State no change
                if (!drugsList.contains(new Insulin().getLabel())) {
                    patientState = PatientState.DEAD;
                    receiveMiracleFlyingSpaghettiMonster(); //Last Miracle
                }
                break;
            case TUBERCULOSIS:
                if (drugsList.contains(new Antibiotic().getLabel()))
                    patientState = PatientState.HEALTHY;
                break;
            case DEAD:
                //Check any miracle from
                receiveMiracleFlyingSpaghettiMonster();
                break;
        }
    }

    /*
        Two type of wrong drug combination treatment
        1. If list drugs contains Aspirin + Paracetamol => Update all patients state = DEAD
        2. If list drugs contains Insulin + Antibiotic
           >> Healthy person will get FEVER, it means DIABETES or TUBERCULOSIS will also get FEVER
     */
    public void receiveUnProperMedication(HashSet<String> drugsList){
        if (drugsList.contains(new Aspirin().getLabel()) && drugsList.contains(new Paracetamol().getLabel()))
            patientState = PatientState.DEAD;
        else if (drugsList.contains(new Insulin().getLabel()) && drugsList.contains(new Antibiotic().getLabel())) {
            if(patientState != PatientState.DEAD) //Cannot change DEAD to FEVER, only Flying Spaghetti Monster can do it
                patientState = PatientState.FEVER;
        }
    }

    /*
        One time in a million the Flying Spaghetti Monster resurrects a Dead patient, the patient becomes Healthy.
        Logic : Run a random generation, check the chance it will equal to a Fixed Number
     */
    public void receiveMiracleFlyingSpaghettiMonster(){
        Random random = new Random();
        int randomChance = random.nextInt(1000000);
        if (randomChance == 12345){
            System.out.println("Flying Spaghetti Monster shows his noodly power and resurrects a Dead patient");
            patientState = PatientState.HEALTHY;
        }
    }

}
