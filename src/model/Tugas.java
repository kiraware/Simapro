package model;

import java.util.Objects;
import java.util.UUID;

public class Tugas {
    private final UUID uuid;
    private String nama;
    private String deskripsi;
    private UUID uuidStatus;

    public Tugas() {
        this.uuid = UUID.randomUUID();
        this.nama = "";
        this.deskripsi = "";
        this.uuidStatus = null;
    }

    public Tugas(UUID uuid, String nama, String deskripsi, UUID uuidStatus) {
        this.uuid = uuid;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.uuidStatus = uuidStatus;
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
        if (!Objects.equals(this.nama, other.nama)) {
            return false;
        }
        if (!Objects.equals(this.deskripsi, other.deskripsi)) {
            return false;
        }
        if (!Objects.equals(this.uuid, other.uuid)) {
            return false;
        }
        return Objects.equals(this.uuidStatus, other.uuidStatus);
    }

    @Override
    public String toString() {
        return "Tugas{" + "uuid=" + uuid + ", nama=" + nama + ", deskripsi=" + deskripsi + ", uuidStatus=" + uuidStatus + '}';
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

    public UUID getUuidStatus() {
        return uuidStatus;
    }

    public void setUuidStatus(UUID uuidStatus) {
        this.uuidStatus = uuidStatus;
    }
}
