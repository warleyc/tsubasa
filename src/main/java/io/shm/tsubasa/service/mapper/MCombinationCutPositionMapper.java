package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCombinationCutPositionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCombinationCutPosition} and its DTO {@link MCombinationCutPositionDTO}.
 */
@Mapper(componentModel = "spring", uses = {MCharacterMapper.class})
public interface MCombinationCutPositionMapper extends EntityMapper<MCombinationCutPositionDTO, MCombinationCutPosition> {

    @Mapping(source = "mcharacter.id", target = "mcharacterId")
    MCombinationCutPositionDTO toDto(MCombinationCutPosition mCombinationCutPosition);

    @Mapping(source = "mcharacterId", target = "mcharacter")
    MCombinationCutPosition toEntity(MCombinationCutPositionDTO mCombinationCutPositionDTO);

    default MCombinationCutPosition fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCombinationCutPosition mCombinationCutPosition = new MCombinationCutPosition();
        mCombinationCutPosition.setId(id);
        return mCombinationCutPosition;
    }
}
