package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MPassiveEffectRangeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MPassiveEffectRange} and its DTO {@link MPassiveEffectRangeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MPassiveEffectRangeMapper extends EntityMapper<MPassiveEffectRangeDTO, MPassiveEffectRange> {


    @Mapping(target = "mFormations", ignore = true)
    @Mapping(target = "mMatchOptions", ignore = true)
    @Mapping(target = "mTeamEffectBases", ignore = true)
    MPassiveEffectRange toEntity(MPassiveEffectRangeDTO mPassiveEffectRangeDTO);

    default MPassiveEffectRange fromId(Long id) {
        if (id == null) {
            return null;
        }
        MPassiveEffectRange mPassiveEffectRange = new MPassiveEffectRange();
        mPassiveEffectRange.setId(id);
        return mPassiveEffectRange;
    }
}
