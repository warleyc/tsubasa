package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMatchResultCutinDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMatchResultCutin} and its DTO {@link MMatchResultCutinDTO}.
 */
@Mapper(componentModel = "spring", uses = {MCharacterMapper.class})
public interface MMatchResultCutinMapper extends EntityMapper<MMatchResultCutinDTO, MMatchResultCutin> {

    @Mapping(source = "id.id", target = "idId")
    MMatchResultCutinDTO toDto(MMatchResultCutin mMatchResultCutin);

    @Mapping(source = "idId", target = "id")
    MMatchResultCutin toEntity(MMatchResultCutinDTO mMatchResultCutinDTO);

    default MMatchResultCutin fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMatchResultCutin mMatchResultCutin = new MMatchResultCutin();
        mMatchResultCutin.setId(id);
        return mMatchResultCutin;
    }
}
