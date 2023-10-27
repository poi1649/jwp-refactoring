package repository;

import domain.MenuGroup;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface MenuGroupDao extends Repository<MenuGroup, Long> {

    MenuGroup save(MenuGroup entity);

    Optional<MenuGroup> findById(Long id);

    List<MenuGroup> findAll();

    boolean existsById(Long id);
}
