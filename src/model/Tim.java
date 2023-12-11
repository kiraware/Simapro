package model;

import java.util.Objects;
import java.util.UUID;

public class Tim {
    private final UUID uuid;
    private String nama;

    public Tim() {
        this.uuid = UUID.randomUUID();
        this.nama = "";
    }

    public Tim(UUID uuid, String nama) {
        this.uuid = uuid;
        this.nama = nama;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Tim other = (Tim) obj;
        return Objects.equals(this.uuid, other.uuid);
    }

    @Override
    public String toString() {
        return "Tim{" + "uuid=" + uuid + ", nama=" + nama + '}';
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
