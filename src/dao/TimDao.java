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
import model.Tim;

public class TimDao {
    private static final Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(TimDao.class.getName());

    public void add(Tim tim) {
        String insert = "INSERT INTO `tim` (uuid, nama, uuidProyek) VALUES ('" + tim.getUuid() + "','" + tim.getNama() + "','" + tim.getUuidProyek() + "')";

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

    public List<Tim> all() {
        String query = "SELECT * FROM `tim`";
        List<Tim> tims = new ArrayList<>();

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                Tim tim = new Tim(UUID.fromString(rs.getString("uuid")), rs.getString("nama"), UUID.fromString(rs.getString("uuidProyek")));
                tims.add(tim);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return tims;
    }
    
    public Tim get(UUID uuid) {
        String query = "SELECT * FROM `tim` WHERE `uuid`='" + uuid + "'";
        Tim tim = null;

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                tim = new Tim(UUID.fromString(rs.getString("uuid")), rs.getString("nama"), UUID.fromString(rs.getString("uuidProyek")));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return tim;
    }

    public void edit(UUID uuid, Tim tim) {
        String update = "UPDATE `tim` SET `uuid`='" + tim.getUuid() + "',`nama`='" + tim.getNama() + "',`uuidProyek`='" + tim.getUuidProyek() + "' WHERE uuid='" + uuid
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
        String delete = "DELETE FROM `tim` WHERE uuid='" + uuid + "'";

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
