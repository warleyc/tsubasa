package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMatchOptionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMatchOption} and its DTO {@link MMatchOptionDTO}.
 */
@Mapper(componentModel = "spring", uses = {MPassiveEffectRangeMapper.class})
public interface MMatchOptionMapper extends EntityMapper<MMatchOptionDTO, MMatchOption> {

    @Mapping(source = "id.id", target = "idId")
    MMatchOptionDTO toDto(MMatchOption mMatchOption);

    @Mapping(source = "idId", target = "id")
    @Mapping(target = "mLeagueRegulations", ignore = true)
    @Mapping(target = "mPvpRegulations", ignore = true)
    MMatchOption toEntity(MMatchOptionDTO mMatchOptionDTO);

    default MMatchOption fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMatchOption mMatchOption = new MMatchOption();
        mMatchOption.setId(id);
        return mMatchOption;
    }
}
