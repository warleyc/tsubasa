package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MNationality;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MNationality entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MNationalityRepository extends JpaRepository<MNationality, Long>, JpaSpecificationExecutor<MNationality> {

}
