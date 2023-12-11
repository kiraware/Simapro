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
import model.Status;

public class StatusDao {
    private static Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(StatusDao.class.getName());

    public StatusDao(Connection conn) {
        CONN = conn;
    }

    public void add(Status status) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("INSERT INTO `status` (uuid, nama) VALUES (?, ?)");
            preparedStatement.setString(1, status.getUuid().toString());
            preparedStatement.setString(2, status.getNama());

            if (preparedStatement.executeUpdate() > 0) {
                logger.log(Level.INFO, "Berhasil memasukkan data");
            } else {
                logger.log(Level.INFO, "Gagal memasukkan data");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public List<Status> all() {
        String query = "SELECT * FROM `status`";
        List<Status> statuss = new ArrayList<>();

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                Status status = new Status(UUID.fromString(rs.getString("uuid")), rs.getString("nama"));
                statuss.add(status);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return statuss;
    }
    
    public Status get(UUID uuid) {
        Status status = null;

        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("SELECT * FROM `status` WHERE `uuid`=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                status = new Status(UUID.fromString(rs.getString("uuid")), rs.getString("nama"));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return status;
    }

    public void edit(Status status) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("UPDATE `status` SET `nama`=? WHERE `uuid`=?");
            preparedStatement.setString(1, status.getNama());
            preparedStatement.setString(2, status.getUuid().toString());

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
            PreparedStatement preparedStatement = CONN.prepareStatement("DELETE FROM `status` WHERE `uuid`=?");
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
