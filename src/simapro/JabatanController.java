package simapro;

import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import dao.JabatanDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Jabatan;

public class JabatanController implements Initializable {

    private final JabatanDao jabatanDao = new JabatanDao();

    @FXML
    private TextField tfUuid;
    @FXML
    private TextField tfNama;
    @FXML
    private TableView<Jabatan> tvJabatans;
    @FXML
    private TableColumn<Jabatan, UUID> colUuid;
    @FXML
    private TableColumn<Jabatan, String> colNama;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showJabatans();
    }
    
    public ObservableList<Jabatan> getJabatansList() {
        return FXCollections.observableList(jabatanDao.all());
    }

    public void showJabatans() {
        ObservableList<Jabatan> jabatans = getJabatansList();

        colUuid.setCellValueFactory(new PropertyValueFactory<>("uuid"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        
        tvJabatans.setItems(jabatans);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAdd) {
            Jabatan jabatan = new Jabatan();
            jabatan.setNama(tfNama.getText());
            jabatanDao.add(jabatan);
            showJabatans();
            tfNama.setText("");
        } else if (event.getSource() == btnUpdate) {
            jabatanDao.edit(new Jabatan(UUID.fromString(tfUuid.getText()), tfNama.getText()));
            showJabatans();
        } else if (event.getSource() == btnDelete) {
            jabatanDao.delete(UUID.fromString(tfUuid.getText()));
            showJabatans();
        } else if (event.getSource() == btnBack) {
            Parent loader = null;
            try {
                loader = FXMLLoader.load(getClass().getResource("/simapro/main.fxml"));
            } catch (IOException ex) {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }

            Scene scene = new Scene(loader);

            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            app_stage.setScene(scene);

            app_stage.show();
        }
    }
}
