package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MChallengeQuestStage;
import io.shm.tsubasa.repository.MChallengeQuestStageRepository;
import io.shm.tsubasa.service.dto.MChallengeQuestStageDTO;
import io.shm.tsubasa.service.mapper.MChallengeQuestStageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MChallengeQuestStage}.
 */
@Service
@Transactional
public class MChallengeQuestStageService {

    private final Logger log = LoggerFactory.getLogger(MChallengeQuestStageService.class);

    private final MChallengeQuestStageRepository mChallengeQuestStageRepository;

    private final MChallengeQuestStageMapper mChallengeQuestStageMapper;

    public MChallengeQuestStageService(MChallengeQuestStageRepository mChallengeQuestStageRepository, MChallengeQuestStageMapper mChallengeQuestStageMapper) {
        this.mChallengeQuestStageRepository = mChallengeQuestStageRepository;
        this.mChallengeQuestStageMapper = mChallengeQuestStageMapper;
    }

    /**
     * Save a mChallengeQuestStage.
     *
     * @param mChallengeQuestStageDTO the entity to save.
     * @return the persisted entity.
     */
    public MChallengeQuestStageDTO save(MChallengeQuestStageDTO mChallengeQuestStageDTO) {
        log.debug("Request to save MChallengeQuestStage : {}", mChallengeQuestStageDTO);
        MChallengeQuestStage mChallengeQuestStage = mChallengeQuestStageMapper.toEntity(mChallengeQuestStageDTO);
        mChallengeQuestStage = mChallengeQuestStageRepository.save(mChallengeQuestStage);
        return mChallengeQuestStageMapper.toDto(mChallengeQuestStage);
    }

    /**
     * Get all the mChallengeQuestStages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MChallengeQuestStageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MChallengeQuestStages");
        return mChallengeQuestStageRepository.findAll(pageable)
            .map(mChallengeQuestStageMapper::toDto);
    }


    /**
     * Get one mChallengeQuestStage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MChallengeQuestStageDTO> findOne(Long id) {
        log.debug("Request to get MChallengeQuestStage : {}", id);
        return mChallengeQuestStageRepository.findById(id)
            .map(mChallengeQuestStageMapper::toDto);
    }

    /**
     * Delete the mChallengeQuestStage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MChallengeQuestStage : {}", id);
        mChallengeQuestStageRepository.deleteById(id);
    }
}
