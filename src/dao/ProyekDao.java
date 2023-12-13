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
import model.Proyek;

public class ProyekDao {
    private static Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(ProyekDao.class.getName());

    public ProyekDao() {
    }

    public ProyekDao(Connection conn) {
        CONN = conn;
    }

    public void add(Proyek proyek) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("INSERT INTO `proyek` (uuidTim, nama, deskripsi, tanggalMulai, tanggalSelesai, anggaran) VALUES (?, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, proyek.getUuidTim().toString());
            preparedStatement.setString(2, proyek.getNama());
            preparedStatement.setString(3, proyek.getDeskripsi());
            preparedStatement.setString(4, proyek.getTanggalMulai().toString());
            preparedStatement.setString(5, proyek.getTanggalSelesai().toString());
            preparedStatement.setString(6, String.valueOf(proyek.getAnggaran()));

            if (preparedStatement.executeUpdate() > 0) {
                logger.log(Level.INFO, "Berhasil memasukkan data");
            } else {
                logger.log(Level.INFO, "Gagal memasukkan data");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public List<Proyek> all() {
        String query = "SELECT * FROM `proyek`";
        List<Proyek> proyeks = new ArrayList<>();

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                Proyek proyek = new Proyek(UUID.fromString(rs.getString("uuidTim")), rs.getString("nama"), rs.getString("deskripsi"), rs.getDate("tanggalMulai").toLocalDate(), rs.getDate("tanggalSelesai").toLocalDate(), rs.getDouble("anggaran"));
                proyeks.add(proyek);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return proyeks;
    }
    
    public Proyek get(UUID uuidTim) {
        Proyek proyek = null;

        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("SELECT * FROM `proyek` WHERE `uuidTim`=?");
            preparedStatement.setString(1, uuidTim.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                proyek = new Proyek(UUID.fromString(rs.getString("uuidTim")), rs.getString("nama"), rs.getString("deskripsi"), rs.getDate("tanggalMulai").toLocalDate(), rs.getDate("tanggalSelesai").toLocalDate(), rs.getDouble("anggaran"));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return proyek;
    }

    public void edit(Proyek proyek) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("UPDATE `proyek` SET `nama`=?, `deskripsi`=?, `tanggalMulai`=?, `tanggalSelesai`=?, `anggaran`=? WHERE `uuidTim`=?");
            preparedStatement.setString(1, proyek.getNama());
            preparedStatement.setString(2, proyek.getDeskripsi());
            preparedStatement.setString(3, proyek.getTanggalMulai().toString());
            preparedStatement.setString(4, proyek.getTanggalSelesai().toString());
            preparedStatement.setString(5, String.valueOf(proyek.getAnggaran()));
            preparedStatement.setString(6, proyek.getUuidTim().toString());

            if (preparedStatement.executeUpdate() > 0) {
                logger.log(Level.INFO, "Berhasil mengubah data");
            } else {
                logger.log(Level.INFO, "Gagal mengubah data");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void delete(UUID uuidTim) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("DELETE FROM `proyek` WHERE `uuidTim`=?");
            preparedStatement.setString(1, uuidTim.toString());
            
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
