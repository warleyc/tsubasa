package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGuildEffect;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGuildEffect entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGuildEffectRepository extends JpaRepository<MGuildEffect, Long>, JpaSpecificationExecutor<MGuildEffect> {

}
