package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MHomeMarqueeDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MHomeMarquee} and its DTO {@link MHomeMarqueeDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MHomeMarqueeMapper extends EntityMapper<MHomeMarqueeDTO, MHomeMarquee> {



    default MHomeMarquee fromId(Long id) {
        if (id == null) {
            return null;
        }
        MHomeMarquee mHomeMarquee = new MHomeMarquee();
        mHomeMarquee.setId(id);
        return mHomeMarquee;
    }
}
