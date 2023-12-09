package model;

import java.util.Objects;
import java.util.UUID;

public class Tugas {
    private final UUID uuid;
    private String nama;
    private String deskripsi;
    private UUID uuidJadwal;
    private UUID uuidStatus;
    private UUID uuidTimPegawai;

    public Tugas() {
        this.uuid = UUID.randomUUID();
        this.nama = "";
        this.deskripsi = "";
        this.uuidJadwal = null;
        this.uuidStatus = null;
        this.uuidTimPegawai = null;
    }

    public Tugas(UUID uuid, String nama, String deskripsi, UUID uuidJadwal, UUID uuidStatus, UUID uuidTimPegawai) {
        this.uuid = uuid;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.uuidJadwal = uuidJadwal;
        this.uuidStatus = uuidStatus;
        this.uuidTimPegawai = uuidTimPegawai;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.uuid);
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
        final Tugas other = (Tugas) obj;
        return Objects.equals(this.uuid, other.uuid);
    }

    @Override
    public String toString() {
        return "Tugas{" + "uuid=" + uuid + ", nama=" + nama + ", deskripsi=" + deskripsi + ", uuidJadwal=" + uuidJadwal + ", uuidStatus=" + uuidStatus + ", uuidTimPegawai=" + uuidTimPegawai + '}';
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

    public UUID getUuidJadwal() {
        return uuidJadwal;
    }

    public void setUuidJadwal(UUID uuidJadwal) {
        this.uuidJadwal = uuidJadwal;
    }

    public UUID getUuidStatus() {
        return uuidStatus;
    }

    public void setUuidStatus(UUID uuidStatus) {
        this.uuidStatus = uuidStatus;
    }

    public UUID getUuidTimPegawai() {
        return uuidTimPegawai;
    }

    public void setUuidTimPegawai(UUID uuidTimPegawai) {
        this.uuidTimPegawai = uuidTimPegawai;
    }
}
