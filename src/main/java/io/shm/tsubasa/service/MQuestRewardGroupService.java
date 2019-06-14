package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MQuestRewardGroup;
import io.shm.tsubasa.repository.MQuestRewardGroupRepository;
import io.shm.tsubasa.service.dto.MQuestRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MQuestRewardGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MQuestRewardGroup}.
 */
@Service
@Transactional
public class MQuestRewardGroupService {

    private final Logger log = LoggerFactory.getLogger(MQuestRewardGroupService.class);

    private final MQuestRewardGroupRepository mQuestRewardGroupRepository;

    private final MQuestRewardGroupMapper mQuestRewardGroupMapper;

    public MQuestRewardGroupService(MQuestRewardGroupRepository mQuestRewardGroupRepository, MQuestRewardGroupMapper mQuestRewardGroupMapper) {
        this.mQuestRewardGroupRepository = mQuestRewardGroupRepository;
        this.mQuestRewardGroupMapper = mQuestRewardGroupMapper;
    }

    /**
     * Save a mQuestRewardGroup.
     *
     * @param mQuestRewardGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MQuestRewardGroupDTO save(MQuestRewardGroupDTO mQuestRewardGroupDTO) {
        log.debug("Request to save MQuestRewardGroup : {}", mQuestRewardGroupDTO);
        MQuestRewardGroup mQuestRewardGroup = mQuestRewardGroupMapper.toEntity(mQuestRewardGroupDTO);
        mQuestRewardGroup = mQuestRewardGroupRepository.save(mQuestRewardGroup);
        return mQuestRewardGroupMapper.toDto(mQuestRewardGroup);
    }

    /**
     * Get all the mQuestRewardGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestRewardGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MQuestRewardGroups");
        return mQuestRewardGroupRepository.findAll(pageable)
            .map(mQuestRewardGroupMapper::toDto);
    }


    /**
     * Get one mQuestRewardGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MQuestRewardGroupDTO> findOne(Long id) {
        log.debug("Request to get MQuestRewardGroup : {}", id);
        return mQuestRewardGroupRepository.findById(id)
            .map(mQuestRewardGroupMapper::toDto);
    }

    /**
     * Delete the mQuestRewardGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MQuestRewardGroup : {}", id);
        mQuestRewardGroupRepository.deleteById(id);
    }
}
