package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MModelCard;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MModelCard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MModelCardRepository extends JpaRepository<MModelCard, Long>, JpaSpecificationExecutor<MModelCard> {

}
