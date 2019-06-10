package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MArousalMaterial;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MArousalMaterial entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MArousalMaterialRepository extends JpaRepository<MArousalMaterial, Long>, JpaSpecificationExecutor<MArousalMaterial> {

}
