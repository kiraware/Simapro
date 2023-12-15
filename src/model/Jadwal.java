package model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Jadwal {
    private final UUID uuidTugas;
    private LocalDate tanggalMulai;
    private LocalDate tanggalSelesai;

    public Jadwal() {
        this.uuidTugas = UUID.randomUUID();
        this.tanggalMulai = null;
        this.tanggalSelesai = null;
    }

    public Jadwal(UUID uuidTugas, LocalDate tanggalMulai, LocalDate tanggalSelesai) {
        this.uuidTugas = uuidTugas;
        this.tanggalMulai = tanggalMulai;
        this.tanggalSelesai = tanggalSelesai;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.uuidTugas);
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
        if (!Objects.equals(this.uuidTugas, other.uuidTugas)) {
            return false;
        }
        if (!Objects.equals(this.tanggalMulai, other.tanggalMulai)) {
            return false;
        }
        return Objects.equals(this.tanggalSelesai, other.tanggalSelesai);
    }

    @Override
    public String toString() {
        return "Jadwal{" + "uuidTugas=" + uuidTugas + ", tanggalMulai=" + tanggalMulai + ", tanggalSelesai=" + tanggalSelesai + '}';
    }

    public UUID getUuidTugas() {
        return uuidTugas;
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
}
