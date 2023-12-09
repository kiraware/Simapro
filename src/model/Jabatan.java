package model;

import java.util.Objects;
import java.util.UUID;

public class Jabatan {
    private UUID uuid;
    private String nama;

    public Jabatan() {
        this.uuid = UUID.randomUUID();
        this.nama = "";
    }

    public Jabatan(UUID uuid, String nama) {
        this.uuid = uuid;
        this.nama = nama;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Jabatan other = (Jabatan) obj;
        return Objects.equals(this.uuid, other.uuid);
    }

    @Override
    public String toString() {
        return "Jabatan{" + "uuid=" + uuid + ", nama=" + nama + '}';
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
}