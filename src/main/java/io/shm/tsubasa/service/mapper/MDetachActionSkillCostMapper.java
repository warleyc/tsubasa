package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDetachActionSkillCostDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDetachActionSkillCost} and its DTO {@link MDetachActionSkillCostDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MDetachActionSkillCostMapper extends EntityMapper<MDetachActionSkillCostDTO, MDetachActionSkillCost> {



    default MDetachActionSkillCost fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDetachActionSkillCost mDetachActionSkillCost = new MDetachActionSkillCost();
        mDetachActionSkillCost.setId(id);
        return mDetachActionSkillCost;
    }
}
