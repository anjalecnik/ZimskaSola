package si.um.feri.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.dao.StrankaRepository;
import si.um.feri.dto.StrankaDTO;
import si.um.feri.vao.Stranka;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/stranka")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class StrankaController {
    private static final Logger log = Logger.getLogger(StrankaController.class.getName());

    @Inject
    StrankaRepository strankaRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<StrankaDTO> getAllStranke() {
        return strankaRepository.listAll()
                .stream()
                .map(Stranka::toDto)
                .collect(Collectors.toList());
    }
}
