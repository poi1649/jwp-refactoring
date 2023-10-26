package kitchenpos.tablegroup.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import kitchenpos.ordertable.domain.OrderTable;

@Entity
public class TableGroup {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @OneToMany
    @JoinColumn(name = "table_group_id")
    private List<OrderTable> orderTables;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    protected TableGroup() {
    }

    public static TableGroup of(final List<OrderTable> orderTables, final LocalDateTime createdDate) {
        validateCanGroup(orderTables);
        return new TableGroup(null, orderTables, createdDate);
    }

    public TableGroup(final Long id, final List<OrderTable> orderTables, final LocalDateTime createdDate) {
        this.id = id;
        this.orderTables = orderTables;
        orderTables.forEach(orderTable -> orderTable.group(id));
        this.createdDate = createdDate;
    }

    private static void validateCanGroup(final List<OrderTable> orderTables) {
        if (orderTables.size() <= 1) {
            throw new IllegalArgumentException("그룹화할 테이블은 2개 이상이어야 합니다.");
        }
        if (orderTables.stream()
                       .anyMatch(OrderTable::isNotEmpty)) {
            throw new IllegalArgumentException("빈 테이블만 그룹화할 수 있습니다.");
        }
        if (orderTables.stream()
                       .anyMatch(OrderTable::hasTableGroup)) {
            throw new IllegalArgumentException("이미 그룹화된 테이블입니다.");
        }
    }

    public void unGroup() {
        orderTables.forEach(OrderTable::unGroup);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public List<OrderTable> getOrderTables() {
        return orderTables;
    }
}
