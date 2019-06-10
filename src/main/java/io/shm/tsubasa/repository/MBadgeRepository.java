package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MBadge;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MBadge entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MBadgeRepository extends JpaRepository<MBadge, Long>, JpaSpecificationExecutor<MBadge> {

}
