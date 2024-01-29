package si.um.feri.vao;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.um.feri.dto.NarociloDTO;

import java.time.LocalDateTime;

@Entity
@Data @NoArgsConstructor
public class Narocilo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected LocalDateTime zacetekVezave;
    protected LocalDateTime konecVezave;
    protected LocalDateTime datumNarocila = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "stranka_id")
    protected Stranka stranka;

    @ManyToOne
    @JoinColumn(name = "paket_id")
    protected Paket paket;

    public NarociloDTO toDto() {
        return new NarociloDTO(id, datumNarocila, zacetekVezave, konecVezave);
    }
}
