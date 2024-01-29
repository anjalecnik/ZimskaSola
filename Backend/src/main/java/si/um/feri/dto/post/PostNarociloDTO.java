package si.um.feri.dto.post;

import java.time.LocalDateTime;

public record PostNarociloDTO(
        Long id_paketa,
        Long id_stranke,
        LocalDateTime datumNarocila,
        LocalDateTime zacetekVezave,
        LocalDateTime konecVezave
) {
}
