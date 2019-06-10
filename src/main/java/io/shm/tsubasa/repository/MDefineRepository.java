package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MDefine;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MDefine entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MDefineRepository extends JpaRepository<MDefine, Long>, JpaSpecificationExecutor<MDefine> {

}
