import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HospitalSimulatorMainApp {
    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(HospitalSimulatorMainApp.class);
        HospitalService hospital = new HospitalService();
        String patientStateList, drugAvailableList = null;

        if (args.length == 0) {
            logger.info("Invalid argument ! Please provide at least 1 argument");
            return;
        } else if (args.length == 1) {
            patientStateList = args[0];
        } else if (args.length == 2) {
            patientStateList = args[0];
            drugAvailableList = args[1];
        } else {
            logger.info("Too many arguments ! Please provide maximum 2 arguments");
            return;
        }

        hospital.performHospitalAction(patientStateList, drugAvailableList);
    }
}

