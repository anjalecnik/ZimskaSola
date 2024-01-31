package si.um.feri.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import si.um.feri.dao.NarociloRepository;
import si.um.feri.dao.PaketRepository;
import si.um.feri.dao.StrankaRepository;
import si.um.feri.dto.NarociloDTO;
import si.um.feri.dto.post.PostNarociloDTO;
import si.um.feri.vao.Narocilo;
import si.um.feri.vao.Paket;
import si.um.feri.vao.Stranka;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/narocilo")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NarociloController {
    private static final Logger log = Logger.getLogger(NarociloController.class.getName());

    @Inject
    NarociloRepository narociloRepository;

    @Inject
    StrankaRepository strankaRepository;

    @Inject
    PaketRepository paketRepository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<NarociloDTO> getAllNarocila() {
        return narociloRepository.listAll()
                .stream()
                .map(Narocilo::toDto)
                .collect(Collectors.toList());

    }

    @GET
    @Path("/pridobi-po-id")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Narocilo> getNarocilaByStranka(@QueryParam("strankaId") Long strankaId) {
        return narociloRepository.find("stranka.id", strankaId)
                .stream()
                .collect(Collectors.toList());
    }

    @POST
    @Path("/dodaj-narocilo")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Narocilo createNarocilo(PostNarociloDTO postNarociloDTO) {
        Stranka stranka = strankaRepository.findById(postNarociloDTO.id_stranke());
        Paket paket = paketRepository.findById(postNarociloDTO.id_paketa());

        Narocilo narocilo = new Narocilo();
        narocilo.setZacetekVezave(postNarociloDTO.zacetekVezave());
        narocilo.setKonecVezave(postNarociloDTO.zacetekVezave().plusDays(paket.getDolzinaVezaveVDneh()));

        narocilo.setStranka(stranka);
        narocilo.setPaket(paket);

        narociloRepository.persist(narocilo);

        return narocilo;
    }
}
