package feral.translate.logic.repositories;

import feral.translate.logic.domain.Translate;
import feral.translate.logic.domain.TranslateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TranslateRepository extends ListCrudRepository<Translate, Long>, TranslateRepositoryInternal {
    Optional<Translate> findByOrigin(String text);

}

interface TranslateRepositoryInternal {
    Page<TranslateResponse> searchByUserId(Long userId, Pageable pageable);

}

@Component
@RequiredArgsConstructor
class TranslateRepositoryImpl implements TranslateRepositoryInternal {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final DataClassRowMapper<TranslateResponse> dataClassRowMapper = new DataClassRowMapper<>(TranslateResponse.class);

    @Override
    public Page<TranslateResponse> searchByUserId(Long userId, Pageable pageable) {
        String sql = """
                SELECT *
                FROM translates.translates_view t
                JOIN translates.user_translates ut ON t.translate_id = ut.translate_id
                WHERE ut.user_id = :userId
                LIMIT :limit OFFSET :offset
            """;

        MapSqlParameterSource params = new MapSqlParameterSource()
            .addValue("userId", userId)
            .addValue("limit", pageable.getPageSize())
            .addValue("offset", pageable.getOffset());

        List<TranslateResponse> results = jdbcTemplate.query(
            sql, params, dataClassRowMapper
        );

        // Подсчет общего количества записей для корректной пагинации
        String countSql = """
                SELECT COUNT(*)
                FROM translates.user_translates ut
                WHERE ut.user_id = :userId
            """;
        int total = jdbcTemplate.queryForObject(countSql, params, Integer.class);

        return new PageImpl<>(results, pageable, total);
    }

}