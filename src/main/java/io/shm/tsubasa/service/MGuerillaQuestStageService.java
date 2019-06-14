package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGuerillaQuestStage;
import io.shm.tsubasa.repository.MGuerillaQuestStageRepository;
import io.shm.tsubasa.service.dto.MGuerillaQuestStageDTO;
import io.shm.tsubasa.service.mapper.MGuerillaQuestStageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGuerillaQuestStage}.
 */
@Service
@Transactional
public class MGuerillaQuestStageService {

    private final Logger log = LoggerFactory.getLogger(MGuerillaQuestStageService.class);

    private final MGuerillaQuestStageRepository mGuerillaQuestStageRepository;

    private final MGuerillaQuestStageMapper mGuerillaQuestStageMapper;

    public MGuerillaQuestStageService(MGuerillaQuestStageRepository mGuerillaQuestStageRepository, MGuerillaQuestStageMapper mGuerillaQuestStageMapper) {
        this.mGuerillaQuestStageRepository = mGuerillaQuestStageRepository;
        this.mGuerillaQuestStageMapper = mGuerillaQuestStageMapper;
    }

    /**
     * Save a mGuerillaQuestStage.
     *
     * @param mGuerillaQuestStageDTO the entity to save.
     * @return the persisted entity.
     */
    public MGuerillaQuestStageDTO save(MGuerillaQuestStageDTO mGuerillaQuestStageDTO) {
        log.debug("Request to save MGuerillaQuestStage : {}", mGuerillaQuestStageDTO);
        MGuerillaQuestStage mGuerillaQuestStage = mGuerillaQuestStageMapper.toEntity(mGuerillaQuestStageDTO);
        mGuerillaQuestStage = mGuerillaQuestStageRepository.save(mGuerillaQuestStage);
        return mGuerillaQuestStageMapper.toDto(mGuerillaQuestStage);
    }

    /**
     * Get all the mGuerillaQuestStages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuerillaQuestStageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGuerillaQuestStages");
        return mGuerillaQuestStageRepository.findAll(pageable)
            .map(mGuerillaQuestStageMapper::toDto);
    }


    /**
     * Get one mGuerillaQuestStage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGuerillaQuestStageDTO> findOne(Long id) {
        log.debug("Request to get MGuerillaQuestStage : {}", id);
        return mGuerillaQuestStageRepository.findById(id)
            .map(mGuerillaQuestStageMapper::toDto);
    }

    /**
     * Delete the mGuerillaQuestStage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGuerillaQuestStage : {}", id);
        mGuerillaQuestStageRepository.deleteById(id);
    }
}
