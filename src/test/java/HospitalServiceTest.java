import model.patient.Patient;
import model.patient.PatientState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HospitalServiceTest {
    HospitalService hospitalService;

    @BeforeEach
    public void initialize() {
        hospitalService = new HospitalService();
    }

    @Test
    @DisplayName("Test With No Drug Available")
    void testWithNoDrugAvailable() {
        String patientStateList = "D,D";

        hospitalService.performHospitalAction(patientStateList, null);
        assertEquals("F:0,H:0,D:0,T:0,X:2", hospitalService.getFinalResultPatientList());
    }

    @Test
    @DisplayName("Test With No Drug Available With More Patients")
    void testWithNoDrugAvailableMorePatients() {
        String patientStateList = "F,F,T,D,D,X,H";

        hospitalService.performHospitalAction(patientStateList, null);
        assertEquals("F:2,H:1,D:0,T:1,X:3", hospitalService.getFinalResultPatientList());
    }

    @Test
    @DisplayName("Test With No Insulin Drug")
    void testWithNoInsulin() {
        String patientStateList = "D,D";
        String drugAvailableList = "As,An,P";

        hospitalService.performHospitalAction(patientStateList, drugAvailableList);
        assertEquals("F:0,H:0,D:0,T:0,X:2", hospitalService.getFinalResultPatientList());
    }

    @Test
    @DisplayName("Test With Duplicate Drug Name")
    void testWithDuplicateDrug() {
        String patientStateList = "F,H,D,T,X,F,F,D";
        String drugAvailableList = "An,P,P,An,I";

        hospitalService.performHospitalAction(patientStateList, drugAvailableList);
        assertEquals("F:0,H:7,D:0,T:0,X:1", hospitalService.getFinalResultPatientList());
    }

    @Test
    @DisplayName("Test With Wrong Medication Aspirin & Paracetamol")
    void testWithWrongMedication_As_P() {
        String patientStateList = "F,H,D,T,X,F,F,D";
        String drugAvailableList = "As,An,P,P,An,I";

        hospitalService.performHospitalAction(patientStateList, drugAvailableList);
        assertEquals("F:0,H:0,D:0,T:0,X:8", hospitalService.getFinalResultPatientList());
    }

    @Test
    @DisplayName("Test With Wrong Medication Antibiotic & Insulin")
    void testWithWrongMedication_I_An_1() {
        String patientStateList = "F,F,F,F,H,D,D,T,T,X";
        String drugAvailableList = "An,I";

        hospitalService.performHospitalAction(patientStateList, drugAvailableList);
        assertEquals("F:9,H:0,D:0,T:0,X:1", hospitalService.getFinalResultPatientList());
    }

    @Test
    @DisplayName("Test With Wrong Medication Antibiotic & Insulin But With Aspirin")
    void testWithWrongMedication_I_An_2() {
        String patientStateList = "F,F,F,F,H,D,D,T,T,X";
        String drugAvailableList = "As,An,I";

        hospitalService.performHospitalAction(patientStateList, drugAvailableList);
        assertEquals("F:0,H:9,D:0,T:0,X:1", hospitalService.getFinalResultPatientList());
    }

    @Test
    @DisplayName("Test With Wrong Format For Patient List With Long String State")
    void testWithWrongFormatPatientListLongStringState() {
        String patientStateList = "DDXX";
        String drugAvailableList = "As,I";

        hospitalService.performHospitalAction(patientStateList, drugAvailableList);
        assertEquals("F:0,H:0,D:0,T:0,X:0", hospitalService.getFinalResultPatientList());
    }

    @Test
    @DisplayName("Test With Small Character Format For Patient List - Valid ")
    void testWithSmallCharFormatPatientList() {
        String patientStateList = "d,d";
        String drugAvailableList = "As,I";

        hospitalService.performHospitalAction(patientStateList, drugAvailableList);
        assertEquals("F:0,H:0,D:2,T:0,X:0", hospitalService.getFinalResultPatientList());
    }

    @Test
    @DisplayName("Test With Wrong Format For Drug List")
    void testWithWrongFormatPatientList() {
        String patientStateList = "D,D";
        String drugAvailableList = "AsAXX,IXGX";

        hospitalService.performHospitalAction(patientStateList, drugAvailableList);
        assertEquals("F:0,H:0,D:0,T:0,X:2", hospitalService.getFinalResultPatientList());
    }

    @Test
    @DisplayName("Test With No Input Argument")
    void testWithNoInputArgument() {
        hospitalService.performHospitalAction(null, null);
        assertEquals("F:0,H:0,D:0,T:0,X:0", hospitalService.getFinalResultPatientList());
    }

    @Test
    @DisplayName("Test Flying Spaghetti Monster Magic")
    void testFlyingSpaghettiMonster() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1500000; i++)
            sb.append("X").append(",");

        String patientStateList = sb.substring(0, sb.length() - 1);
        String drugAvailableList = "";

        hospitalService.performHospitalAction(patientStateList, drugAvailableList);
        ArrayList<Patient> patientsList = hospitalService.getPatientsList();

        String isWorking = "";
        //Find at least 1 Healthy in 1.5 mil run-time (to be safe), it should get at least one Resurrected Patient
        for (Patient patient : patientsList) {
            if (patient.getPatientState() == PatientState.HEALTHY) {
                isWorking = "Flying Spaghetti Monster Working";
                break;
            }
        }
        assertEquals("Flying Spaghetti Monster Working", isWorking);
    }

}