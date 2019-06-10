package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MActionRarity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MActionRarity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MActionRarityRepository extends JpaRepository<MActionRarity, Long>, JpaSpecificationExecutor<MActionRarity> {

}
