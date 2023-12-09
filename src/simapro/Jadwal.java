package simapro;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Jadwal {
    private UUID uuid;
    private LocalDate tanggalMulai;
    private LocalDate tanggalSelesai;
    private UUID uuidTugas;

    public Jadwal() {
        this.uuid = UUID.randomUUID();
        this.tanggalMulai = null;
        this.tanggalSelesai = null;
        this.uuidTugas = null;
    }

    public Jadwal(UUID uuid, LocalDate tanggalMulai, LocalDate tanggalSelesai, UUID uuidTugas) {
        this.uuid = uuid;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
        this.uuidTugas = uuidTugas;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.uuid);
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
        final Jadwal other = (Jadwal) obj;
        return Objects.equals(this.uuid, other.uuid);
    }

    @Override
    public String toString() {
        return "Jadwal{" + "uuid=" + uuid + ", tanggalMulai=" + tanggalMulai + ", tanggalSelesai=" + tanggalSelesai + ", uuidTugas=" + uuidTugas + '}';
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDate getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(LocalDate tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public LocalDate getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(LocalDate tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public UUID getUuidTugas() {
        return uuidTugas;
    }

    public void setUuidTugas(UUID uuidTugas) {
        this.uuidTugas = uuidTugas;
    }
}
