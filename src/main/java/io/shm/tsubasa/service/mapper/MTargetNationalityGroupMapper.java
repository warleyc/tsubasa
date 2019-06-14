package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTargetNationalityGroupDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTargetNationalityGroup} and its DTO {@link MTargetNationalityGroupDTO}.
 */
@Mapper(componentModel = "spring", uses = {MNationalityMapper.class})
public interface MTargetNationalityGroupMapper extends EntityMapper<MTargetNationalityGroupDTO, MTargetNationalityGroup> {

    @Mapping(source = "mnationality.id", target = "mnationalityId")
    MTargetNationalityGroupDTO toDto(MTargetNationalityGroup mTargetNationalityGroup);

    @Mapping(source = "mnationalityId", target = "mnationality")
    MTargetNationalityGroup toEntity(MTargetNationalityGroupDTO mTargetNationalityGroupDTO);

    default MTargetNationalityGroup fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTargetNationalityGroup mTargetNationalityGroup = new MTargetNationalityGroup();
        mTargetNationalityGroup.setId(id);
        return mTargetNationalityGroup;
    }
}
