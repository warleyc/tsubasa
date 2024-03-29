package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MNpcDeckDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MNpcDeck} and its DTO {@link MNpcDeckDTO}.
 */
@Mapper(componentModel = "spring", uses = {MFormationMapper.class})
public interface MNpcDeckMapper extends EntityMapper<MNpcDeckDTO, MNpcDeck> {

    @Mapping(source = "mformation.id", target = "mformationId")
    MNpcDeckDTO toDto(MNpcDeck mNpcDeck);

    @Mapping(source = "mformationId", target = "mformation")
    @Mapping(target = "mDummyOpponents", ignore = true)
    MNpcDeck toEntity(MNpcDeckDTO mNpcDeckDTO);

    default MNpcDeck fromId(Long id) {
        if (id == null) {
            return null;
        }
        MNpcDeck mNpcDeck = new MNpcDeck();
        mNpcDeck.setId(id);
        return mNpcDeck;
    }
}
