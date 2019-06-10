package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MActionSkillCutinDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MActionSkillCutin} and its DTO {@link MActionSkillCutinDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MActionSkillCutinMapper extends EntityMapper<MActionSkillCutinDTO, MActionSkillCutin> {



    default MActionSkillCutin fromId(Long id) {
        if (id == null) {
            return null;
        }
        MActionSkillCutin mActionSkillCutin = new MActionSkillCutin();
        mActionSkillCutin.setId(id);
        return mActionSkillCutin;
    }
}
