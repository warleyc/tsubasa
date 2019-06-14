package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMarathonQuestStage;
import io.shm.tsubasa.repository.MMarathonQuestStageRepository;
import io.shm.tsubasa.service.dto.MMarathonQuestStageDTO;
import io.shm.tsubasa.service.mapper.MMarathonQuestStageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMarathonQuestStage}.
 */
@Service
@Transactional
public class MMarathonQuestStageService {

    private final Logger log = LoggerFactory.getLogger(MMarathonQuestStageService.class);

    private final MMarathonQuestStageRepository mMarathonQuestStageRepository;

    private final MMarathonQuestStageMapper mMarathonQuestStageMapper;

    public MMarathonQuestStageService(MMarathonQuestStageRepository mMarathonQuestStageRepository, MMarathonQuestStageMapper mMarathonQuestStageMapper) {
        this.mMarathonQuestStageRepository = mMarathonQuestStageRepository;
        this.mMarathonQuestStageMapper = mMarathonQuestStageMapper;
    }

    /**
     * Save a mMarathonQuestStage.
     *
     * @param mMarathonQuestStageDTO the entity to save.
     * @return the persisted entity.
     */
    public MMarathonQuestStageDTO save(MMarathonQuestStageDTO mMarathonQuestStageDTO) {
        log.debug("Request to save MMarathonQuestStage : {}", mMarathonQuestStageDTO);
        MMarathonQuestStage mMarathonQuestStage = mMarathonQuestStageMapper.toEntity(mMarathonQuestStageDTO);
        mMarathonQuestStage = mMarathonQuestStageRepository.save(mMarathonQuestStage);
        return mMarathonQuestStageMapper.toDto(mMarathonQuestStage);
    }

    /**
     * Get all the mMarathonQuestStages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonQuestStageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMarathonQuestStages");
        return mMarathonQuestStageRepository.findAll(pageable)
            .map(mMarathonQuestStageMapper::toDto);
    }


    /**
     * Get one mMarathonQuestStage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMarathonQuestStageDTO> findOne(Long id) {
        log.debug("Request to get MMarathonQuestStage : {}", id);
        return mMarathonQuestStageRepository.findById(id)
            .map(mMarathonQuestStageMapper::toDto);
    }

    /**
     * Delete the mMarathonQuestStage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMarathonQuestStage : {}", id);
        mMarathonQuestStageRepository.deleteById(id);
    }
}
