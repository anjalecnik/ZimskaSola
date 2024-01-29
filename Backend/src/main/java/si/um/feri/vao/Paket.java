package si.um.feri.vao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.um.feri.dto.PaketDTO;

import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor
public class Paket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String naziv;
    protected double cena;
    protected LocalDateTime ustvarjeno= LocalDateTime.now();

    public PaketDTO toDto() {
        return new PaketDTO(id, naziv, cena, ustvarjeno);
    }
}
