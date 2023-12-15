package simapro;

import java.util.UUID;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import dao.PegawaiDao;
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
import model.Pegawai;

public class PegawaiController implements Initializable {

    private final PegawaiDao pegawaiDao = new PegawaiDao();

    @FXML
    private TextField tfUuid;
    @FXML
    private TextField tfNama;
    @FXML
    private TableView<Pegawai> tvPegawais;
    @FXML
    private TableColumn<Pegawai, UUID> colUuid;
    @FXML
    private TableColumn<Pegawai, String> colNama;
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
        showPegawais();
    }
    
    public ObservableList<Pegawai> getPegawaisList() {
        return FXCollections.observableList(pegawaiDao.all());
    }

    public void showPegawais() {
        ObservableList<Pegawai> pegawais = getPegawaisList();

        colUuid.setCellValueFactory(new PropertyValueFactory<>("uuid"));
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        
        tvPegawais.setItems(pegawais);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAdd) {
            Pegawai pegawai = new Pegawai();
            pegawai.setNama(tfNama.getText());
            pegawaiDao.add(pegawai);
            showPegawais();
            tfNama.setText("");
        } else if (event.getSource() == btnUpdate) {
            pegawaiDao.edit(new Pegawai(UUID.fromString(tfUuid.getText()), tfNama.getText()));
            showPegawais();
        } else if (event.getSource() == btnDelete) {
            pegawaiDao.delete(UUID.fromString(tfUuid.getText()));
            showPegawais();
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
