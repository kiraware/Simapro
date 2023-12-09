package simapro;

import db.DBHelper;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.UUID;

public class TimPegawaiModel {
    private static final Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(TimPegawaiModel.class.getName());

    public void add(TimPegawai timPegawai) {
        String insert = "INSERT INTO `timPegawai` (uuid, uuidTim, uuidPegawai) VALUES ('" + timPegawai.getUuid() + "','" + timPegawai.getUuidTim() + "','" + timPegawai.getUuidPegawai() + "')";

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

    public List<TimPegawai> all() {
        String query = "SELECT * FROM `timPegawai`";
        List<TimPegawai> timPegawais = new ArrayList<>();

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                TimPegawai timPegawai = new TimPegawai(UUID.fromString(rs.getString("uuid")), UUID.fromString(rs.getString("uuidTim")), UUID.fromString(rs.getString("uuidPegawai")));
                timPegawais.add(timPegawai);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return timPegawais;
    }
    
    public TimPegawai get(UUID uuid) {
        String query = "SELECT * FROM `timPegawai` WHERE `uuid`='" + uuid + "'";
        TimPegawai timPegawai = null;

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                timPegawai = new TimPegawai(UUID.fromString(rs.getString("uuid")), UUID.fromString(rs.getString("uuidTim")), UUID.fromString(rs.getString("uuidPegawai")));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return timPegawai;
    }

    public void edit(UUID uuid, TimPegawai timPegawai) {
        String update = "UPDATE `timPegawai` SET `uuid`='" + timPegawai.getUuid() + "',`uuidTim`='" + timPegawai.getUuidTim() + "',`uuidPegawai`='" + timPegawai.getUuidPegawai() + "' WHERE uuid='" + uuid
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
        String delete = "DELETE FROM `timPegawai` WHERE uuid='" + uuid + "'";

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
