package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MEmblemParts;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MEmblemParts entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MEmblemPartsRepository extends JpaRepository<MEmblemParts, Long>, JpaSpecificationExecutor<MEmblemParts> {

}
