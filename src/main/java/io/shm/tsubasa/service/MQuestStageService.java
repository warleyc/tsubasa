package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MQuestStage;
import io.shm.tsubasa.repository.MQuestStageRepository;
import io.shm.tsubasa.service.dto.MQuestStageDTO;
import io.shm.tsubasa.service.mapper.MQuestStageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MQuestStage}.
 */
@Service
@Transactional
public class MQuestStageService {

    private final Logger log = LoggerFactory.getLogger(MQuestStageService.class);

    private final MQuestStageRepository mQuestStageRepository;

    private final MQuestStageMapper mQuestStageMapper;

    public MQuestStageService(MQuestStageRepository mQuestStageRepository, MQuestStageMapper mQuestStageMapper) {
        this.mQuestStageRepository = mQuestStageRepository;
        this.mQuestStageMapper = mQuestStageMapper;
    }

    /**
     * Save a mQuestStage.
     *
     * @param mQuestStageDTO the entity to save.
     * @return the persisted entity.
     */
    public MQuestStageDTO save(MQuestStageDTO mQuestStageDTO) {
        log.debug("Request to save MQuestStage : {}", mQuestStageDTO);
        MQuestStage mQuestStage = mQuestStageMapper.toEntity(mQuestStageDTO);
        mQuestStage = mQuestStageRepository.save(mQuestStage);
        return mQuestStageMapper.toDto(mQuestStage);
    }

    /**
     * Get all the mQuestStages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestStageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MQuestStages");
        return mQuestStageRepository.findAll(pageable)
            .map(mQuestStageMapper::toDto);
    }


    /**
     * Get one mQuestStage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MQuestStageDTO> findOne(Long id) {
        log.debug("Request to get MQuestStage : {}", id);
        return mQuestStageRepository.findById(id)
            .map(mQuestStageMapper::toDto);
    }

    /**
     * Delete the mQuestStage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MQuestStage : {}", id);
        mQuestStageRepository.deleteById(id);
    }
}
