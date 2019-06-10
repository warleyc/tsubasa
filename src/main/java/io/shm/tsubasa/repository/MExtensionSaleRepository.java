package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MExtensionSale;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MExtensionSale entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MExtensionSaleRepository extends JpaRepository<MExtensionSale, Long>, JpaSpecificationExecutor<MExtensionSale> {

}
