package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGoalJudgement;
import io.shm.tsubasa.repository.MGoalJudgementRepository;
import io.shm.tsubasa.service.dto.MGoalJudgementDTO;
import io.shm.tsubasa.service.mapper.MGoalJudgementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGoalJudgement}.
 */
@Service
@Transactional
public class MGoalJudgementService {

    private final Logger log = LoggerFactory.getLogger(MGoalJudgementService.class);

    private final MGoalJudgementRepository mGoalJudgementRepository;

    private final MGoalJudgementMapper mGoalJudgementMapper;

    public MGoalJudgementService(MGoalJudgementRepository mGoalJudgementRepository, MGoalJudgementMapper mGoalJudgementMapper) {
        this.mGoalJudgementRepository = mGoalJudgementRepository;
        this.mGoalJudgementMapper = mGoalJudgementMapper;
    }

    /**
     * Save a mGoalJudgement.
     *
     * @param mGoalJudgementDTO the entity to save.
     * @return the persisted entity.
     */
    public MGoalJudgementDTO save(MGoalJudgementDTO mGoalJudgementDTO) {
        log.debug("Request to save MGoalJudgement : {}", mGoalJudgementDTO);
        MGoalJudgement mGoalJudgement = mGoalJudgementMapper.toEntity(mGoalJudgementDTO);
        mGoalJudgement = mGoalJudgementRepository.save(mGoalJudgement);
        return mGoalJudgementMapper.toDto(mGoalJudgement);
    }

    /**
     * Get all the mGoalJudgements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGoalJudgementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGoalJudgements");
        return mGoalJudgementRepository.findAll(pageable)
            .map(mGoalJudgementMapper::toDto);
    }


    /**
     * Get one mGoalJudgement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGoalJudgementDTO> findOne(Long id) {
        log.debug("Request to get MGoalJudgement : {}", id);
        return mGoalJudgementRepository.findById(id)
            .map(mGoalJudgementMapper::toDto);
    }

    /**
     * Delete the mGoalJudgement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGoalJudgement : {}", id);
        mGoalJudgementRepository.deleteById(id);
    }
}
