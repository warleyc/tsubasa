package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MLeagueRegulationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MLeagueRegulation} and its DTO {@link MLeagueRegulationDTO}.
 */
@Mapper(componentModel = "spring", uses = {MMatchOptionMapper.class})
public interface MLeagueRegulationMapper extends EntityMapper<MLeagueRegulationDTO, MLeagueRegulation> {

    @Mapping(source = "mmatchoption.id", target = "mmatchoptionId")
    MLeagueRegulationDTO toDto(MLeagueRegulation mLeagueRegulation);

    @Mapping(source = "mmatchoptionId", target = "mmatchoption")
    MLeagueRegulation toEntity(MLeagueRegulationDTO mLeagueRegulationDTO);

    default MLeagueRegulation fromId(Long id) {
        if (id == null) {
            return null;
        }
        MLeagueRegulation mLeagueRegulation = new MLeagueRegulation();
        mLeagueRegulation.setId(id);
        return mLeagueRegulation;
    }
}
