package si.um.feri.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public record NarociloDTO (
        Long id,
        LocalDateTime datumNarocila,
        LocalDateTime zacetekVezave,
        LocalDateTime konecVezave
) {
    public static final DateTimeFormatter JSON_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
}
