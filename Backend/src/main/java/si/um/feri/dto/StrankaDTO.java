package si.um.feri.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public record StrankaDTO(
        String ime,
        String priimek,
        String naslov,
        String email,
        String geslo
) {
}
