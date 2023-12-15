package simapro;

import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import dao.StatusDao;
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
import model.Status;

public class StatusController implements Initializable {

    private final StatusDao statusDao = new StatusDao();

    @FXML
    private TextField tfUuid;
    @FXML
    private TextField tfNama;
    @FXML
    private TableView<Status> tvStatuses;
    @FXML
    private TableColumn<Status, UUID> colUuid;
    @FXML
    private TableColumn<Status, String> colNama;
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
        showStatuses();
    }
    
    public ObservableList<Status> getStatusesList() {
        return FXCollections.observableList(statusDao.all());
    }

    public void showStatuses() {
        ObservableList<Status> statuses = getStatusesList();

        colUuid.setCellValueFactory(new PropertyValueFactory<>("uuid"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        
        tvStatuses.setItems(statuses);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAdd) {
            Status status = new Status();
            status.setNama(tfNama.getText());
            statusDao.add(status);
            showStatuses();
            tfNama.setText("");
        } else if (event.getSource() == btnUpdate) {
            statusDao.edit(new Status(UUID.fromString(tfUuid.getText()), tfNama.getText()));
            showStatuses();
        } else if (event.getSource() == btnDelete) {
            statusDao.delete(UUID.fromString(tfUuid.getText()));
            showStatuses();
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
