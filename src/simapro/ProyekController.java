package simapro;

import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import dao.ProyekDao;
import dao.TimDao;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Proyek;
import model.Tim;

public class ProyekController implements Initializable {

    private final ProyekDao proyekDao = new ProyekDao();
    private final TimDao timDao = new TimDao();
    
    private UUID uuidTim;

    @FXML
    private TableView<Proyek> tvProyeks;
    @FXML
    private TableColumn<Proyek, UUID> colUuidTim;
    @FXML
    private TableColumn<Proyek, String> colNama;
    @FXML
    private TableColumn<Proyek, String> colDeskripsi;
    @FXML
    private TableColumn<Proyek, LocalDate> colTanggalMulai;
    @FXML
    private TableColumn<Proyek, LocalDate> colTanggalSelesai;
    @FXML
    private TableColumn<Proyek, Double> colAnggaran;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnBack;
    @FXML
    private Button btnDelete;
    @FXML
    private Button btnUpdate;
    @FXML
    private MenuButton mBtnUuidTim;
    @FXML
    private TextField tfNama;
    @FXML
    private TextArea taDeskripsi;
    @FXML
    private DatePicker dpTanggalMulai;
    @FXML
    private DatePicker dpTanggalSelesai;
    @FXML
    private TextField tfAnggaran;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showProyeks();
        showTims();
    }
    
    public ObservableList<Proyek> getProyeksList() {
        return FXCollections.observableList(proyekDao.all());
    }
    
    public ObservableList<Tim> getTimsList() {
        return FXCollections.observableList(timDao.all());
    }

    public void showProyeks() {
        ObservableList<Proyek> proyeks = getProyeksList();

        colUuidTim.setCellValueFactory(new PropertyValueFactory<>("uuidTim"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colDeskripsi.setCellValueFactory(new PropertyValueFactory<>("deskripsi"));
        colTanggalMulai.setCellValueFactory(new PropertyValueFactory<>("tanggalMulai"));
        colTanggalSelesai.setCellValueFactory(new PropertyValueFactory<>("tanggalSelesai"));
        colAnggaran.setCellValueFactory(new PropertyValueFactory<>("anggaran"));

        tvProyeks.setItems(proyeks);
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

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAdd) {
            Proyek proyek = new Proyek(uuidTim, tfNama.getText(), taDeskripsi.getText(), dpTanggalMulai.getValue(), dpTanggalSelesai.getValue(), Double.valueOf(tfAnggaran.getText()));
            proyekDao.add(proyek);
            showProyeks();
            uuidTim = null;
            mBtnUuidTim.setText("Pilih Tim");
            tfNama.setText(null);
            taDeskripsi.setText(null);
            dpTanggalMulai.setValue(null);
            dpTanggalSelesai.setValue(null);
            tfAnggaran.setText(null);
        } else if (event.getSource() == btnUpdate) {
            proyekDao.edit(new Proyek(uuidTim, tfNama.getText(), taDeskripsi.getText(), dpTanggalMulai.getValue(), dpTanggalSelesai.getValue(), Double.valueOf(tfAnggaran.getText())));
            showProyeks();
        } else if (event.getSource() == btnDelete) {
            proyekDao.delete(uuidTim);
            showProyeks();
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
