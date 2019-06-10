package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MAnnounceTextDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MAnnounceText} and its DTO {@link MAnnounceTextDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MAnnounceTextMapper extends EntityMapper<MAnnounceTextDTO, MAnnounceText> {



    default MAnnounceText fromId(Long id) {
        if (id == null) {
            return null;
        }
        MAnnounceText mAnnounceText = new MAnnounceText();
        mAnnounceText.setId(id);
        return mAnnounceText;
    }
}
