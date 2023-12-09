package simapro;

import java.util.Objects;
import java.util.UUID;

public class Tim {
    private UUID uuid;
    private String nama;
    private UUID uuidProyek;

    public Tim() {
        this.uuid = UUID.randomUUID();
        this.nama = "";
        this.uuidProyek = null;
    }

    public Tim(UUID uuid, String nama, UUID uuidProyek) {
        this.uuid = uuid;
        this.nama = nama;
        this.uuidProyek = uuidProyek;
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
        return "Tim{" + "uuid=" + uuid + ", nama=" + nama + ", uuidProyek=" + uuidProyek + '}';
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

    public UUID getUuidProyek() {
        return uuidProyek;
    }

    public void setUuidProyek(UUID uuidProyek) {
        this.uuidProyek = uuidProyek;
    }
}
