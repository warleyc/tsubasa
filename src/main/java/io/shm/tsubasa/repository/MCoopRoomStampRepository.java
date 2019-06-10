package io.shm.tsubasa.repository;

import io.shm.tsubasa.domain.MCoopRoomStamp;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the MCoopRoomStamp entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MCoopRoomStampRepository extends JpaRepository<MCoopRoomStamp, Long>, JpaSpecificationExecutor<MCoopRoomStamp> {

}
