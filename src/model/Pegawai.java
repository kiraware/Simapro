package model;

import java.util.Objects;
import java.util.UUID;

public class Pegawai {
    private final UUID uuid;
    private String nama;

    public Pegawai() {
        this.uuid = UUID.randomUUID();
        this.nama = "";
    }

    public Pegawai(UUID uuid, String nama) {
        this.uuid = uuid;
        this.nama = nama;
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
        if (!Objects.equals(this.nama, other.nama)) {
            return false;
        }
        return Objects.equals(this.uuid, other.uuid);
    }

    @Override
    public String toString() {
        return "Pegawai{" + "uuid=" + uuid + ", nama=" + nama + '}';
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
}
