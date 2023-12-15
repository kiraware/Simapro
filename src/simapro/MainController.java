package simapro;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController implements Initializable {

    @FXML
    private Button btnJabatan;
    @FXML
    private Button btnPegawai;
    @FXML
    private Button btnTim;
    @FXML
    private Button btnStatus;
    @FXML
    private Button btnProyek;
    @FXML
    private Button btnTugas;
    @FXML
    private Button btnJadwal;
    @FXML
    private Button btnTimPegawai;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        String targetScene = null;
        if (event.getSource() == btnJabatan) {
            targetScene = "jabatan";
        } else if (event.getSource() == btnPegawai) {
            targetScene = "pegawai";
        } else if (event.getSource() == btnTim) {
            targetScene = "tim";
        } else if (event.getSource() == btnStatus) {
            targetScene = "status";
        } else if (event.getSource() == btnProyek) {
            targetScene = "proyek";
        } else if (event.getSource() == btnTugas) {
            targetScene = "tugas";
        } else if (event.getSource() == btnJadwal) {
            targetScene = "jadwal";
        } else if (event.getSource() == btnTimPegawai) {
            targetScene = "timpegawai";
        }

        Parent loader = null;
        try {
            loader = FXMLLoader.load(getClass().getResource("/simapro/" + targetScene + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }

        Scene scene = new Scene(loader);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(scene);

        app_stage.show();
    }
}
