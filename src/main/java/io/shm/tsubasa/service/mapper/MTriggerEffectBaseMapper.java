package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTriggerEffectBaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTriggerEffectBase} and its DTO {@link MTriggerEffectBaseDTO}.
 */
@Mapper(componentModel = "spring", uses = {MTriggerEffectRangeMapper.class})
public interface MTriggerEffectBaseMapper extends EntityMapper<MTriggerEffectBaseDTO, MTriggerEffectBase> {

    @Mapping(source = "mtriggereffectrange.id", target = "mtriggereffectrangeId")
    MTriggerEffectBaseDTO toDto(MTriggerEffectBase mTriggerEffectBase);

    @Mapping(source = "mtriggereffectrangeId", target = "mtriggereffectrange")
    @Mapping(target = "mTargetTriggerEffectGroups", ignore = true)
    MTriggerEffectBase toEntity(MTriggerEffectBaseDTO mTriggerEffectBaseDTO);

    default MTriggerEffectBase fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTriggerEffectBase mTriggerEffectBase = new MTriggerEffectBase();
        mTriggerEffectBase.setId(id);
        return mTriggerEffectBase;
    }
}
