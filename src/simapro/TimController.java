package simapro;

import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import dao.TimDao;
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
import model.Tim;

public class TimController implements Initializable {

    private final TimDao timDao = new TimDao();

    @FXML
    private TextField tfUuid;
    @FXML
    private TextField tfNama;
    @FXML
    private TableColumn<Tim, UUID> colUuid;
    @FXML
    private TableColumn<Tim, String> colNama;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TableView<Tim> tvTims;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTims();
    }
    
    public ObservableList<Tim> getTimsList() {
        return FXCollections.observableList(timDao.all());
    }

    public void showTims() {
        ObservableList<Tim> tims = getTimsList();

        colUuid.setCellValueFactory(new PropertyValueFactory<>("uuid"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        
        tvTims.setItems(tims);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAdd) {
            Tim tim = new Tim();
            tim.setNama(tfNama.getText());
            timDao.add(tim);
            showTims();
            tfNama.setText("");
        } else if (event.getSource() == btnUpdate) {
            timDao.edit(new Tim(UUID.fromString(tfUuid.getText()), tfNama.getText()));
            showTims();
        } else if (event.getSource() == btnDelete) {
            timDao.delete(UUID.fromString(tfUuid.getText()));
            showTims();
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
