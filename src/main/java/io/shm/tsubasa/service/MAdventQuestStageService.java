package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MAdventQuestStage;
import io.shm.tsubasa.repository.MAdventQuestStageRepository;
import io.shm.tsubasa.service.dto.MAdventQuestStageDTO;
import io.shm.tsubasa.service.mapper.MAdventQuestStageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MAdventQuestStage}.
 */
@Service
@Transactional
public class MAdventQuestStageService {

    private final Logger log = LoggerFactory.getLogger(MAdventQuestStageService.class);

    private final MAdventQuestStageRepository mAdventQuestStageRepository;

    private final MAdventQuestStageMapper mAdventQuestStageMapper;

    public MAdventQuestStageService(MAdventQuestStageRepository mAdventQuestStageRepository, MAdventQuestStageMapper mAdventQuestStageMapper) {
        this.mAdventQuestStageRepository = mAdventQuestStageRepository;
        this.mAdventQuestStageMapper = mAdventQuestStageMapper;
    }

    /**
     * Save a mAdventQuestStage.
     *
     * @param mAdventQuestStageDTO the entity to save.
     * @return the persisted entity.
     */
    public MAdventQuestStageDTO save(MAdventQuestStageDTO mAdventQuestStageDTO) {
        log.debug("Request to save MAdventQuestStage : {}", mAdventQuestStageDTO);
        MAdventQuestStage mAdventQuestStage = mAdventQuestStageMapper.toEntity(mAdventQuestStageDTO);
        mAdventQuestStage = mAdventQuestStageRepository.save(mAdventQuestStage);
        return mAdventQuestStageMapper.toDto(mAdventQuestStage);
    }

    /**
     * Get all the mAdventQuestStages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MAdventQuestStageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MAdventQuestStages");
        return mAdventQuestStageRepository.findAll(pageable)
            .map(mAdventQuestStageMapper::toDto);
    }


    /**
     * Get one mAdventQuestStage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MAdventQuestStageDTO> findOne(Long id) {
        log.debug("Request to get MAdventQuestStage : {}", id);
        return mAdventQuestStageRepository.findById(id)
            .map(mAdventQuestStageMapper::toDto);
    }

    /**
     * Delete the mAdventQuestStage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MAdventQuestStage : {}", id);
        mAdventQuestStageRepository.deleteById(id);
    }
}
