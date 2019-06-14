package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MGoalJudgementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MGoalJudgement} and its DTO {@link MGoalJudgementDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MGoalJudgementMapper extends EntityMapper<MGoalJudgementDTO, MGoalJudgement> {



    default MGoalJudgement fromId(Long id) {
        if (id == null) {
            return null;
        }
        MGoalJudgement mGoalJudgement = new MGoalJudgement();
        mGoalJudgement.setId(id);
        return mGoalJudgement;
    }
}
