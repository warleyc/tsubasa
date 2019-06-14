package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MMarathonBoostScheduleDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MMarathonBoostSchedule} and its DTO {@link MMarathonBoostScheduleDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MMarathonBoostScheduleMapper extends EntityMapper<MMarathonBoostScheduleDTO, MMarathonBoostSchedule> {



    default MMarathonBoostSchedule fromId(Long id) {
        if (id == null) {
            return null;
        }
        MMarathonBoostSchedule mMarathonBoostSchedule = new MMarathonBoostSchedule();
        mMarathonBoostSchedule.setId(id);
        return mMarathonBoostSchedule;
    }
}
