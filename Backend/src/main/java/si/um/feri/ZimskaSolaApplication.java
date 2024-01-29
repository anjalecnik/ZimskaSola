package si.um.feri;

import io.quarkus.runtime.StartupEvent;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import si.um.feri.dao.PaketRepository;
import si.um.feri.vao.Paket;

import java.util.logging.Logger;


@ApplicationScoped
public class ZimskaSolaApplication {
    private static final Logger log = Logger.getLogger(ZimskaSolaApplication.class.getName());

    @Inject
    PaketRepository paketRepository;

    void onStart(@Observes StartupEvent ev) {
        log.info("The application is starting...");
        populateTestDataIfNotPresent();
    }

    @Transactional
    void populateTestDataIfNotPresent() {
        if (paketRepository.count() > 0) {
            log.info("populateTestData - skipped.");
            return;
        }
        log.info("populateTestData initiated.");

        Paket p1 = new Paket();
        p1.setNaziv("Mini paket");
        p1.setCena(18.99);
        paketRepository.persist(p1);

        Paket p2 = new Paket();
        p2.setNaziv("Mid paket");
        p2.setCena(25.99);
        paketRepository.persist(p2);
    }
}