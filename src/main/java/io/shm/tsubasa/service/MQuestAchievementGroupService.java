package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MQuestAchievementGroup;
import io.shm.tsubasa.repository.MQuestAchievementGroupRepository;
import io.shm.tsubasa.service.dto.MQuestAchievementGroupDTO;
import io.shm.tsubasa.service.mapper.MQuestAchievementGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MQuestAchievementGroup}.
 */
@Service
@Transactional
public class MQuestAchievementGroupService {

    private final Logger log = LoggerFactory.getLogger(MQuestAchievementGroupService.class);

    private final MQuestAchievementGroupRepository mQuestAchievementGroupRepository;

    private final MQuestAchievementGroupMapper mQuestAchievementGroupMapper;

    public MQuestAchievementGroupService(MQuestAchievementGroupRepository mQuestAchievementGroupRepository, MQuestAchievementGroupMapper mQuestAchievementGroupMapper) {
        this.mQuestAchievementGroupRepository = mQuestAchievementGroupRepository;
        this.mQuestAchievementGroupMapper = mQuestAchievementGroupMapper;
    }

    /**
     * Save a mQuestAchievementGroup.
     *
     * @param mQuestAchievementGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MQuestAchievementGroupDTO save(MQuestAchievementGroupDTO mQuestAchievementGroupDTO) {
        log.debug("Request to save MQuestAchievementGroup : {}", mQuestAchievementGroupDTO);
        MQuestAchievementGroup mQuestAchievementGroup = mQuestAchievementGroupMapper.toEntity(mQuestAchievementGroupDTO);
        mQuestAchievementGroup = mQuestAchievementGroupRepository.save(mQuestAchievementGroup);
        return mQuestAchievementGroupMapper.toDto(mQuestAchievementGroup);
    }

    /**
     * Get all the mQuestAchievementGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestAchievementGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MQuestAchievementGroups");
        return mQuestAchievementGroupRepository.findAll(pageable)
            .map(mQuestAchievementGroupMapper::toDto);
    }


    /**
     * Get one mQuestAchievementGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MQuestAchievementGroupDTO> findOne(Long id) {
        log.debug("Request to get MQuestAchievementGroup : {}", id);
        return mQuestAchievementGroupRepository.findById(id)
            .map(mQuestAchievementGroupMapper::toDto);
    }

    /**
     * Delete the mQuestAchievementGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MQuestAchievementGroup : {}", id);
        mQuestAchievementGroupRepository.deleteById(id);
    }
}
