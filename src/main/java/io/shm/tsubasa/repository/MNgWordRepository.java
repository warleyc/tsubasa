package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MNgWord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MNgWord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MNgWordRepository extends JpaRepository<MNgWord, Long>, JpaSpecificationExecutor<MNgWord> {

}
