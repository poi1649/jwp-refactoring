package kitchenpos.dao.fakedao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import kitchenpos.dao.MenuGroupDao;
import kitchenpos.domain.MenuGroup;

public class InMemoryMenuGroupDao implements MenuGroupDao {

    private final List<MenuGroup> menuGroups = new ArrayList<>();

    @Override
    public MenuGroup save(final MenuGroup entity) {
        entity.setId((long) (menuGroups.size() + 1));
        menuGroups.add(entity);
        return entity;
    }

    @Override
    public Optional<MenuGroup> findById(final Long id) {
        return menuGroups.stream()
                         .filter(menuGroup -> menuGroup.getId().equals(id))
                         .findAny();
    }

    @Override
    public List<MenuGroup> findAll() {
        return menuGroups;
    }

    @Override
    public boolean existsById(final Long id) {
        return menuGroups.stream()
                         .anyMatch(menuGroup -> menuGroup.getId().equals(id));
    }
}
