package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MEmblemPartsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MEmblemParts} and its DTO {@link MEmblemPartsDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MEmblemPartsMapper extends EntityMapper<MEmblemPartsDTO, MEmblemParts> {


    @Mapping(target = "mDummyEmblems", ignore = true)
    @Mapping(target = "removeMDummyEmblem", ignore = true)
    MEmblemParts toEntity(MEmblemPartsDTO mEmblemPartsDTO);

    default MEmblemParts fromId(Long id) {
        if (id == null) {
            return null;
        }
        MEmblemParts mEmblemParts = new MEmblemParts();
        mEmblemParts.setId(id);
        return mEmblemParts;
    }
}
