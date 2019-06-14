package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MInheritActionSkillCostDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MInheritActionSkillCost} and its DTO {@link MInheritActionSkillCostDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MInheritActionSkillCostMapper extends EntityMapper<MInheritActionSkillCostDTO, MInheritActionSkillCost> {



    default MInheritActionSkillCost fromId(Long id) {
        if (id == null) {
            return null;
        }
        MInheritActionSkillCost mInheritActionSkillCost = new MInheritActionSkillCost();
        mInheritActionSkillCost.setId(id);
        return mInheritActionSkillCost;
    }
}
