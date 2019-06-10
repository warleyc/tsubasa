package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MEmblemSet;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MEmblemSet entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MEmblemSetRepository extends JpaRepository<MEmblemSet, Long>, JpaSpecificationExecutor<MEmblemSet> {

}
