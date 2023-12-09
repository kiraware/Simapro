package simapro;

import java.util.Objects;
import java.util.UUID;

public class Tugas {
    private UUID uuid;
    private String nama;
    private String deskripsi;
    private UUID uuidJadwal;
    private UUID uuidStatus;
    private UUID uuidTim;

    public Tugas() {
        this.uuid = UUID.randomUUID();
        this.nama = "";
        this.deskripsi = "";
        this.uuidJadwal = null;
        this.uuidStatus = null;
        this.uuidTim = null;
    }

    public Tugas(UUID uuid, String nama, String deskripsi, UUID uuidJadwal, UUID uuidStatus, UUID uuidTim) {
        this.uuid = uuid;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.uuidJadwal = uuidJadwal;
        this.uuidStatus = uuidStatus;
        this.uuidTim = uuidTim;
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
        return "Tugas{" + "uuid=" + uuid + ", nama=" + nama + ", deskripsi=" + deskripsi + ", uuidJadwal=" + uuidJadwal + ", uuidStatus=" + uuidStatus + ", uuidTim=" + uuidTim + '}';
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public UUID getUuidTim() {
        return uuidTim;
    }

    public void setUuidTim(UUID uuidTim) {
        this.uuidTim = uuidTim;
    }
}