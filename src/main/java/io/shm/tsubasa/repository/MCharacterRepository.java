package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCharacter;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCharacter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCharacterRepository extends JpaRepository<MCharacter, Long>, JpaSpecificationExecutor<MCharacter> {

}
