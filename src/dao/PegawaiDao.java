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
import model.Pegawai;

public class PegawaiDao {
    private static Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(PegawaiDao.class.getName());

    public PegawaiDao(Connection conn) {
        CONN = conn;
    }

    public void add(Pegawai pegawai) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("INSERT INTO `pegawai` (uuid, nama) VALUES (?, ?)");
            preparedStatement.setString(1, pegawai.getUuid().toString());
            preparedStatement.setString(2, pegawai.getNama());

            if (preparedStatement.executeUpdate() > 0) {
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
                Pegawai pegawai = new Pegawai(UUID.fromString(rs.getString("uuid")), rs.getString("nama"));
                pegawais.add(pegawai);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return pegawais;
    }
    
    public Pegawai get(UUID uuid) {
        Pegawai pegawai = null;

        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("SELECT * FROM `pegawai` WHERE `uuid`=?");
            preparedStatement.setString(1, uuid.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                pegawai = new Pegawai(UUID.fromString(rs.getString("uuid")), rs.getString("nama"));
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return pegawai;
    }

    public void edit(Pegawai pegawai) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("UPDATE `pegawai` SET `nama`=? WHERE `uuid`=?");
            preparedStatement.setString(1, pegawai.getNama());
            preparedStatement.setString(2, pegawai.getUuid().toString());

            if (preparedStatement.executeUpdate() > 0) {
                logger.log(Level.INFO, "Berhasil mengubah data");
            } else {
                logger.log(Level.INFO, "Gagal mengubah data");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void delete(UUID uuid) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("DELETE FROM `pegawai` WHERE `uuid`=?");
            preparedStatement.setString(1, uuid.toString());
            
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
