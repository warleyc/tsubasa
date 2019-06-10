package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCutSeqGroup;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCutSeqGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCutSeqGroupRepository extends JpaRepository<MCutSeqGroup, Long>, JpaSpecificationExecutor<MCutSeqGroup> {

}
