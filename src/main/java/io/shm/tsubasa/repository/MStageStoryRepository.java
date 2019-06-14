package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MStageStory;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MStageStory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MStageStoryRepository extends JpaRepository<MStageStory, Long>, JpaSpecificationExecutor<MStageStory> {

}
