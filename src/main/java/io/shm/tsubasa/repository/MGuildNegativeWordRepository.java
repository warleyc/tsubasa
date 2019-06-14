package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGuildNegativeWord;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGuildNegativeWord entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGuildNegativeWordRepository extends JpaRepository<MGuildNegativeWord, Long>, JpaSpecificationExecutor<MGuildNegativeWord> {

}
