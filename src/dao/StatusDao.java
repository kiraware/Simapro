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
import model.Status;

public class StatusDao {
    private static final Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(StatusDao.class.getName());

    public void add(Status status) {
        String insert = "INSERT INTO `status` (uuid, nama) VALUES ('" + status.getUuid() + "','" + status.getNama() + "')";

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

    public List<Status> all() {
        String query = "SELECT * FROM `status`";
        List<Status> statuses = new ArrayList<>();

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                Status status = new Status(UUID.fromString(rs.getString("uuid")), rs.getString("nama"));
                statuses.add(status);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return statuses;
    }
    
    public Status get(UUID uuid) {
        String query = "SELECT * FROM `status` WHERE `uuid`='" + uuid + "'";
        Status status = null;

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                status = new Status(UUID.fromString(rs.getString("uuid")), rs.getString("nama"));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return status;
    }

    public void edit(UUID uuid, Status status) {
        String update = "UPDATE `status` SET `uuid`='" + status.getUuid() + "',`nama`='" + status.getNama() + "' WHERE uuid='" + uuid
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
        String delete = "DELETE FROM `status` WHERE uuid='" + uuid + "'";

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
