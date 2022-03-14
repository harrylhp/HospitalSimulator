public class HospitalSimulatorMainApp {
    public static void main(String [] args){
        HospitalService hospital = new HospitalService();
        String patientStateList = null , drugAvailableList = null;

        if(args.length == 0){
            System.out.println("Invalid argument ! Please provide at least 1 argument");
            return;
        }
        else if (args.length == 1){
            patientStateList = args[0];
        }
        else if (args.length == 2){
            patientStateList = args[0];
            drugAvailableList = args[1];
        }
        else {
            System.out.println("Too many arguments ! Please provide maximum 2 arguments");
            return;
        }

        hospital.performHospitalAction(patientStateList,drugAvailableList);
    }
}

