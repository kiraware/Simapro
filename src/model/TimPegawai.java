package model;

import java.util.Objects;
import java.util.UUID;

public class TimPegawai {
    private final UUID uuidTugas;
    private UUID uuidTim;
    private UUID uuidPegawai;
    private UUID uuidJabatan;

    public TimPegawai() {
        this.uuidTugas = UUID.randomUUID();
        this.uuidTim = null;
        this.uuidPegawai = null;
        this.uuidJabatan = null;
    }

    public TimPegawai(UUID uuidTugas, UUID uuidTim, UUID uuidPegawai, UUID uuidJabatan) {
        this.uuidTugas = uuidTugas;
        this.uuidTim = uuidTim;
        this.uuidPegawai = uuidPegawai;
        this.uuidJabatan = uuidJabatan;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.uuidTugas);
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
        return Objects.equals(this.uuidTugas, other.uuidTugas);
    }

    @Override
    public String toString() {
        return "TimPegawai{" + "uuidTugas=" + uuidTugas + ", uuidTim=" + uuidTim + ", uuidPegawai=" + uuidPegawai + ", uuidJabatan=" + uuidJabatan + '}';
    }

    public UUID getUuidTugas() {
        return uuidTugas;
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

    public UUID getUuidJabatan() {
        return uuidJabatan;
    }

    public void setUuidJabatan(UUID uuidJabatan) {
        this.uuidJabatan = uuidJabatan;
    }
}
