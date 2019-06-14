package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTicketQuestStage;
import io.shm.tsubasa.repository.MTicketQuestStageRepository;
import io.shm.tsubasa.service.dto.MTicketQuestStageDTO;
import io.shm.tsubasa.service.mapper.MTicketQuestStageMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTicketQuestStage}.
 */
@Service
@Transactional
public class MTicketQuestStageService {

    private final Logger log = LoggerFactory.getLogger(MTicketQuestStageService.class);

    private final MTicketQuestStageRepository mTicketQuestStageRepository;

    private final MTicketQuestStageMapper mTicketQuestStageMapper;

    public MTicketQuestStageService(MTicketQuestStageRepository mTicketQuestStageRepository, MTicketQuestStageMapper mTicketQuestStageMapper) {
        this.mTicketQuestStageRepository = mTicketQuestStageRepository;
        this.mTicketQuestStageMapper = mTicketQuestStageMapper;
    }

    /**
     * Save a mTicketQuestStage.
     *
     * @param mTicketQuestStageDTO the entity to save.
     * @return the persisted entity.
     */
    public MTicketQuestStageDTO save(MTicketQuestStageDTO mTicketQuestStageDTO) {
        log.debug("Request to save MTicketQuestStage : {}", mTicketQuestStageDTO);
        MTicketQuestStage mTicketQuestStage = mTicketQuestStageMapper.toEntity(mTicketQuestStageDTO);
        mTicketQuestStage = mTicketQuestStageRepository.save(mTicketQuestStage);
        return mTicketQuestStageMapper.toDto(mTicketQuestStage);
    }

    /**
     * Get all the mTicketQuestStages.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTicketQuestStageDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTicketQuestStages");
        return mTicketQuestStageRepository.findAll(pageable)
            .map(mTicketQuestStageMapper::toDto);
    }


    /**
     * Get one mTicketQuestStage by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTicketQuestStageDTO> findOne(Long id) {
        log.debug("Request to get MTicketQuestStage : {}", id);
        return mTicketQuestStageRepository.findById(id)
            .map(mTicketQuestStageMapper::toDto);
    }

    /**
     * Delete the mTicketQuestStage by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTicketQuestStage : {}", id);
        mTicketQuestStageRepository.deleteById(id);
    }
}
