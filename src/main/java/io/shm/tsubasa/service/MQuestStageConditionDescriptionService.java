package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MQuestStageConditionDescription;
import io.shm.tsubasa.repository.MQuestStageConditionDescriptionRepository;
import io.shm.tsubasa.service.dto.MQuestStageConditionDescriptionDTO;
import io.shm.tsubasa.service.mapper.MQuestStageConditionDescriptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MQuestStageConditionDescription}.
 */
@Service
@Transactional
public class MQuestStageConditionDescriptionService {

    private final Logger log = LoggerFactory.getLogger(MQuestStageConditionDescriptionService.class);

    private final MQuestStageConditionDescriptionRepository mQuestStageConditionDescriptionRepository;

    private final MQuestStageConditionDescriptionMapper mQuestStageConditionDescriptionMapper;

    public MQuestStageConditionDescriptionService(MQuestStageConditionDescriptionRepository mQuestStageConditionDescriptionRepository, MQuestStageConditionDescriptionMapper mQuestStageConditionDescriptionMapper) {
        this.mQuestStageConditionDescriptionRepository = mQuestStageConditionDescriptionRepository;
        this.mQuestStageConditionDescriptionMapper = mQuestStageConditionDescriptionMapper;
    }

    /**
     * Save a mQuestStageConditionDescription.
     *
     * @param mQuestStageConditionDescriptionDTO the entity to save.
     * @return the persisted entity.
     */
    public MQuestStageConditionDescriptionDTO save(MQuestStageConditionDescriptionDTO mQuestStageConditionDescriptionDTO) {
        log.debug("Request to save MQuestStageConditionDescription : {}", mQuestStageConditionDescriptionDTO);
        MQuestStageConditionDescription mQuestStageConditionDescription = mQuestStageConditionDescriptionMapper.toEntity(mQuestStageConditionDescriptionDTO);
        mQuestStageConditionDescription = mQuestStageConditionDescriptionRepository.save(mQuestStageConditionDescription);
        return mQuestStageConditionDescriptionMapper.toDto(mQuestStageConditionDescription);
    }

    /**
     * Get all the mQuestStageConditionDescriptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestStageConditionDescriptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MQuestStageConditionDescriptions");
        return mQuestStageConditionDescriptionRepository.findAll(pageable)
            .map(mQuestStageConditionDescriptionMapper::toDto);
    }


    /**
     * Get one mQuestStageConditionDescription by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MQuestStageConditionDescriptionDTO> findOne(Long id) {
        log.debug("Request to get MQuestStageConditionDescription : {}", id);
        return mQuestStageConditionDescriptionRepository.findById(id)
            .map(mQuestStageConditionDescriptionMapper::toDto);
    }

    /**
     * Delete the mQuestStageConditionDescription by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MQuestStageConditionDescription : {}", id);
        mQuestStageConditionDescriptionRepository.deleteById(id);
    }
}
