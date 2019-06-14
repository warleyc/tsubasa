package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MPvpGradeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MPvpGrade} and its DTO {@link MPvpGradeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MPvpGradeMapper extends EntityMapper<MPvpGradeDTO, MPvpGrade> {



    default MPvpGrade fromId(Long id) {
        if (id == null) {
            return null;
        }
        MPvpGrade mPvpGrade = new MPvpGrade();
        mPvpGrade.setId(id);
        return mPvpGrade;
    }
}
