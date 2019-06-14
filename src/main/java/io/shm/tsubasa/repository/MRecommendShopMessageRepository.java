package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MRecommendShopMessage;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MRecommendShopMessage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MRecommendShopMessageRepository extends JpaRepository<MRecommendShopMessage, Long>, JpaSpecificationExecutor<MRecommendShopMessage> {

}
