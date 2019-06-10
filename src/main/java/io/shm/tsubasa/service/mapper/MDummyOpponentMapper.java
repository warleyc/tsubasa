package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDummyOpponentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDummyOpponent} and its DTO {@link MDummyOpponentDTO}.
 */
@Mapper(componentModel = "spring", uses = {MNpcDeckMapper.class})
public interface MDummyOpponentMapper extends EntityMapper<MDummyOpponentDTO, MDummyOpponent> {

    @Mapping(source = "id.id", target = "idId")
    MDummyOpponentDTO toDto(MDummyOpponent mDummyOpponent);

    @Mapping(source = "idId", target = "id")
    MDummyOpponent toEntity(MDummyOpponentDTO mDummyOpponentDTO);

    default MDummyOpponent fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDummyOpponent mDummyOpponent = new MDummyOpponent();
        mDummyOpponent.setId(id);
        return mDummyOpponent;
    }
}
