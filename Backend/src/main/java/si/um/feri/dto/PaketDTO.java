package si.um.feri.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public record PaketDTO (
        Long id,
        String naziv,
        double cena,
        LocalDateTime ustvarjeno
) {
    public static final DateTimeFormatter JSON_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
}