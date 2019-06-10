package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MActionSkillHolderCardCtDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MActionSkillHolderCardCt} and its DTO {@link MActionSkillHolderCardCtDTO}.
 */
@Mapper(componentModel = "spring", uses = {MCharacterMapper.class})
public interface MActionSkillHolderCardCtMapper extends EntityMapper<MActionSkillHolderCardCtDTO, MActionSkillHolderCardCt> {

    @Mapping(source = "id.id", target = "idId")
    MActionSkillHolderCardCtDTO toDto(MActionSkillHolderCardCt mActionSkillHolderCardCt);

    @Mapping(source = "idId", target = "id")
    MActionSkillHolderCardCt toEntity(MActionSkillHolderCardCtDTO mActionSkillHolderCardCtDTO);

    default MActionSkillHolderCardCt fromId(Long id) {
        if (id == null) {
            return null;
        }
        MActionSkillHolderCardCt mActionSkillHolderCardCt = new MActionSkillHolderCardCt();
        mActionSkillHolderCardCt.setId(id);
        return mActionSkillHolderCardCt;
    }
}
