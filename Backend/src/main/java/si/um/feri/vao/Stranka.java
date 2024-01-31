package si.um.feri.vao;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import si.um.feri.dto.StrankaDTO;

@Entity
@Data @NoArgsConstructor
public class Stranka {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected String ime;
    protected String priimek;
    protected String naslov;
    protected String email;
    protected String geslo;

    public StrankaDTO toDto() {
        return new StrankaDTO(ime, priimek, naslov, email, geslo);
    }
}
