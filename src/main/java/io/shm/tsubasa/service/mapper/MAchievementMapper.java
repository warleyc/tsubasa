package io.shm.tsubasa.service.mapper;

import io.shm.tsubasa.domain.*;
import io.shm.tsubasa.service.dto.MAchievementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MAchievement} and its DTO {@link MAchievementDTO}.
 */
@Mapper(componentModel = "spring", uses = {MMissionMapper.class})
public interface MAchievementMapper extends EntityMapper<MAchievementDTO, MAchievement> {

    @Mapping(source = "mmission.id", target = "mmissionId")
    MAchievementDTO toDto(MAchievement mAchievement);

    @Mapping(source = "mmissionId", target = "mmission")
    MAchievement toEntity(MAchievementDTO mAchievementDTO);

    default MAchievement fromId(Long id) {
        if (id == null) {
            return null;
        }
        MAchievement mAchievement = new MAchievement();
        mAchievement.setId(id);
        return mAchievement;
    }
}
