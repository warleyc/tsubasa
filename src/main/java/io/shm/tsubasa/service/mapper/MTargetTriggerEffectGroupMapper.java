package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTargetTriggerEffectGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTargetTriggerEffectGroup} and its DTO {@link MTargetTriggerEffectGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {MTriggerEffectBaseMapper.class})
public interface MTargetTriggerEffectGroupMapper extends EntityMapper<MTargetTriggerEffectGroupDTO, MTargetTriggerEffectGroup> {

    @Mapping(source = "mtriggereffectbase.id", target = "mtriggereffectbaseId")
    MTargetTriggerEffectGroupDTO toDto(MTargetTriggerEffectGroup mTargetTriggerEffectGroup);

    @Mapping(source = "mtriggereffectbaseId", target = "mtriggereffectbase")
    MTargetTriggerEffectGroup toEntity(MTargetTriggerEffectGroupDTO mTargetTriggerEffectGroupDTO);

    default MTargetTriggerEffectGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTargetTriggerEffectGroup mTargetTriggerEffectGroup = new MTargetTriggerEffectGroup();
        mTargetTriggerEffectGroup.setId(id);
        return mTargetTriggerEffectGroup;
    }
}
