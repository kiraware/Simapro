package model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Proyek {
    private final UUID uuid;
    private String nama;
    private String deskripsi;
    private LocalDate tanggalMulai;
    private LocalDate tanggalSelesai;
    private double anggaran;
    private UUID uuidTim;

    public Proyek() {
        this.uuid = UUID.randomUUID();
        this.nama = "";
        this.deskripsi = "";
        this.tanggalMulai = null;
        this.tanggalSelesai = null;
        this.anggaran = 0;
        this.uuidTim = null;
    }

    public Proyek(UUID uuid, String nama, String deskripsi, LocalDate tanggalMulai, LocalDate tanggalSelesai, double anggaran, UUID uuidTim) {
        this.uuid = uuid;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
        this.anggaran = anggaran;
        this.uuidTim = uuidTim;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + Objects.hashCode(this.uuid);
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
        return Objects.equals(this.uuid, other.uuid);
    }

    @Override
    public String toString() {
        return "Proyek{" + "uuid=" + uuid + ", nama=" + nama + ", deskripsi=" + deskripsi + ", tanggalMulai=" + tanggalMulai + ", tanggalSelesai=" + tanggalSelesai + ", anggaran=" + anggaran + ", uuidTim=" + uuidTim + '}';
    }

    public UUID getUuid() {
        return uuid;
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

    public UUID getUuidTim() {
        return uuidTim;
    }

    public void setUuidTim(UUID uuidTim) {
        this.uuidTim = uuidTim;
    }
}
