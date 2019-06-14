package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MWeeklyQuestStage;
import io.shm.tsubasa.repository.MWeeklyQuestStageRepository;
import io.shm.tsubasa.service.dto.MWeeklyQuestStageDTO;
import io.shm.tsubasa.service.mapper.MWeeklyQuestStageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MWeeklyQuestStage}.
 */
@Service
@Transactional
public class MWeeklyQuestStageService {

    private final Logger log = LoggerFactory.getLogger(MWeeklyQuestStageService.class);

    private final MWeeklyQuestStageRepository mWeeklyQuestStageRepository;

    private final MWeeklyQuestStageMapper mWeeklyQuestStageMapper;

    public MWeeklyQuestStageService(MWeeklyQuestStageRepository mWeeklyQuestStageRepository, MWeeklyQuestStageMapper mWeeklyQuestStageMapper) {
        this.mWeeklyQuestStageRepository = mWeeklyQuestStageRepository;
        this.mWeeklyQuestStageMapper = mWeeklyQuestStageMapper;
    }

    /**
     * Save a mWeeklyQuestStage.
     *
     * @param mWeeklyQuestStageDTO the entity to save.
     * @return the persisted entity.
     */
    public MWeeklyQuestStageDTO save(MWeeklyQuestStageDTO mWeeklyQuestStageDTO) {
        log.debug("Request to save MWeeklyQuestStage : {}", mWeeklyQuestStageDTO);
        MWeeklyQuestStage mWeeklyQuestStage = mWeeklyQuestStageMapper.toEntity(mWeeklyQuestStageDTO);
        mWeeklyQuestStage = mWeeklyQuestStageRepository.save(mWeeklyQuestStage);
        return mWeeklyQuestStageMapper.toDto(mWeeklyQuestStage);
    }

    /**
     * Get all the mWeeklyQuestStages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MWeeklyQuestStageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MWeeklyQuestStages");
        return mWeeklyQuestStageRepository.findAll(pageable)
            .map(mWeeklyQuestStageMapper::toDto);
    }


    /**
     * Get one mWeeklyQuestStage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MWeeklyQuestStageDTO> findOne(Long id) {
        log.debug("Request to get MWeeklyQuestStage : {}", id);
        return mWeeklyQuestStageRepository.findById(id)
            .map(mWeeklyQuestStageMapper::toDto);
    }

    /**
     * Delete the mWeeklyQuestStage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MWeeklyQuestStage : {}", id);
        mWeeklyQuestStageRepository.deleteById(id);
    }
}
