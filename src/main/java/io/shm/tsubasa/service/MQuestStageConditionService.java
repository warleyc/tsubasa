package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MQuestStageCondition;
import io.shm.tsubasa.repository.MQuestStageConditionRepository;
import io.shm.tsubasa.service.dto.MQuestStageConditionDTO;
import io.shm.tsubasa.service.mapper.MQuestStageConditionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MQuestStageCondition}.
 */
@Service
@Transactional
public class MQuestStageConditionService {

    private final Logger log = LoggerFactory.getLogger(MQuestStageConditionService.class);

    private final MQuestStageConditionRepository mQuestStageConditionRepository;

    private final MQuestStageConditionMapper mQuestStageConditionMapper;

    public MQuestStageConditionService(MQuestStageConditionRepository mQuestStageConditionRepository, MQuestStageConditionMapper mQuestStageConditionMapper) {
        this.mQuestStageConditionRepository = mQuestStageConditionRepository;
        this.mQuestStageConditionMapper = mQuestStageConditionMapper;
    }

    /**
     * Save a mQuestStageCondition.
     *
     * @param mQuestStageConditionDTO the entity to save.
     * @return the persisted entity.
     */
    public MQuestStageConditionDTO save(MQuestStageConditionDTO mQuestStageConditionDTO) {
        log.debug("Request to save MQuestStageCondition : {}", mQuestStageConditionDTO);
        MQuestStageCondition mQuestStageCondition = mQuestStageConditionMapper.toEntity(mQuestStageConditionDTO);
        mQuestStageCondition = mQuestStageConditionRepository.save(mQuestStageCondition);
        return mQuestStageConditionMapper.toDto(mQuestStageCondition);
    }

    /**
     * Get all the mQuestStageConditions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestStageConditionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MQuestStageConditions");
        return mQuestStageConditionRepository.findAll(pageable)
            .map(mQuestStageConditionMapper::toDto);
    }


    /**
     * Get one mQuestStageCondition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MQuestStageConditionDTO> findOne(Long id) {
        log.debug("Request to get MQuestStageCondition : {}", id);
        return mQuestStageConditionRepository.findById(id)
            .map(mQuestStageConditionMapper::toDto);
    }

    /**
     * Delete the mQuestStageCondition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MQuestStageCondition : {}", id);
        mQuestStageConditionRepository.deleteById(id);
    }
}
