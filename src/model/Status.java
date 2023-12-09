package model;

import java.util.Objects;
import java.util.UUID;

public class Status {
    private UUID uuid;
    private String nama;

    public Status() {
        this.uuid = UUID.randomUUID();
        this.nama = "";
    }

    public Status(UUID uuid, String nama) {
        this.uuid = uuid;
        this.nama = nama;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.uuid);
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
        final Status other = (Status) obj;
        return Objects.equals(this.uuid, other.uuid);
    }

    @Override
    public String toString() {
        return "Status{" + "uuid=" + uuid + ", nama=" + nama + '}';
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
