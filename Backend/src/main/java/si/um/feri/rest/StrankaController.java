package si.um.feri.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import si.um.feri.dao.StrankaRepository;
import si.um.feri.dto.StrankaDTO;
import si.um.feri.dto.post.GetStrankaDTO;
import si.um.feri.vao.Stranka;

import java.util.List;
import java.util.Objects;
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

    @POST
    @Path("/prijava")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response loginStranka(GetStrankaDTO getStrankaDTO) {
        Stranka strankaBaza = strankaRepository.findByEmail(getStrankaDTO.email());
        if (Objects.equals(strankaBaza.getGeslo(), getStrankaDTO.geslo())) {
            return Response.ok(strankaBaza.getId())
                    .header("Access-Control-Allow-Origin", "http://localhost:3000")
                    .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                    .header("Access-Control-Allow-Headers", "Content-Type")
                    .build();
        }
        return Response.status(Response.Status.UNAUTHORIZED)
                .header("Access-Control-Allow-Origin", "http://localhost:3000")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .build();
    }

    @POST
    @Path("/registracija")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response registerStranka(StrankaDTO strankaDTO) {
        Stranka stranka = new Stranka();
        stranka.setIme(strankaDTO.ime());
        stranka.setPriimek(strankaDTO.priimek());
        stranka.setNaslov(strankaDTO.naslov());
        stranka.setEmail(strankaDTO.email());
        stranka.setGeslo(strankaDTO.geslo());

        strankaRepository.persist(stranka);

        Stranka strankaBaza = strankaRepository.findByEmail(strankaDTO.email());
        if (Objects.equals(strankaBaza.getGeslo(), strankaDTO.geslo())) {
            return Response.ok(strankaBaza.getId())
                    .header("Access-Control-Allow-Origin", "*")
                    .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                    .header("Access-Control-Allow-Headers", "Content-Type")
                    .build();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, OPTIONS")
                .header("Access-Control-Allow-Headers", "Content-Type")
                .build();
    }

}
