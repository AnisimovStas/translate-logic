package feral.translate.logic.repositories;

import feral.translate.logic.domain.UserTranslate;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTranslatesRepository extends ListCrudRepository<UserTranslate, Long> {

    @Modifying
    @Query("""
        INSERT INTO translates.user_translates (user_id, translate_id, counter)
        VALUES (:userId, :translateId, 1)
        ON CONFLICT (user_id, translate_id)
        DO UPDATE SET counter = translates.user_translates.counter + 1
        """
    )
    void saveOrUpdate(Long userId, Long translateId);
}