package model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Proyek {
    private final UUID uuidTim;
    private String nama;
    private String deskripsi;
    private LocalDate tanggalMulai;
    private LocalDate tanggalSelesai;
    private double anggaran;

    public Proyek() {
        this.uuidTim = UUID.randomUUID();
        this.nama = "";
        this.deskripsi = "";
        this.tanggalMulai = null;
        this.tanggalSelesai = null;
        this.anggaran = 0;
    }

    public Proyek(UUID uuidTim, String nama, String deskripsi, LocalDate tanggalMulai, LocalDate tanggalSelesai, double anggaran) {
        this.uuidTim = uuidTim;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
        this.anggaran = anggaran;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.uuidTim);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proyek other = (Proyek) obj;
        return Objects.equals(this.uuidTim, other.uuidTim);
    }

    @Override
    public String toString() {
        return "Proyek{" + "uuidTim=" + uuidTim + ", nama=" + nama + ", deskripsi=" + deskripsi + ", tanggalMulai=" + tanggalMulai + ", tanggalSelesai=" + tanggalSelesai + ", anggaran=" + anggaran + '}';
    }

    public UUID getUuidTim() {
        return uuidTim;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public LocalDate getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(LocalDate tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public LocalDate getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(LocalDate tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public double getAnggaran() {
        return anggaran;
    }

    public void setAnggaran(double anggaran) {
        this.anggaran = anggaran;
    }
}
