package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MExtraNews;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MExtraNews entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MExtraNewsRepository extends JpaRepository<MExtraNews, Long>, JpaSpecificationExecutor<MExtraNews> {

}
