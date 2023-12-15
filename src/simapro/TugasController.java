package simapro;

import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import dao.StatusDao;
import dao.TugasDao;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Status;
import model.Tugas;

public class TugasController implements Initializable {

    private final StatusDao statusDao = new StatusDao();
    private final TugasDao tugasDao = new TugasDao();
    
    private UUID uuidStatus;

    @FXML
    private TextField tfUuid;
    @FXML
    private TextField tfNama;
    @FXML
    private TableView<Tugas> tvTugass;
    @FXML
    private TableColumn<Tugas, UUID> colUuid;
    @FXML
    private TableColumn<Tugas, String> colNama;
    @FXML
    private TableColumn<Tugas, String> colDeskripsi;
    @FXML
    private TableColumn<Tugas, UUID> colUuidStatus;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private TextArea taDeskripsi;
    @FXML
    private MenuButton mBtnUuidStatus;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTugass();
        showStatuses();
    }
    
    public ObservableList<Status> getStatusesList() {
        return FXCollections.observableList(statusDao.all());
    }
    
    public ObservableList<Tugas> getTugassList() {
        return FXCollections.observableList(tugasDao.all());
    }

    public void showStatuses() {
        ObservableList<Status> statuses = getStatusesList();

        for (Status status : statuses) {
            MenuItem item = new MenuItem(status.getNama());
            item.setOnAction(a -> {
               uuidStatus = status.getUuid();
               mBtnUuidStatus.setText(status.getNama());
            });
            mBtnUuidStatus.getItems().add(item);
        }
    }

    public void showTugass() {
        ObservableList<Tugas> tugass = getTugassList();

        colUuid.setCellValueFactory(new PropertyValueFactory<>("uuid"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
        colUuidStatus.setCellValueFactory(new PropertyValueFactory<>("uuidStatus"));
        
        tvTugass.setItems(tugass);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAdd) {
            Tugas tugas = new Tugas();
            tugas.setNama(tfNama.getText());
            tugas.setDeskripsi(taDeskripsi.getText());
            tugas.setUuidStatus(uuidStatus);
            tugasDao.add(tugas);
            showTugass();
            tfNama.setText("");
            taDeskripsi.setText("");
            uuidStatus = null;
            mBtnUuidStatus.setText("Pilih Status");
        } else if (event.getSource() == btnUpdate) {
            tugasDao.edit(new Tugas(UUID.fromString(tfUuid.getText()), tfNama.getText(), taDeskripsi.getText(), uuidStatus));
            showTugass();
        } else if (event.getSource() == btnDelete) {
            tugasDao.delete(UUID.fromString(tfUuid.getText()));
            showTugass();
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
