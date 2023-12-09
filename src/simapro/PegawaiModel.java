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

public class PegawaiModel {
    private static final Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(PegawaiModel.class.getName());

    public void add(Pegawai pegawai) {
        String insert = "INSERT INTO `pegawai` (uuid, nama, uuidJabatan) VALUES ('" + pegawai.getUuid() + "','" + pegawai.getNama() + "','" + pegawai.getUuidJabatan() + "')";

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

    public List<Pegawai> all() {
        String query = "SELECT * FROM `pegawai`";
        List<Pegawai> pegawais = new ArrayList<>();

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                Pegawai pegawai = new Pegawai(UUID.fromString(rs.getString("uuid")), rs.getString("nama"), UUID.fromString(rs.getString("uuidJabatan")));
                pegawais.add(pegawai);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return pegawais;
    }
    
    public Pegawai get(UUID uuid) {
        String query = "SELECT * FROM `pegawai` WHERE `uuid`='" + uuid + "'";
        Pegawai pegawai = null;

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                pegawai = new Pegawai(UUID.fromString(rs.getString("uuid")), rs.getString("nama"), UUID.fromString(rs.getString("uuidJabatan")));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return pegawai;
    }

    public void edit(UUID uuid, Pegawai pegawai) {
        String update = "UPDATE `pegawai` SET `uuid`='" + pegawai.getUuid() + "',`nama`='" + pegawai.getNama() + "',`uuidJabatan`='" + pegawai.getUuidJabatan() + "' WHERE uuid='" + uuid
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
        String delete = "DELETE FROM `pegawai` WHERE uuid='" + uuid + "'";

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
