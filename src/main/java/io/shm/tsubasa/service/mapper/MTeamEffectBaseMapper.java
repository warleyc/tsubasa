package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTeamEffectBaseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTeamEffectBase} and its DTO {@link MTeamEffectBaseDTO}.
 */
@Mapper(componentModel = "spring", uses = {MPassiveEffectRangeMapper.class})
public interface MTeamEffectBaseMapper extends EntityMapper<MTeamEffectBaseDTO, MTeamEffectBase> {

    @Mapping(source = "mpassiveeffectrange.id", target = "mpassiveeffectrangeId")
    MTeamEffectBaseDTO toDto(MTeamEffectBase mTeamEffectBase);

    @Mapping(source = "mpassiveeffectrangeId", target = "mpassiveeffectrange")
    MTeamEffectBase toEntity(MTeamEffectBaseDTO mTeamEffectBaseDTO);

    default MTeamEffectBase fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTeamEffectBase mTeamEffectBase = new MTeamEffectBase();
        mTeamEffectBase.setId(id);
        return mTeamEffectBase;
    }
}
