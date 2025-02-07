package feral.translate.logic.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(schema = "translates", name = "user_translates")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserTranslate {
    Long userId;
    Long translateId;
    Long counter;
}
