package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MDummyEmblemDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MDummyEmblem} and its DTO {@link MDummyEmblemDTO}.
 */
@Mapper(componentModel = "spring", uses = {MEmblemPartsMapper.class})
public interface MDummyEmblemMapper extends EntityMapper<MDummyEmblemDTO, MDummyEmblem> {

    @Mapping(source = "memblemparts.id", target = "memblempartsId")
    MDummyEmblemDTO toDto(MDummyEmblem mDummyEmblem);

    @Mapping(source = "memblempartsId", target = "memblemparts")
    MDummyEmblem toEntity(MDummyEmblemDTO mDummyEmblemDTO);

    default MDummyEmblem fromId(Long id) {
        if (id == null) {
            return null;
        }
        MDummyEmblem mDummyEmblem = new MDummyEmblem();
        mDummyEmblem.setId(id);
        return mDummyEmblem;
    }
}
