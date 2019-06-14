package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTriggerEffectRangeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTriggerEffectRange} and its DTO {@link MTriggerEffectRangeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTriggerEffectRangeMapper extends EntityMapper<MTriggerEffectRangeDTO, MTriggerEffectRange> {


    @Mapping(target = "mTriggerEffectBases", ignore = true)
    MTriggerEffectRange toEntity(MTriggerEffectRangeDTO mTriggerEffectRangeDTO);

    default MTriggerEffectRange fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTriggerEffectRange mTriggerEffectRange = new MTriggerEffectRange();
        mTriggerEffectRange.setId(id);
        return mTriggerEffectRange;
    }
}
