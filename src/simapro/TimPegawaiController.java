package simapro;

import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import dao.TimPegawaiDao;
import dao.TugasDao;
import dao.TimDao;
import dao.PegawaiDao;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.TimPegawai;
import model.Tugas;
import model.Tim;
import model.Pegawai;
import model.Jabatan;

public class TimPegawaiController implements Initializable {

    private final TimPegawaiDao timPegawaiDao = new TimPegawaiDao();
    private final TugasDao tugasDao = new TugasDao();
    private final TimDao timDao = new TimDao();
    private final PegawaiDao pegawaiDao = new PegawaiDao();
    private final JabatanDao jabatanDao = new JabatanDao();

    private UUID uuidTugas;
    private UUID uuidTim;
    private UUID uuidPegawai;
    private UUID uuidJabatan;

    @FXML
    private TableView<TimPegawai> tvTimPegawais;
    @FXML
    private TableColumn<TimPegawai, UUID> colUuidTugas;
    @FXML
    private TableColumn<TimPegawai, UUID> colUuidTim;
    @FXML
    private TableColumn<TimPegawai, UUID> colUuidPegawai;
    @FXML
    private TableColumn<TimPegawai, UUID> colUuidJabatan;
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
    private MenuButton mBtnUuidTim;
    @FXML
    private MenuButton mBtnUuidPegawai;
    @FXML
    private MenuButton mBtnUuidJabatan;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showTimPegawais();
        showTugass();
        showTims();
        showPegawais();
        showJabatans();
    }
    
    public ObservableList<TimPegawai> getTimPegawaisList() {
        return FXCollections.observableList(timPegawaiDao.all());
    }
    
    public ObservableList<Tugas> getTugassList() {
        return FXCollections.observableList(tugasDao.all());
    }
    
    public ObservableList<Tim> getTimsList() {
        return FXCollections.observableList(timDao.all());
    }
    
    public ObservableList<Pegawai> getPegawaisList() {
        return FXCollections.observableList(pegawaiDao.all());
    }
    
    public ObservableList<Jabatan> getJabatansList() {
        return FXCollections.observableList(jabatanDao.all());
    }

    public void showTimPegawais() {
        ObservableList<TimPegawai> timPegawais = getTimPegawaisList();

        colUuidTugas.setCellValueFactory(new PropertyValueFactory<>("uuidTugas"));
        colUuidTim.setCellValueFactory(new PropertyValueFactory<>("uuidTim"));
        colUuidPegawai.setCellValueFactory(new PropertyValueFactory<>("uuidPegawai"));
        colUuidJabatan.setCellValueFactory(new PropertyValueFactory<>("uuidJabatan"));

        tvTimPegawais.setItems(timPegawais);
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

    public void showTims() {
        ObservableList<Tim> tims = getTimsList();

        for (Tim tim : tims) {
            MenuItem item = new MenuItem(tim.getNama());
            item.setOnAction(a -> {
               uuidTim = tim.getUuid();
               mBtnUuidTim.setText(tim.getNama());
            });
            mBtnUuidTim.getItems().add(item);
        }
    }

    public void showPegawais() {
        ObservableList<Pegawai> pegawais = getPegawaisList();

        for (Pegawai pegawai : pegawais) {
            MenuItem item = new MenuItem(pegawai.getNama());
            item.setOnAction(a -> {
               uuidPegawai = pegawai.getUuid();
               mBtnUuidPegawai.setText(pegawai.getNama());
            });
            mBtnUuidPegawai.getItems().add(item);
        }
    }

    public void showJabatans() {
        ObservableList<Jabatan> jabatans = getJabatansList();

        for (Jabatan jabatan : jabatans) {
            MenuItem item = new MenuItem(jabatan.getNama());
            item.setOnAction(a -> {
               uuidJabatan = jabatan.getUuid();
               mBtnUuidJabatan.setText(jabatan.getNama());
            });
            mBtnUuidJabatan.getItems().add(item);
        }
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAdd) {
            TimPegawai timPegawai = new TimPegawai(uuidTugas, uuidTim, uuidPegawai, uuidJabatan);
            timPegawaiDao.add(timPegawai);
            showTimPegawais();
            uuidTugas = null;
            uuidTim = null;
            uuidPegawai = null;
            uuidJabatan = null;
            mBtnUuidTugas.setText("Pilih Tugas");
            mBtnUuidTim.setText("Pilih Tim");
            mBtnUuidPegawai.setText("Pilih Pegawai");
            mBtnUuidJabatan.setText("Pilih Jabatan");
        } else if (event.getSource() == btnUpdate) {
            timPegawaiDao.edit(new TimPegawai(uuidTugas, uuidTim, uuidPegawai, uuidJabatan));
            showTimPegawais();
        } else if (event.getSource() == btnDelete) {
            timPegawaiDao.delete(uuidTugas);
            showTimPegawais();
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
