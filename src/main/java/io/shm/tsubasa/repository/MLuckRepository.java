package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MLuck;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MLuck entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MLuckRepository extends JpaRepository<MLuck, Long>, JpaSpecificationExecutor<MLuck> {

}
