package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGachaRenditionTradeSign;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGachaRenditionTradeSign entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGachaRenditionTradeSignRepository extends JpaRepository<MGachaRenditionTradeSign, Long>, JpaSpecificationExecutor<MGachaRenditionTradeSign> {

}
