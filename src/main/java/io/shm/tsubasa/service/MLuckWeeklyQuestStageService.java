package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MLuckWeeklyQuestStage;
import io.shm.tsubasa.repository.MLuckWeeklyQuestStageRepository;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestStageDTO;
import io.shm.tsubasa.service.mapper.MLuckWeeklyQuestStageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MLuckWeeklyQuestStage}.
 */
@Service
@Transactional
public class MLuckWeeklyQuestStageService {

    private final Logger log = LoggerFactory.getLogger(MLuckWeeklyQuestStageService.class);

    private final MLuckWeeklyQuestStageRepository mLuckWeeklyQuestStageRepository;

    private final MLuckWeeklyQuestStageMapper mLuckWeeklyQuestStageMapper;

    public MLuckWeeklyQuestStageService(MLuckWeeklyQuestStageRepository mLuckWeeklyQuestStageRepository, MLuckWeeklyQuestStageMapper mLuckWeeklyQuestStageMapper) {
        this.mLuckWeeklyQuestStageRepository = mLuckWeeklyQuestStageRepository;
        this.mLuckWeeklyQuestStageMapper = mLuckWeeklyQuestStageMapper;
    }

    /**
     * Save a mLuckWeeklyQuestStage.
     *
     * @param mLuckWeeklyQuestStageDTO the entity to save.
     * @return the persisted entity.
     */
    public MLuckWeeklyQuestStageDTO save(MLuckWeeklyQuestStageDTO mLuckWeeklyQuestStageDTO) {
        log.debug("Request to save MLuckWeeklyQuestStage : {}", mLuckWeeklyQuestStageDTO);
        MLuckWeeklyQuestStage mLuckWeeklyQuestStage = mLuckWeeklyQuestStageMapper.toEntity(mLuckWeeklyQuestStageDTO);
        mLuckWeeklyQuestStage = mLuckWeeklyQuestStageRepository.save(mLuckWeeklyQuestStage);
        return mLuckWeeklyQuestStageMapper.toDto(mLuckWeeklyQuestStage);
    }

    /**
     * Get all the mLuckWeeklyQuestStages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MLuckWeeklyQuestStageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MLuckWeeklyQuestStages");
        return mLuckWeeklyQuestStageRepository.findAll(pageable)
            .map(mLuckWeeklyQuestStageMapper::toDto);
    }


    /**
     * Get one mLuckWeeklyQuestStage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MLuckWeeklyQuestStageDTO> findOne(Long id) {
        log.debug("Request to get MLuckWeeklyQuestStage : {}", id);
        return mLuckWeeklyQuestStageRepository.findById(id)
            .map(mLuckWeeklyQuestStageMapper::toDto);
    }

    /**
     * Delete the mLuckWeeklyQuestStage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MLuckWeeklyQuestStage : {}", id);
        mLuckWeeklyQuestStageRepository.deleteById(id);
    }
}
