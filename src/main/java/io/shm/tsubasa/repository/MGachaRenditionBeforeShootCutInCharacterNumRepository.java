package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGachaRenditionBeforeShootCutInCharacterNum;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGachaRenditionBeforeShootCutInCharacterNum entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGachaRenditionBeforeShootCutInCharacterNumRepository extends JpaRepository<MGachaRenditionBeforeShootCutInCharacterNum, Long>, JpaSpecificationExecutor<MGachaRenditionBeforeShootCutInCharacterNum> {

}
