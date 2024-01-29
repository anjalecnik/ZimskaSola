package si.um.feri;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import si.um.feri.dao.NarociloRepository;
import si.um.feri.dao.PaketRepository;
import si.um.feri.dao.StrankaRepository;
import si.um.feri.vao.Narocilo;
import si.um.feri.vao.Paket;
import si.um.feri.vao.Stranka;

import java.time.LocalDateTime;
import java.util.logging.Logger;


@ApplicationScoped
public class ZimskaSolaApplication {
    private static final Logger log = Logger.getLogger(ZimskaSolaApplication.class.getName());

    @Inject
    PaketRepository paketRepository;

    @Inject
    StrankaRepository strankaRepository;

    @Inject
    NarociloRepository narociloRepository;

    void onStart(@Observes StartupEvent ev) {
        log.info("The application is starting...");
        populateTestDataIfNotPresent();
    }

    @Transactional
    public void populateTestDataIfNotPresent() {
        if (paketRepository.count() > 0) {
            log.info("populateTestData - skipped.");
            return;
        }
        log.info("populateTestData initiated.");

        Paket p1 = new Paket();
        p1.setNaziv("Mini paket");
        p1.setCena(18.99);
        p1.setDolzinaVezaveVDneh(30);
        paketRepository.persist(p1);

        Paket p2 = new Paket();
        p2.setNaziv("Mid paket");
        p2.setCena(25.99);
        p2.setDolzinaVezaveVDneh(356);
        paketRepository.persist(p2);

        Stranka stranka1 = new Stranka();
        stranka1.setIme("Test");
        stranka1.setPriimek("Test");
        stranka1.setEmail("test.test@gmail.com");
        stranka1.setNaslov("Koroška cesta 20");
        strankaRepository.persist(stranka1);

        Stranka stranka2 = new Stranka();
        stranka2.setIme("Stranka");
        stranka2.setPriimek("Stranka");
        stranka2.setEmail("stranka.stranka@gmail.com");
        stranka2.setNaslov("Koroška cesta 20");
        strankaRepository.persist(stranka2);

        Narocilo narocilo1 = new Narocilo();
        narocilo1.setZacetekVezave(LocalDateTime.now());
        narocilo1.setKonecVezave(LocalDateTime.now().plusDays(p1.getDolzinaVezaveVDneh()));
        narocilo1.setStranka(strankaRepository.findById(stranka1.getId()));
        narocilo1.setPaket(paketRepository.findById(p1.getId()));
        narociloRepository.persist(narocilo1);

        Narocilo narocilo2 = new Narocilo();
        narocilo2.setZacetekVezave(LocalDateTime.now());
        narocilo2.setKonecVezave(LocalDateTime.now().plusDays(p1.getDolzinaVezaveVDneh()));
        narocilo2.setStranka(strankaRepository.findById(stranka2.getId()));
        narocilo2.setPaket(paketRepository.findById(p2.getId()));
        narociloRepository.persist(narocilo2);
    }
}