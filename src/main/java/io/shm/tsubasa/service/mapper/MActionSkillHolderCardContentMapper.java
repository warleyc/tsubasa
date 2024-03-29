package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardContentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MActionSkillHolderCardContent} and its DTO {@link MActionSkillHolderCardContentDTO}.
 */
@Mapper(componentModel = "spring", uses = {MCharacterMapper.class})
public interface MActionSkillHolderCardContentMapper extends EntityMapper<MActionSkillHolderCardContentDTO, MActionSkillHolderCardContent> {

    @Mapping(source = "mcharacter.id", target = "mcharacterId")
    MActionSkillHolderCardContentDTO toDto(MActionSkillHolderCardContent mActionSkillHolderCardContent);

    @Mapping(source = "mcharacterId", target = "mcharacter")
    MActionSkillHolderCardContent toEntity(MActionSkillHolderCardContentDTO mActionSkillHolderCardContentDTO);

    default MActionSkillHolderCardContent fromId(Long id) {
        if (id == null) {
            return null;
        }
        MActionSkillHolderCardContent mActionSkillHolderCardContent = new MActionSkillHolderCardContent();
        mActionSkillHolderCardContent.setId(id);
        return mActionSkillHolderCardContent;
    }
}
