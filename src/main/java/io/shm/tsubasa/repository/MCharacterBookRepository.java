package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCharacterBook;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCharacterBook entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCharacterBookRepository extends JpaRepository<MCharacterBook, Long>, JpaSpecificationExecutor<MCharacterBook> {

}
