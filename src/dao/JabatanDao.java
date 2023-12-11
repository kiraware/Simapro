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
import model.Jabatan;

public class JabatanDao {
    private static Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(JabatanDao.class.getName());

    public JabatanDao(Connection conn) {
        CONN = conn;
    }

    public void add(Jabatan jabatan) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("INSERT INTO `jabatan` (uuid, nama) VALUES (?, ?)");
            preparedStatement.setString(1, jabatan.getUuid().toString());
            preparedStatement.setString(2, jabatan.getNama());

            if (preparedStatement.executeUpdate() > 0) {
                logger.log(Level.INFO, "Berhasil memasukkan data");
            } else {
                logger.log(Level.INFO, "Gagal memasukkan data");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public List<Jabatan> all() {
        String query = "SELECT * FROM `jabatan`";
        List<Jabatan> jabatans = new ArrayList<>();

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                Jabatan jabatan = new Jabatan(UUID.fromString(rs.getString("uuid")), rs.getString("nama"));
                jabatans.add(jabatan);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return jabatans;
    }
    
    public Jabatan get(UUID uuid) {
        Jabatan jabatan = null;

        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("SELECT * FROM `jabatan` WHERE `uuid`=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                jabatan = new Jabatan(UUID.fromString(rs.getString("uuid")), rs.getString("nama"));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return jabatan;
    }

    public void edit(Jabatan jabatan) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("UPDATE `jabatan` SET `nama`=? WHERE `uuid`=?");
            preparedStatement.setString(1, jabatan.getNama());
            preparedStatement.setString(2, jabatan.getUuid().toString());

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
            PreparedStatement preparedStatement = CONN.prepareStatement("DELETE FROM `jabatan` WHERE `uuid`=?");
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
