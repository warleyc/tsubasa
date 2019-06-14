package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTargetPlayableCardGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTargetPlayableCardGroup} and its DTO {@link MTargetPlayableCardGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {MPlayableCardMapper.class})
public interface MTargetPlayableCardGroupMapper extends EntityMapper<MTargetPlayableCardGroupDTO, MTargetPlayableCardGroup> {

    @Mapping(source = "id.id", target = "idId")
    MTargetPlayableCardGroupDTO toDto(MTargetPlayableCardGroup mTargetPlayableCardGroup);

    @Mapping(source = "idId", target = "id")
    MTargetPlayableCardGroup toEntity(MTargetPlayableCardGroupDTO mTargetPlayableCardGroupDTO);

    default MTargetPlayableCardGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTargetPlayableCardGroup mTargetPlayableCardGroup = new MTargetPlayableCardGroup();
        mTargetPlayableCardGroup.setId(id);
        return mTargetPlayableCardGroup;
    }
}
