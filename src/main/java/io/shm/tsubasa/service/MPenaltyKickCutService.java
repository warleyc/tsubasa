package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MPenaltyKickCut;
import io.shm.tsubasa.repository.MPenaltyKickCutRepository;
import io.shm.tsubasa.service.dto.MPenaltyKickCutDTO;
import io.shm.tsubasa.service.mapper.MPenaltyKickCutMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MPenaltyKickCut}.
 */
@Service
@Transactional
public class MPenaltyKickCutService {

    private final Logger log = LoggerFactory.getLogger(MPenaltyKickCutService.class);

    private final MPenaltyKickCutRepository mPenaltyKickCutRepository;

    private final MPenaltyKickCutMapper mPenaltyKickCutMapper;

    public MPenaltyKickCutService(MPenaltyKickCutRepository mPenaltyKickCutRepository, MPenaltyKickCutMapper mPenaltyKickCutMapper) {
        this.mPenaltyKickCutRepository = mPenaltyKickCutRepository;
        this.mPenaltyKickCutMapper = mPenaltyKickCutMapper;
    }

    /**
     * Save a mPenaltyKickCut.
     *
     * @param mPenaltyKickCutDTO the entity to save.
     * @return the persisted entity.
     */
    public MPenaltyKickCutDTO save(MPenaltyKickCutDTO mPenaltyKickCutDTO) {
        log.debug("Request to save MPenaltyKickCut : {}", mPenaltyKickCutDTO);
        MPenaltyKickCut mPenaltyKickCut = mPenaltyKickCutMapper.toEntity(mPenaltyKickCutDTO);
        mPenaltyKickCut = mPenaltyKickCutRepository.save(mPenaltyKickCut);
        return mPenaltyKickCutMapper.toDto(mPenaltyKickCut);
    }

    /**
     * Get all the mPenaltyKickCuts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MPenaltyKickCutDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MPenaltyKickCuts");
        return mPenaltyKickCutRepository.findAll(pageable)
            .map(mPenaltyKickCutMapper::toDto);
    }


    /**
     * Get one mPenaltyKickCut by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MPenaltyKickCutDTO> findOne(Long id) {
        log.debug("Request to get MPenaltyKickCut : {}", id);
        return mPenaltyKickCutRepository.findById(id)
            .map(mPenaltyKickCutMapper::toDto);
    }

    /**
     * Delete the mPenaltyKickCut by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MPenaltyKickCut : {}", id);
        mPenaltyKickCutRepository.deleteById(id);
    }
}
