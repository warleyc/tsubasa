package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTrainingCardDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTrainingCard} and its DTO {@link MTrainingCardDTO}.
 */
@Mapper(componentModel = "spring", uses = {MCardThumbnailAssetsMapper.class})
public interface MTrainingCardMapper extends EntityMapper<MTrainingCardDTO, MTrainingCard> {

    @Mapping(source = "mcardthumbnailassets.id", target = "mcardthumbnailassetsId")
    MTrainingCardDTO toDto(MTrainingCard mTrainingCard);

    @Mapping(source = "mcardthumbnailassetsId", target = "mcardthumbnailassets")
    MTrainingCard toEntity(MTrainingCardDTO mTrainingCardDTO);

    default MTrainingCard fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTrainingCard mTrainingCard = new MTrainingCard();
        mTrainingCard.setId(id);
        return mTrainingCard;
    }
}
