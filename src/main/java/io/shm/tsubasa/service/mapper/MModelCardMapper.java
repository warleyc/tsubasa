package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MModelCardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MModelCard} and its DTO {@link MModelCardDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MModelCardMapper extends EntityMapper<MModelCardDTO, MModelCard> {


    @Mapping(target = "mPlayableCards", ignore = true)
    MModelCard toEntity(MModelCardDTO mModelCardDTO);

    default MModelCard fromId(Long id) {
        if (id == null) {
            return null;
        }
        MModelCard mModelCard = new MModelCard();
        mModelCard.setId(id);
        return mModelCard;
    }
}
