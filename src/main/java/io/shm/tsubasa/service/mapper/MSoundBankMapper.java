package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MSoundBankDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MSoundBank} and its DTO {@link MSoundBankDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MSoundBankMapper extends EntityMapper<MSoundBankDTO, MSoundBank> {



    default MSoundBank fromId(Long id) {
        if (id == null) {
            return null;
        }
        MSoundBank mSoundBank = new MSoundBank();
        mSoundBank.setId(id);
        return mSoundBank;
    }
}
