package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MCutSeqGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MCutSeqGroup} and its DTO {@link MCutSeqGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MCutSeqGroupMapper extends EntityMapper<MCutSeqGroupDTO, MCutSeqGroup> {



    default MCutSeqGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MCutSeqGroup mCutSeqGroup = new MCutSeqGroup();
        mCutSeqGroup.setId(id);
        return mCutSeqGroup;
    }
}
