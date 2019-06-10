package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGachaRenditionFirstGimmickDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGachaRenditionFirstGimmick} and its DTO {@link MGachaRenditionFirstGimmickDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGachaRenditionFirstGimmickMapper extends EntityMapper<MGachaRenditionFirstGimmickDTO, MGachaRenditionFirstGimmick> {



    default MGachaRenditionFirstGimmick fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGachaRenditionFirstGimmick mGachaRenditionFirstGimmick = new MGachaRenditionFirstGimmick();
        mGachaRenditionFirstGimmick.setId(id);
        return mGachaRenditionFirstGimmick;
    }
}
