package model;

import java.util.Objects;
import java.util.UUID;

public class Pegawai {
    private UUID uuid;
    private String nama;
    private UUID uuidJabatan;

    public Pegawai() {
        this.uuid = UUID.randomUUID();
        this.nama = "";
        this.uuidJabatan = null;
    }

    public Pegawai(UUID uuid, String nama, UUID uuidJabatan) {
        this.uuid = uuid;
        this.nama = nama;
        this.uuidJabatan = uuidJabatan;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.uuid);
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
        final Pegawai other = (Pegawai) obj;
        return Objects.equals(this.uuid, other.uuid);
    }

    @Override
    public String toString() {
        return "Pegawai{" + "uuid=" + uuid + ", nama=" + nama + ", uuidJabatan=" + uuidJabatan + '}';
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

    public UUID getUuidJabatan() {
        return uuidJabatan;
    }

    public void setUuidJabatan(UUID uuidJabatan) {
        this.uuidJabatan = uuidJabatan;
    }
}
