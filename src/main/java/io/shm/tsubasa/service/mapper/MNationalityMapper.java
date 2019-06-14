package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MNationalityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MNationality} and its DTO {@link MNationalityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MNationalityMapper extends EntityMapper<MNationalityDTO, MNationality> {


    @Mapping(target = "mTargetNationalityGroups", ignore = true)
    MNationality toEntity(MNationalityDTO mNationalityDTO);

    default MNationality fromId(Long id) {
        if (id == null) {
            return null;
        }
        MNationality mNationality = new MNationality();
        mNationality.setId(id);
        return mNationality;
    }
}
