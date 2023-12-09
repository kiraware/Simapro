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

public class ProyekModel {
    private static final Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(ProyekModel.class.getName());

    public void add(Proyek proyek) {
        String insert = "INSERT INTO `proyek` (uuid, nama, deskripsi, tanggalMulai, tanggalSelesai, anggaran, uuidTim) VALUES ('" + proyek.getUuid() + "','" + proyek.getNama() + "','" + proyek.getDeskripsi() + "','" + proyek.getTanggalMulai() + "','" + proyek.getTanggalSelesai() + "','" + proyek.getAnggaran()+ "','" + proyek.getUuidTim() + "')";

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

    public List<Proyek> all() {
        String query = "SELECT * FROM `proyek`";
        List<Proyek> proyekes = new ArrayList<>();

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                Proyek proyek = new Proyek(UUID.fromString(rs.getString("uuid")), rs.getString("nama"), rs.getString("deskripsi"), rs.getDate("tanggalMulai").toLocalDate(), rs.getDate("tanggalSelesai").toLocalDate(), rs.getDouble("anggaran"), UUID.fromString(rs.getString("uuidTim")));
                proyekes.add(proyek);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return proyekes;
    }
    
    public Proyek get(UUID uuid) {
        String query = "SELECT * FROM `proyek` WHERE `uuid`='" + uuid + "'";
        Proyek proyek = null;

        try {
            ResultSet rs = CONN.createStatement().executeQuery(query);

            while (rs.next()) {
                proyek = new Proyek(UUID.fromString(rs.getString("uuid")), rs.getString("nama"), rs.getString("deskripsi"), rs.getDate("tanggalMulai").toLocalDate(), rs.getDate("tanggalSelesai").toLocalDate(), rs.getDouble("anggaran"), UUID.fromString(rs.getString("uuidTim")));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return proyek;
    }

    public void edit(UUID uuid, Proyek proyek) {
        String update = "UPDATE `proyek` SET `uuid`='" + proyek.getUuid() + "',`nama`='" + proyek.getNama() + "',`deskripsi`='" + proyek.getDeskripsi() + "',`tanggalMulai`='" + proyek.getTanggalMulai()+ "',`tanggalSelesai`='" + proyek.getTanggalSelesai() + "',`anggaran`='" + proyek.getAnggaran()+ "',`uuidTim`='" + proyek.getUuidTim() + "' WHERE uuid='" + uuid
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
        String delete = "DELETE FROM `proyek` WHERE uuid='" + uuid + "'";

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
