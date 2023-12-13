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
import model.TimPegawai;

public class TimPegawaiDao {
    private static Connection CONN = DBHelper.getConnection();
    private static final Logger logger = Logger.getLogger(TimPegawaiDao.class.getName());

    public TimPegawaiDao() {
    }

    public TimPegawaiDao(Connection conn) {
        CONN = conn;
    }

    public void add(TimPegawai timPegawai) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("INSERT INTO `timpegawai` (uuidTugas, uuidTim, uuidPegawai, uuidJabatan) VALUES (?, ?, ?, ?)");
            preparedStatement.setString(1, timPegawai.getUuidTugas().toString());
            preparedStatement.setString(2, timPegawai.getUuidTim() != null ? timPegawai.getUuidTim().toString() : null);
            preparedStatement.setString(3, timPegawai.getUuidPegawai() != null ? timPegawai.getUuidPegawai().toString() : null);
            preparedStatement.setString(4, timPegawai.getUuidJabatan() != null ? timPegawai.getUuidJabatan().toString() : null);

            if (preparedStatement.executeUpdate() > 0) {
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
                TimPegawai timPegawai = new TimPegawai(UUID.fromString(rs.getString("uuidTugas")), rs.getString("uuidTim") != null ? UUID.fromString(rs.getString("uuidTim")) : null, rs.getString("uuidPegawai") != null ? UUID.fromString(rs.getString("uuidPegawai")) : null, rs.getString("uuidJabatan") != null ? UUID.fromString(rs.getString("uuidJabatan")) : null);
                timPegawais.add(timPegawai);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return timPegawais;
    }
    
    public TimPegawai get(UUID uuidTugas) {
        TimPegawai timPegawai = null;

        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("SELECT * FROM `timpegawai` WHERE `uuidTugas`=?");
            preparedStatement.setString(1, uuidTugas.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                timPegawai = new TimPegawai(UUID.fromString(rs.getString("uuidTugas")), rs.getString("uuidTim") != null ? UUID.fromString(rs.getString("uuidTim")) : null, rs.getString("uuidPegawai") != null ? UUID.fromString(rs.getString("uuidPegawai")) : null, rs.getString("uuidJabatan") != null ? UUID.fromString(rs.getString("uuidJabatan")) : null);
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }

        return timPegawai;
    }

    public void edit(TimPegawai timPegawai) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("UPDATE `timpegawai` SET `uuidTim`=?, `uuidPegawai`=?, `uuidJabatan`=? WHERE `uuidTugas`=?");
            preparedStatement.setString(1, timPegawai.getUuidTim() != null ? timPegawai.getUuidTim().toString() : null);
            preparedStatement.setString(2, timPegawai.getUuidPegawai() != null ? timPegawai.getUuidPegawai().toString() : null);
            preparedStatement.setString(3, timPegawai.getUuidJabatan() != null ? timPegawai.getUuidJabatan().toString() : null);
            preparedStatement.setString(4, timPegawai.getUuidTugas().toString());

            if (preparedStatement.executeUpdate() > 0) {
                logger.log(Level.INFO, "Berhasil mengubah data");
            } else {
                logger.log(Level.INFO, "Gagal mengubah data");
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    public void delete(UUID uuidTugas) {
        try {
            PreparedStatement preparedStatement = CONN.prepareStatement("DELETE FROM `timpegawai` WHERE `uuidTugas`=?");
            preparedStatement.setString(1, uuidTugas.toString());
            
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
