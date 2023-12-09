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
import model.Tugas;

public class TugasDao {
    private static final Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(TugasDao.class.getName());

    public void add(Tugas tugas) {
        String insert = "INSERT INTO `tugas` (uuid, nama, deskripsi, uuidJadwal, uuidStatus, uuidTim) VALUES ('" + tugas.getUuid() + "','" + tugas.getNama() + "','" + tugas.getDeskripsi() + "','" + tugas.getUuidJadwal() + "','" + tugas.getUuidStatus() + "','" + tugas.getUuidTim() + "')";

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

    public List<Tugas> all() {
        String query = "SELECT * FROM `tugas`";
        List<Tugas> tugases = new ArrayList<>();

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                Tugas tugas = new Tugas(UUID.fromString(rs.getString("uuid")), rs.getString("nama"), rs.getString("deskripsi"), UUID.fromString(rs.getString("uuidJadwal")), UUID.fromString(rs.getString("uuidStatus")), UUID.fromString(rs.getString("uuidTim")));
                tugases.add(tugas);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return tugases;
    }
    
    public Tugas get(UUID uuid) {
        String query = "SELECT * FROM `tugas` WHERE `uuid`='" + uuid + "'";
        Tugas tugas = null;

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                tugas = new Tugas(UUID.fromString(rs.getString("uuid")), rs.getString("nama"), rs.getString("deskripsi"), UUID.fromString(rs.getString("uuidJadwal")), UUID.fromString(rs.getString("uuidStatus")), UUID.fromString(rs.getString("uuidTim")));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return tugas;
    }

    public void edit(UUID uuid, Tugas tugas) {
        String update = "UPDATE `tugas` SET `uuid`='" + tugas.getUuid() + "',`nama`='" + tugas.getNama() + "',`deskripsi`='" + tugas.getDeskripsi() + "',`uuidJadwal`='" + tugas.getUuidJadwal()+ "',`uuidStatus`='" + tugas.getUuidStatus() + "',`uuidTim`='" + tugas.getUuidTim() + "' WHERE uuid='" + uuid
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
        String delete = "DELETE FROM `tugas` WHERE uuid='" + uuid + "'";

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
