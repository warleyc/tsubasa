package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MPvpRegulationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MPvpRegulation} and its DTO {@link MPvpRegulationDTO}.
 */
@Mapper(componentModel = "spring", uses = {MMatchOptionMapper.class})
public interface MPvpRegulationMapper extends EntityMapper<MPvpRegulationDTO, MPvpRegulation> {

    @Mapping(source = "mmatchoption.id", target = "mmatchoptionId")
    MPvpRegulationDTO toDto(MPvpRegulation mPvpRegulation);

    @Mapping(source = "mmatchoptionId", target = "mmatchoption")
    MPvpRegulation toEntity(MPvpRegulationDTO mPvpRegulationDTO);

    default MPvpRegulation fromId(Long id) {
        if (id == null) {
            return null;
        }
        MPvpRegulation mPvpRegulation = new MPvpRegulation();
        mPvpRegulation.setId(id);
        return mPvpRegulation;
    }
}
