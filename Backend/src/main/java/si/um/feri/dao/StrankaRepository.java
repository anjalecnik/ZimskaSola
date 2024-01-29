package si.um.feri.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import si.um.feri.vao.Stranka;

@ApplicationScoped
public class StrankaRepository implements PanacheRepository<Stranka> {
}