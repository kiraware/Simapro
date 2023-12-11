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
import model.Jadwal;

public class JadwalDao {
    private static Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(JadwalDao.class.getName());

    public JadwalDao(Connection conn) {
        CONN = conn;
    }

    public void add(Jadwal jadwal) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("INSERT INTO `jadwal` (uuidTugas, tanggalMulai, tanggalSelesai) VALUES (?, ?, ?)");
            preparedStatement.setString(1, jadwal.getUuidTugas().toString());
            preparedStatement.setString(2, jadwal.getTanggalMulai().toString());
            preparedStatement.setString(3, jadwal.getTanggalSelesai().toString());

            if (preparedStatement.executeUpdate() > 0) {
                logger.log(Level.INFO, "Berhasil memasukkan data");
            } else {
                logger.log(Level.INFO, "Gagal memasukkan data");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public List<Jadwal> all() {
        String query = "SELECT * FROM `jadwal`";
        List<Jadwal> jadwals = new ArrayList<>();

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                Jadwal jadwal = new Jadwal(UUID.fromString(rs.getString("uuidTugas")), rs.getDate("tanggalMulai").toLocalDate(), rs.getDate("tanggalSelesai").toLocalDate());
                jadwals.add(jadwal);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return jadwals;
    }
    
    public Jadwal get(UUID uuidTugas) {
        Jadwal jadwal = null;

        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("SELECT * FROM `jadwal` WHERE `uuidTugas`=?");
            preparedStatement.setString(1, uuidTugas.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                jadwal = new Jadwal(UUID.fromString(rs.getString("uuidTugas")), rs.getDate("tanggalMulai").toLocalDate(), rs.getDate("tanggalSelesai").toLocalDate());
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return jadwal;
    }

    public void edit(Jadwal jadwal) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("UPDATE `jadwal` SET `tanggalMulai`=?, `tanggalSelesai`=? WHERE `uuidTugas`=?");
            preparedStatement.setString(1, jadwal.getTanggalMulai().toString());
            preparedStatement.setString(2, jadwal.getTanggalSelesai().toString());
            preparedStatement.setString(3, jadwal.getUuidTugas().toString());

            if (preparedStatement.executeUpdate() > 0) {
                logger.log(Level.INFO, "Berhasil mengubah data");
            } else {
                logger.log(Level.INFO, "Gagal mengubah data");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void delete(UUID uuidTugas) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("DELETE FROM `jadwal` WHERE `uuidTugas`=?");
            preparedStatement.setString(1, uuidTugas.toString());
            
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
