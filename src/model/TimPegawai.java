package model;

import java.util.Objects;
import java.util.UUID;

public class TimPegawai {
    private UUID uuid;
    private UUID uuidTim;
    private UUID uuidPegawai;

    public TimPegawai() {
        this.uuid = UUID.randomUUID();
        this.uuidTim = null;
        this.uuidPegawai = null;
    }

    public TimPegawai(UUID uuid, UUID uuidTim, UUID uuidPegawai) {
        this.uuid = uuid;
        this.uuidTim = uuidTim;
        this.uuidPegawai = uuidPegawai;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.uuid);
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
        final TimPegawai other = (TimPegawai) obj;
        return Objects.equals(this.uuid, other.uuid);
    }

    @Override
    public String toString() {
        return "TimPegawai{" + "uuid=" + uuid + ", uuidTim=" + uuidTim + ", uuidPegawai=" + uuidPegawai + '}';
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getUuidTim() {
        return uuidTim;
    }

    public void setUuidTim(UUID uuidTim) {
        this.uuidTim = uuidTim;
    }

    public UUID getUuidPegawai() {
        return uuidPegawai;
    }

    public void setUuidPegawai(UUID uuidPegawai) {
        this.uuidPegawai = uuidPegawai;
    }
}
