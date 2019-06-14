package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MTeamDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MTeam} and its DTO {@link MTeamDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MTeamMapper extends EntityMapper<MTeamDTO, MTeam> {


    @Mapping(target = "mTargetTeamGroups", ignore = true)
    MTeam toEntity(MTeamDTO mTeamDTO);

    default MTeam fromId(Long id) {
        if (id == null) {
            return null;
        }
        MTeam mTeam = new MTeam();
        mTeam.setId(id);
        return mTeam;
    }
}
