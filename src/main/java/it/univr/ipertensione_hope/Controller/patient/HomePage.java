package it.univr.ipertensione_hope.Controller.patient;

import it.univr.ipertensione_hope.Controller.BloodPressure;
import it.univr.ipertensione_hope.View.WindowsManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomePage implements Initializable {

    @FXML
    public Text mainText;
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;
    Boolean dailyPressure;
    Boolean hasMedicineToTake;
    boolean doItLaterPressure;
    boolean doItLaterPrescription;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
           doItLaterPressure = false;
           doItLaterPrescription = false;
           //checkList();
    }

    /*
    public void checkList(){

        try {
            if (!doItLaterPressure)
                dailyPressure = BloodPressure.hasDailyPressure();
            if(!doItLaterPrescription)
                hasMedicineToTake = Medications.hasMedicineToTake();


        } catch (SQLException e){
            e.printStackTrace();
        }

        if(!dailyPressure){
            mainText.setText("You haven't submitted any blood pressure readings today. Would you like to do it now?");
            yesButton.setOnAction(event -> openBloodPressureWindow());
            noButton.setOnAction(event -> {
                dailyPressure = true;
                doItLaterPressure = true;
                checkList();
            });
        } else if (hasMedicineToTake) {
            mainText.setText("You have some medications to report. Would you like to do it now?");
            yesButton.setOnAction(event -> openTakePrescriptionWindow());
            noButton.setOnAction(event -> {
                hasMedicineToTake = false;
                doItLaterPrescription = true;
                checkList();
            });
        } else {
            mainText.setText("Would you like to report any new Symptoms?");
            yesButton.setOnAction(event -> openSymptomsWindow());
            noButton.setText("No, I'm Fine!");
            noButton.setOnAction(event -> {
                if(doItLaterPrescription && doItLaterPressure)
                    mainText.setText("Don't forget to report your medications and pressure data!");
                if(doItLaterPrescription)
                    mainText.setText("Don't forget to report your medications!");
                else if(doItLaterPressure)
                    mainText.setText("Don't forget to report today's blood pressure!");
                else
                    mainText.setText("Great! You are all set for today. See you tomorrow!");
                noButton.setVisible(false);
                yesButton.setVisible(false);
            });
        }
    }


     */



    private void openBloodPressureWindow(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WindowsManager.mainClass.getResource("../../View/BloodPressure.fxml"));
            Parent root1 = fxmlLoader.load();

            BloodPressure bloodPressure = fxmlLoader.getController();
            bloodPressure.setHomePageController(this);
            bloodPressure.changeAddButton();


            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            // Specifies the owner Window (parent) for new window
            stage.setScene(new Scene(root1));
            stage.setTitle("BloodMonitor - Blood Pressure Data");
            stage.setResizable(false);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
    private void openTakePrescriptionWindow(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WindowsManager.mainClass.getResource("../../View/patient/Medications.fxml"));
            Parent root1 = fxmlLoader.load();

            Medications medications = fxmlLoader.getController();

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            // Specifies the owner Window (parent) for new window
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.setTitle("BloodMonitor - My Medications");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void openSymptomsWindow(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(WindowsManager.mainClass.getResource("../../View/patient/ViewSymptomsPatient.fxml"));
            Parent root1 = fxmlLoader.load();

            ViewSymptomsPatient viewSymptomsPatient = fxmlLoader.getController();
            viewSymptomsPatient.setHomePageController(this);
            viewSymptomsPatient.changeAddButton();

            Stage stage = new Stage();
            stage.initModality(Modality.WINDOW_MODAL);
            // Specifies the owner Window (parent) for new window
            stage.setScene(new Scene(root1));
            stage.setResizable(false);
            stage.setTitle("BloodMonitor - Report Symptoms");
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
