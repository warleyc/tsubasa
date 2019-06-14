package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTimeattackQuestStage;
import io.shm.tsubasa.repository.MTimeattackQuestStageRepository;
import io.shm.tsubasa.service.dto.MTimeattackQuestStageDTO;
import io.shm.tsubasa.service.mapper.MTimeattackQuestStageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTimeattackQuestStage}.
 */
@Service
@Transactional
public class MTimeattackQuestStageService {

    private final Logger log = LoggerFactory.getLogger(MTimeattackQuestStageService.class);

    private final MTimeattackQuestStageRepository mTimeattackQuestStageRepository;

    private final MTimeattackQuestStageMapper mTimeattackQuestStageMapper;

    public MTimeattackQuestStageService(MTimeattackQuestStageRepository mTimeattackQuestStageRepository, MTimeattackQuestStageMapper mTimeattackQuestStageMapper) {
        this.mTimeattackQuestStageRepository = mTimeattackQuestStageRepository;
        this.mTimeattackQuestStageMapper = mTimeattackQuestStageMapper;
    }

    /**
     * Save a mTimeattackQuestStage.
     *
     * @param mTimeattackQuestStageDTO the entity to save.
     * @return the persisted entity.
     */
    public MTimeattackQuestStageDTO save(MTimeattackQuestStageDTO mTimeattackQuestStageDTO) {
        log.debug("Request to save MTimeattackQuestStage : {}", mTimeattackQuestStageDTO);
        MTimeattackQuestStage mTimeattackQuestStage = mTimeattackQuestStageMapper.toEntity(mTimeattackQuestStageDTO);
        mTimeattackQuestStage = mTimeattackQuestStageRepository.save(mTimeattackQuestStage);
        return mTimeattackQuestStageMapper.toDto(mTimeattackQuestStage);
    }

    /**
     * Get all the mTimeattackQuestStages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTimeattackQuestStageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTimeattackQuestStages");
        return mTimeattackQuestStageRepository.findAll(pageable)
            .map(mTimeattackQuestStageMapper::toDto);
    }


    /**
     * Get one mTimeattackQuestStage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTimeattackQuestStageDTO> findOne(Long id) {
        log.debug("Request to get MTimeattackQuestStage : {}", id);
        return mTimeattackQuestStageRepository.findById(id)
            .map(mTimeattackQuestStageMapper::toDto);
    }

    /**
     * Delete the mTimeattackQuestStage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTimeattackQuestStage : {}", id);
        mTimeattackQuestStageRepository.deleteById(id);
    }
}
