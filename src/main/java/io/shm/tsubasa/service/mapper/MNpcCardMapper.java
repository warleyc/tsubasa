package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MNpcCardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MNpcCard} and its DTO {@link MNpcCardDTO}.
 */
@Mapper(componentModel = "spring", uses = {MCharacterMapper.class})
public interface MNpcCardMapper extends EntityMapper<MNpcCardDTO, MNpcCard> {

    @Mapping(source = "id.id", target = "idId")
    MNpcCardDTO toDto(MNpcCard mNpcCard);

    @Mapping(source = "idId", target = "id")
    MNpcCard toEntity(MNpcCardDTO mNpcCardDTO);

    default MNpcCard fromId(Long id) {
        if (id == null) {
            return null;
        }
        MNpcCard mNpcCard = new MNpcCard();
        mNpcCard.setId(id);
        return mNpcCard;
    }
}
