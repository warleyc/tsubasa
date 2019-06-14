package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MArousalDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MArousal} and its DTO {@link MArousalDTO}.
 */
@Mapper(componentModel = "spring", uses = {MPlayableCardMapper.class})
public interface MArousalMapper extends EntityMapper<MArousalDTO, MArousal> {

    @Mapping(source = "mplayablecard.id", target = "mplayablecardId")
    MArousalDTO toDto(MArousal mArousal);

    @Mapping(source = "mplayablecardId", target = "mplayablecard")
    MArousal toEntity(MArousalDTO mArousalDTO);

    default MArousal fromId(Long id) {
        if (id == null) {
            return null;
        }
        MArousal mArousal = new MArousal();
        mArousal.setId(id);
        return mArousal;
    }
}
