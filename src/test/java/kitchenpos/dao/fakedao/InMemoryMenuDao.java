package kitchenpos.dao.fakedao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import kitchenpos.dao.MenuDao;
import kitchenpos.domain.menu.Menu;

public class InMemoryMenuDao implements MenuDao {

    private final List<Menu> menus = new ArrayList<>();

    @Override
    public Menu save(final Menu entity) {
        final var id = (long) (menus.size() + 1);
        final var saved = new Menu(id, entity.getName(), entity.getPrice(), entity.getMenuProducts(), entity.getMenuGroup());
        menus.add(saved);
        return saved;
    }

    @Override
    public Optional<Menu> findById(final Long id) {
        return menus.stream()
                    .filter(menu -> menu.getId().equals(id))
                    .findAny();
    }

    @Override
    public List<Menu> findAll() {
        return menus;
    }
}
