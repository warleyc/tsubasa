package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MGuildChatDefaultStamp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MGuildChatDefaultStamp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MGuildChatDefaultStampRepository extends JpaRepository<MGuildChatDefaultStamp, Long>, JpaSpecificationExecutor<MGuildChatDefaultStamp> {

}
