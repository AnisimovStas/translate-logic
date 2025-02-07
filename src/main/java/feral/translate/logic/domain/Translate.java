package feral.translate.logic.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table(schema = "translates", name = "translates")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Translate {
    @Id
    Long id;
    String origin;
    String translate;
}
