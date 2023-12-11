package dao;

import db.DBHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;
import model.Tugas;

public class TugasDao {
    private static Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(TugasDao.class.getName());

    public TugasDao(Connection conn) {
        CONN = conn;
    }

    public void add(Tugas tugas) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("INSERT INTO `tugas` (uuid, nama, deskripsi, uuidStatus) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, tugas.getUuid().toString());
            preparedStatement.setString(2, tugas.getNama());
            preparedStatement.setString(3, tugas.getDeskripsi());
            preparedStatement.setString(4, tugas.getUuidStatus() != null ? tugas.getUuidStatus().toString() : null);

            if (preparedStatement.executeUpdate() > 0) {
                logger.log(Level.INFO, "Berhasil memasukkan data");
            } else {
                logger.log(Level.INFO, "Gagal memasukkan data");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public List<Tugas> all() {
        String query = "SELECT * FROM `tugas`";
        List<Tugas> tugass = new ArrayList<>();

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                Tugas tugas = new Tugas(UUID.fromString(rs.getString("uuid")), rs.getString("nama"), rs.getString("deskripsi"), rs.getString("uuidStatus") != null ? UUID.fromString(rs.getString("uuidStatus")) : null);
                tugass.add(tugas);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return tugass;
    }
    
    public Tugas get(UUID uuid) {
        Tugas tugas = null;

        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("SELECT * FROM `tugas` WHERE `uuid`=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                tugas = new Tugas(UUID.fromString(rs.getString("uuid")), rs.getString("nama"), rs.getString("deskripsi"), rs.getString("uuidStatus") != null ? UUID.fromString(rs.getString("uuidStatus")) : null);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return tugas;
    }

    public void edit(Tugas tugas) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("UPDATE `tugas` SET `nama`=?, `deskripsi`=?, `uuidStatus`=? WHERE `uuid`=?");
            preparedStatement.setString(1, tugas.getNama());
            preparedStatement.setString(2, tugas.getDeskripsi());
            preparedStatement.setString(3, tugas.getUuidStatus() != null ? tugas.getUuidStatus().toString() : null);
            preparedStatement.setString(4, tugas.getUuid().toString());

            if (preparedStatement.executeUpdate() > 0) {
                logger.log(Level.INFO, "Berhasil mengubah data");
            } else {
                logger.log(Level.INFO, "Gagal mengubah data");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void delete(UUID uuid) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("DELETE FROM `tugas` WHERE `uuid`=?");
            preparedStatement.setString(1, uuid.toString());
            
            if (preparedStatement.executeUpdate() > 0) {
                logger.log(Level.INFO, "Berhasil menghapus data");
            } else {
                logger.log(Level.INFO, "Gagal menghapus data");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
