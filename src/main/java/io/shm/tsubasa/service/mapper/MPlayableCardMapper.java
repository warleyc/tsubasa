package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MPlayableCardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MPlayableCard} and its DTO {@link MPlayableCardDTO}.
 */
@Mapper(componentModel = "spring", uses = {MModelCardMapper.class})
public interface MPlayableCardMapper extends EntityMapper<MPlayableCardDTO, MPlayableCard> {

    @Mapping(source = "id.id", target = "idId")
    MPlayableCardDTO toDto(MPlayableCard mPlayableCard);

    @Mapping(source = "idId", target = "id")
    @Mapping(target = "mArousals", ignore = true)
    @Mapping(target = "mTargetPlayableCardGroups", ignore = true)
    MPlayableCard toEntity(MPlayableCardDTO mPlayableCardDTO);

    default MPlayableCard fromId(Long id) {
        if (id == null) {
            return null;
        }
        MPlayableCard mPlayableCard = new MPlayableCard();
        mPlayableCard.setId(id);
        return mPlayableCard;
    }
}
