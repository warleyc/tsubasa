package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MPvpWaveDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MPvpWave} and its DTO {@link MPvpWaveDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MPvpWaveMapper extends EntityMapper<MPvpWaveDTO, MPvpWave> {


    @Mapping(target = "mPvpRankingRewards", ignore = true)
    MPvpWave toEntity(MPvpWaveDTO mPvpWaveDTO);

    default MPvpWave fromId(Long id) {
        if (id == null) {
            return null;
        }
        MPvpWave mPvpWave = new MPvpWave();
        mPvpWave.setId(id);
        return mPvpWave;
    }
}
