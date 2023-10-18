package kitchenpos.domain.menugroup;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import kitchenpos.exception.InvalidNameException;

@Embeddable
public class MenuGroupName {

    @Column(name = "name", nullable = false)
    private String value;

    public MenuGroupName(final String value) {
        validate(value);
        this.value = value;
    }

    protected MenuGroupName() {
    }

    private void validate(final String value) {
        validateNotBlank(value);
        validateLength(value);
    }

    private void validateNotBlank(final String value) {
        if (value == null || value.isBlank()) {
            throw new InvalidNameException("메뉴 그룹 이름은 공백일 수 없습니다.");
        }
    }

    private void validateLength(final String value) {
        if (value.length() > 255) {
            throw new InvalidNameException("메뉴 그룹 이름은 255자를 초과할 수 없습니다.");
        }
    }

    public String getValue() {
        return value;
    }
}
