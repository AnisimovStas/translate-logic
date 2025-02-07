package feral.translate.logic.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TranslateResponse {
    Long translateId;
    String origin;
    String translate;
    Long counter;
}
