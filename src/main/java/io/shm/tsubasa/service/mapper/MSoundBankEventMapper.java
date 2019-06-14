package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MSoundBankEventDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MSoundBankEvent} and its DTO {@link MSoundBankEventDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MSoundBankEventMapper extends EntityMapper<MSoundBankEventDTO, MSoundBankEvent> {



    default MSoundBankEvent fromId(Long id) {
        if (id == null) {
            return null;
        }
        MSoundBankEvent mSoundBankEvent = new MSoundBankEvent();
        mSoundBankEvent.setId(id);
        return mSoundBankEvent;
    }
}
