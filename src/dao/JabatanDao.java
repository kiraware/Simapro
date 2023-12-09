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
import model.Jabatan;

public class JabatanDao {
    private static final Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(JabatanDao.class.getName());

    public void add(Jabatan jabatan) {
        String insert = "INSERT INTO `jabatan` (uuid, nama) VALUES ('" + jabatan.getUuid() + "','" + jabatan.getNama() + "')";

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
        String query = "SELECT * FROM `jabatan` WHERE `uuid`='" + uuid + "'";
        Jabatan jabatan = null;

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                jabatan = new Jabatan(UUID.fromString(rs.getString("uuid")), rs.getString("nama"));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return jabatan;
    }

    public void edit(UUID uuid, Jabatan jabatan) {
        String update = "UPDATE `jabatan` SET `uuid`='" + jabatan.getUuid() + "',`nama`='" + jabatan.getNama() + "' WHERE uuid='" + uuid
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
        String delete = "DELETE FROM `jabatan` WHERE uuid='" + uuid + "'";

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
