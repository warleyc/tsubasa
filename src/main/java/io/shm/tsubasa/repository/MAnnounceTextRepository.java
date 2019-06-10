package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MAnnounceText;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MAnnounceText entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MAnnounceTextRepository extends JpaRepository<MAnnounceText, Long>, JpaSpecificationExecutor<MAnnounceText> {

}
