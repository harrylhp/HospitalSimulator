Quick Detail about Hospital Simulator:
================================================
Patients can have one of these states: (F Fever / H Healthy / D Diabetes / T Tuberculosis / X Dead)

This is the list of available drugs: (As Aspirin / An Antibiotic / I Insulin / P Paracetamol)

Drug effects are described by the following rules:

  ➔ Aspirin cures Fever

  ➔ Antibiotic cures Tuberculosis

  ➔ Insulin prevents diabetic subjects from dying, does not cure Diabetes

  ➔ Insulin mixed with Antibiotic causes Healthy people to catch Fever

  ➔ ParacetamolcuresFever

  ➔ Paracetamol kills subjects if mixed with Aspirin

  ➔ One time in a million the Flying Spaghetti Monster shows his noodly power and resurrects a Dead patient, the patient becomes Healthy

Input Example:

Case 1: 
  ➔ Input: D,D

  ➔ Output: F:0,H:0,D:0,T:0,X:2

  ➔ Diabetic patients die without Insulin.

Case 2: 
  ➔ Input: F,P

  ➔ Output: F:0,H:1,D:0,T:0,X:2

  ➔ Paracetamol cures Fever.
  
User Guide To Setup:
================================================

1.Import the project into your IDE and use Maven to build it with command line : mvn package

2.Open Command Line and go to the build folder

3.Run the program with command line

>java -jar HospitalSimulator-1.0-SNAPSHOT.jar input1 input2

Example of input1 : D,D,F

Example of input2 : As,P


