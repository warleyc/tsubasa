package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMarathonAchievementRewardGroup;
import io.shm.tsubasa.repository.MMarathonAchievementRewardGroupRepository;
import io.shm.tsubasa.service.dto.MMarathonAchievementRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MMarathonAchievementRewardGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMarathonAchievementRewardGroup}.
 */
@Service
@Transactional
public class MMarathonAchievementRewardGroupService {

    private final Logger log = LoggerFactory.getLogger(MMarathonAchievementRewardGroupService.class);

    private final MMarathonAchievementRewardGroupRepository mMarathonAchievementRewardGroupRepository;

    private final MMarathonAchievementRewardGroupMapper mMarathonAchievementRewardGroupMapper;

    public MMarathonAchievementRewardGroupService(MMarathonAchievementRewardGroupRepository mMarathonAchievementRewardGroupRepository, MMarathonAchievementRewardGroupMapper mMarathonAchievementRewardGroupMapper) {
        this.mMarathonAchievementRewardGroupRepository = mMarathonAchievementRewardGroupRepository;
        this.mMarathonAchievementRewardGroupMapper = mMarathonAchievementRewardGroupMapper;
    }

    /**
     * Save a mMarathonAchievementRewardGroup.
     *
     * @param mMarathonAchievementRewardGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MMarathonAchievementRewardGroupDTO save(MMarathonAchievementRewardGroupDTO mMarathonAchievementRewardGroupDTO) {
        log.debug("Request to save MMarathonAchievementRewardGroup : {}", mMarathonAchievementRewardGroupDTO);
        MMarathonAchievementRewardGroup mMarathonAchievementRewardGroup = mMarathonAchievementRewardGroupMapper.toEntity(mMarathonAchievementRewardGroupDTO);
        mMarathonAchievementRewardGroup = mMarathonAchievementRewardGroupRepository.save(mMarathonAchievementRewardGroup);
        return mMarathonAchievementRewardGroupMapper.toDto(mMarathonAchievementRewardGroup);
    }

    /**
     * Get all the mMarathonAchievementRewardGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonAchievementRewardGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMarathonAchievementRewardGroups");
        return mMarathonAchievementRewardGroupRepository.findAll(pageable)
            .map(mMarathonAchievementRewardGroupMapper::toDto);
    }


    /**
     * Get one mMarathonAchievementRewardGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMarathonAchievementRewardGroupDTO> findOne(Long id) {
        log.debug("Request to get MMarathonAchievementRewardGroup : {}", id);
        return mMarathonAchievementRewardGroupRepository.findById(id)
            .map(mMarathonAchievementRewardGroupMapper::toDto);
    }

    /**
     * Delete the mMarathonAchievementRewardGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMarathonAchievementRewardGroup : {}", id);
        mMarathonAchievementRewardGroupRepository.deleteById(id);
    }
}
