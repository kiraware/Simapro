package dao;

import db.DBHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;
import model.Jadwal;

public class JadwalDao {
    private static final Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(JadwalDao.class.getName());

    public void add(Jadwal jadwal) {
        String insert = "INSERT INTO `jadwal` (uuid, tanggalMulai, tanggalSelesai, uuidTugas) VALUES ('" + jadwal.getUuid() + "','" + jadwal.getTanggalMulai()+ "','" + jadwal.getTanggalSelesai() + "','" + jadwal.getUuidTugas() + "')";

        try {
            if (CONN.createStatement().executeUpdate(insert) > 0) {
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
        List<Jadwal> jadwales = new ArrayList<>();

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                Jadwal jadwal = new Jadwal(UUID.fromString(rs.getString("uuid")), rs.getDate("tanggalMulai").toLocalDate(), rs.getDate("tanggalSelesai").toLocalDate(), UUID.fromString(rs.getString("uuidTugas")));
                jadwales.add(jadwal);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return jadwales;
    }
    
    public Jadwal get(UUID uuid) {
        String query = "SELECT * FROM `jadwal` WHERE `uuid`='" + uuid + "'";
        Jadwal jadwal = null;

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                jadwal = new Jadwal(UUID.fromString(rs.getString("uuid")), rs.getDate("tanggalMulai").toLocalDate(), rs.getDate("tanggalSelesai").toLocalDate(), UUID.fromString(rs.getString("uuidTugas")));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return jadwal;
    }

    public void edit(UUID uuid, Jadwal jadwal) {
        String update = "UPDATE `jadwal` SET `uuid`='" + jadwal.getUuid() + "',`tanggalMulai`='" + jadwal.getTanggalMulai() + "',`tanggalSelesai`='" + jadwal.getTanggalSelesai() + "',`uuidTugas`='" + jadwal.getUuidTugas() + "' WHERE uuid='" + uuid
                + "'";
        System.out.println(update);

        try {
            if (CONN.createStatement().executeUpdate(update) > 0) {
                logger.log(Level.INFO, "Berhasil mengubah data");
            } else {
                logger.log(Level.INFO, "Gagal mengubah data");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void delete(UUID uuid) {
        String delete = "DELETE FROM `jadwal` WHERE uuid='" + uuid + "'";

        try {
            if (CONN.createStatement().executeUpdate(delete) > 0) {
                logger.log(Level.INFO, "Berhasil menghapus data");
            } else {
                logger.log(Level.INFO, "Gagal menghapus data");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}
