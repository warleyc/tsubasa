package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MUserRankDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MUserRank} and its DTO {@link MUserRankDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MUserRankMapper extends EntityMapper<MUserRankDTO, MUserRank> {



    default MUserRank fromId(Long id) {
        if (id == null) {
            return null;
        }
        MUserRank mUserRank = new MUserRank();
        mUserRank.setId(id);
        return mUserRank;
    }
}
