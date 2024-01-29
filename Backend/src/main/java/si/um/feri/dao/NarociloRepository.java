package si.um.feri.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import si.um.feri.vao.Narocilo;

@ApplicationScoped
public class NarociloRepository implements PanacheRepository<Narocilo> {
}