package si.um.feri.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.dao.PaketRepository;
import si.um.feri.dto.PaketDTO;
import si.um.feri.vao.Paket;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/paketi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaketController {
    private static final Logger log = Logger.getLogger(PaketController.class.getName());

    @Inject
    PaketRepository paketRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PaketDTO> getAllPaketi() {
        return paketRepository.listAll()
                .stream()
                .map(Paket::toDto)
                .collect(Collectors.toList());
    }
}
