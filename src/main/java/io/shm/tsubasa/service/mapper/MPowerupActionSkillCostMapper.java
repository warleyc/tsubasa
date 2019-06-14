package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MPowerupActionSkillCostDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MPowerupActionSkillCost} and its DTO {@link MPowerupActionSkillCostDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MPowerupActionSkillCostMapper extends EntityMapper<MPowerupActionSkillCostDTO, MPowerupActionSkillCost> {



    default MPowerupActionSkillCost fromId(Long id) {
        if (id == null) {
            return null;
        }
        MPowerupActionSkillCost mPowerupActionSkillCost = new MPowerupActionSkillCost();
        mPowerupActionSkillCost.setId(id);
        return mPowerupActionSkillCost;
    }
}
