package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MUserPolicyUpdateDateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MUserPolicyUpdateDate} and its DTO {@link MUserPolicyUpdateDateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MUserPolicyUpdateDateMapper extends EntityMapper<MUserPolicyUpdateDateDTO, MUserPolicyUpdateDate> {



    default MUserPolicyUpdateDate fromId(Long id) {
        if (id == null) {
            return null;
        }
        MUserPolicyUpdateDate mUserPolicyUpdateDate = new MUserPolicyUpdateDate();
        mUserPolicyUpdateDate.setId(id);
        return mUserPolicyUpdateDate;
    }
}
