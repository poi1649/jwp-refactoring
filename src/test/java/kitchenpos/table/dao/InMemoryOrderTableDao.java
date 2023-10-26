package kitchenpos.table.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import kitchenpos.ordertable.domain.OrderTable;
import kitchenpos.ordertable.repository.OrderTableDao;

public class InMemoryOrderTableDao implements OrderTableDao {

    private final List<OrderTable> orderTables = new ArrayList<>();

    @Override
    public OrderTable save(final OrderTable entity) {
        final var id = (long) (orderTables.size() + 1);
        final var saved = new OrderTable(id, entity.getTableGroupId(), entity.getNumberOfGuests(), entity.isEmpty());
        orderTables.add(saved);
        return saved;
    }

    @Override
    public Optional<OrderTable> findById(final Long id) {
        return orderTables.stream()
                          .filter(orderTable -> orderTable.getId().equals(id))
                          .findAny();
    }

    @Override
    public List<OrderTable> findAll() {
        return orderTables;
    }

    @Override
    public List<OrderTable> findAllByIdIn(final List<Long> ids) {
        return orderTables.stream()
                          .filter(orderTable -> ids.contains(orderTable.getId()))
                          .collect(Collectors.toList());
    }
}
