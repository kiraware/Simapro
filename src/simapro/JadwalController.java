package simapro;

import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import dao.JadwalDao;
import dao.TugasDao;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Jadwal;
import model.Tugas;

public class JadwalController implements Initializable {

    private final JadwalDao jadwalDao = new JadwalDao();
    private final TugasDao tugasDao = new TugasDao();
    
    private UUID uuidTugas;

    @FXML
    private TableView<Jadwal> tvJadwals;
    @FXML
    private TableColumn<Jadwal, UUID> colUuidTugas;
    @FXML
    private TableColumn<Jadwal, LocalDate> colTanggalMulai;
    @FXML
    private TableColumn<Jadwal, LocalDate> colTanggalSelesai;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private MenuButton mBtnUuidTugas;
    @FXML
    private DatePicker dpTanggalMulai;
    @FXML
    private DatePicker dpTanggalSelesai;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showJadwals();
        showTugass();
    }
    
    public ObservableList<Jadwal> getJadwalsList() {
        return FXCollections.observableList(jadwalDao.all());
    }
    
    public ObservableList<Tugas> getTugassList() {
        return FXCollections.observableList(tugasDao.all());
    }

    public void showJadwals() {
        ObservableList<Jadwal> jadwals = getJadwalsList();

        colUuidTugas.setCellValueFactory(new PropertyValueFactory<>("uuidTugas"));
        colTanggalMulai.setCellValueFactory(new PropertyValueFactory<>("tanggalMulai"));
        colTanggalSelesai.setCellValueFactory(new PropertyValueFactory<>("tanggalSelesai"));

        tvJadwals.setItems(jadwals);
    }

    public void showTugass() {
        ObservableList<Tugas> tugass = getTugassList();

        for (Tugas tugas : tugass) {
            MenuItem item = new MenuItem(tugas.getNama());
            item.setOnAction(a -> {
               uuidTugas = tugas.getUuid();
               mBtnUuidTugas.setText(tugas.getNama());
            });
            mBtnUuidTugas.getItems().add(item);
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAdd) {
            Jadwal jadwal = new Jadwal(uuidTugas, dpTanggalMulai.getValue(), dpTanggalSelesai.getValue());
            jadwalDao.add(jadwal);
            showJadwals();
            uuidTugas = null;
            mBtnUuidTugas.setText("Pilih Tugas");
            dpTanggalMulai.setValue(null);
            dpTanggalSelesai.setValue(null);
        } else if (event.getSource() == btnUpdate) {
            jadwalDao.edit(new Jadwal(uuidTugas, dpTanggalMulai.getValue(), dpTanggalSelesai.getValue()));
            showJadwals();
        } else if (event.getSource() == btnDelete) {
            jadwalDao.delete(uuidTugas);
            showJadwals();
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
