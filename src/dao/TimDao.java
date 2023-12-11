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
import model.Tim;

public class TimDao {
    private static Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(TimDao.class.getName());

    public TimDao(Connection conn) {
        CONN = conn;
    }

    public void add(Tim tim) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("INSERT INTO `tim` (uuid, nama) VALUES (?, ?)");
            preparedStatement.setString(1, tim.getUuid().toString());
            preparedStatement.setString(2, tim.getNama());

            if (preparedStatement.executeUpdate() > 0) {
                logger.log(Level.INFO, "Berhasil memasukkan data");
            } else {
                logger.log(Level.INFO, "Gagal memasukkan data");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public List<Tim> all() {
        String query = "SELECT * FROM `tim`";
        List<Tim> tims = new ArrayList<>();

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                Tim tim = new Tim(UUID.fromString(rs.getString("uuid")), rs.getString("nama"));
                tims.add(tim);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return tims;
    }
    
    public Tim get(UUID uuid) {
        Tim tim = null;

        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("SELECT * FROM `tim` WHERE `uuid`=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                tim = new Tim(UUID.fromString(rs.getString("uuid")), rs.getString("nama"));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return tim;
    }

    public void edit(Tim tim) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("UPDATE `tim` SET `nama`=? WHERE `uuid`=?");
            preparedStatement.setString(1, tim.getNama());
            preparedStatement.setString(2, tim.getUuid().toString());

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
            PreparedStatement preparedStatement = CONN.prepareStatement("DELETE FROM `tim` WHERE `uuid`=?");
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
