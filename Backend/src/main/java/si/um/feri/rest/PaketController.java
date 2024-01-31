package si.um.feri.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.dao.PaketRepository;
import si.um.feri.dto.PaketDTO;
import si.um.feri.dto.post.PostPaketDTO;
import si.um.feri.vao.Paket;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/paket")
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

    @POST
    @Path("/dodaj-paket")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Paket createPaket(PostPaketDTO postPaketDTO) {
        Paket paket = new Paket();
        paket.setNaziv(postPaketDTO.naziv());
        paket.setCena(postPaketDTO.cena());
        paket.setDolzinaVezaveVDneh(postPaketDTO.dolzinaVezaveVDneh());

        paketRepository.persist(paket);

        return paket;
    }
}
