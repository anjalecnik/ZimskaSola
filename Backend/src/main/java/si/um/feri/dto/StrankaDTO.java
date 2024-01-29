package si.um.feri.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public record StrankaDTO(
        Long id,
        String ime,
        String priimek,
        String email,
        String naslov
) {
}
