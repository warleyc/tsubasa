package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCardRarity;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCardRarity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCardRarityRepository extends JpaRepository<MCardRarity, Long>, JpaSpecificationExecutor<MCardRarity> {

}
