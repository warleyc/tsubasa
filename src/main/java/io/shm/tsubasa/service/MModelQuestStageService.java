package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MModelQuestStage;
import io.shm.tsubasa.repository.MModelQuestStageRepository;
import io.shm.tsubasa.service.dto.MModelQuestStageDTO;
import io.shm.tsubasa.service.mapper.MModelQuestStageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MModelQuestStage}.
 */
@Service
@Transactional
public class MModelQuestStageService {

    private final Logger log = LoggerFactory.getLogger(MModelQuestStageService.class);

    private final MModelQuestStageRepository mModelQuestStageRepository;

    private final MModelQuestStageMapper mModelQuestStageMapper;

    public MModelQuestStageService(MModelQuestStageRepository mModelQuestStageRepository, MModelQuestStageMapper mModelQuestStageMapper) {
        this.mModelQuestStageRepository = mModelQuestStageRepository;
        this.mModelQuestStageMapper = mModelQuestStageMapper;
    }

    /**
     * Save a mModelQuestStage.
     *
     * @param mModelQuestStageDTO the entity to save.
     * @return the persisted entity.
     */
    public MModelQuestStageDTO save(MModelQuestStageDTO mModelQuestStageDTO) {
        log.debug("Request to save MModelQuestStage : {}", mModelQuestStageDTO);
        MModelQuestStage mModelQuestStage = mModelQuestStageMapper.toEntity(mModelQuestStageDTO);
        mModelQuestStage = mModelQuestStageRepository.save(mModelQuestStage);
        return mModelQuestStageMapper.toDto(mModelQuestStage);
    }

    /**
     * Get all the mModelQuestStages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MModelQuestStageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MModelQuestStages");
        return mModelQuestStageRepository.findAll(pageable)
            .map(mModelQuestStageMapper::toDto);
    }


    /**
     * Get one mModelQuestStage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MModelQuestStageDTO> findOne(Long id) {
        log.debug("Request to get MModelQuestStage : {}", id);
        return mModelQuestStageRepository.findById(id)
            .map(mModelQuestStageMapper::toDto);
    }

    /**
     * Delete the mModelQuestStage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MModelQuestStage : {}", id);
        mModelQuestStageRepository.deleteById(id);
    }
}
