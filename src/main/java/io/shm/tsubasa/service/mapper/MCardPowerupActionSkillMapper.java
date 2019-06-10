package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCardPowerupActionSkillDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCardPowerupActionSkill} and its DTO {@link MCardPowerupActionSkillDTO}.
 */
@Mapper(componentModel = "spring", uses = {MCardThumbnailAssetsMapper.class})
public interface MCardPowerupActionSkillMapper extends EntityMapper<MCardPowerupActionSkillDTO, MCardPowerupActionSkill> {

    @Mapping(source = "id.id", target = "idId")
    MCardPowerupActionSkillDTO toDto(MCardPowerupActionSkill mCardPowerupActionSkill);

    @Mapping(source = "idId", target = "id")
    MCardPowerupActionSkill toEntity(MCardPowerupActionSkillDTO mCardPowerupActionSkillDTO);

    default MCardPowerupActionSkill fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCardPowerupActionSkill mCardPowerupActionSkill = new MCardPowerupActionSkill();
        mCardPowerupActionSkill.setId(id);
        return mCardPowerupActionSkill;
    }
}
