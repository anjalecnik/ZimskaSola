package si.um.feri;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import si.um.feri.dao.StrankaRepository;
import si.um.feri.vao.Stranka;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class StrankaTest {

    @jakarta.inject.Inject
    StrankaRepository daoStranka;

    Stranka stranka;

    @Test
    @Transactional
    void CreateStranka_adds_new_entry() {
        Stranka stranka = new Stranka();
        stranka.setIme("Ime");
        stranka.setPriimek("Priimek");
        stranka.setEmail("ime.priimek@email.com");
        stranka.setNaslov("Testni naslov 20");

        daoStranka.persist(stranka);

        Stranka strankaBaza = daoStranka.findByImeAndPriimek("Ime", "Priimek");
        assertNotNull(strankaBaza);
        assertEquals(strankaBaza, stranka);
    }
}
