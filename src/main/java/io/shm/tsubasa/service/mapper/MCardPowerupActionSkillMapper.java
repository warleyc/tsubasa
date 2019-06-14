package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCardPowerupActionSkillDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCardPowerupActionSkill} and its DTO {@link MCardPowerupActionSkillDTO}.
 */
@Mapper(componentModel = "spring", uses = {MCardThumbnailAssetsMapper.class})
public interface MCardPowerupActionSkillMapper extends EntityMapper<MCardPowerupActionSkillDTO, MCardPowerupActionSkill> {

    @Mapping(source = "mcardthumbnailassets.id", target = "mcardthumbnailassetsId")
    MCardPowerupActionSkillDTO toDto(MCardPowerupActionSkill mCardPowerupActionSkill);

    @Mapping(source = "mcardthumbnailassetsId", target = "mcardthumbnailassets")
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
