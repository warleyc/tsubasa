package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTriggerEffectRange;
import io.shm.tsubasa.repository.MTriggerEffectRangeRepository;
import io.shm.tsubasa.service.dto.MTriggerEffectRangeDTO;
import io.shm.tsubasa.service.mapper.MTriggerEffectRangeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTriggerEffectRange}.
 */
@Service
@Transactional
public class MTriggerEffectRangeService {

    private final Logger log = LoggerFactory.getLogger(MTriggerEffectRangeService.class);

    private final MTriggerEffectRangeRepository mTriggerEffectRangeRepository;

    private final MTriggerEffectRangeMapper mTriggerEffectRangeMapper;

    public MTriggerEffectRangeService(MTriggerEffectRangeRepository mTriggerEffectRangeRepository, MTriggerEffectRangeMapper mTriggerEffectRangeMapper) {
        this.mTriggerEffectRangeRepository = mTriggerEffectRangeRepository;
        this.mTriggerEffectRangeMapper = mTriggerEffectRangeMapper;
    }

    /**
     * Save a mTriggerEffectRange.
     *
     * @param mTriggerEffectRangeDTO the entity to save.
     * @return the persisted entity.
     */
    public MTriggerEffectRangeDTO save(MTriggerEffectRangeDTO mTriggerEffectRangeDTO) {
        log.debug("Request to save MTriggerEffectRange : {}", mTriggerEffectRangeDTO);
        MTriggerEffectRange mTriggerEffectRange = mTriggerEffectRangeMapper.toEntity(mTriggerEffectRangeDTO);
        mTriggerEffectRange = mTriggerEffectRangeRepository.save(mTriggerEffectRange);
        return mTriggerEffectRangeMapper.toDto(mTriggerEffectRange);
    }

    /**
     * Get all the mTriggerEffectRanges.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTriggerEffectRangeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTriggerEffectRanges");
        return mTriggerEffectRangeRepository.findAll(pageable)
            .map(mTriggerEffectRangeMapper::toDto);
    }


    /**
     * Get one mTriggerEffectRange by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTriggerEffectRangeDTO> findOne(Long id) {
        log.debug("Request to get MTriggerEffectRange : {}", id);
        return mTriggerEffectRangeRepository.findById(id)
            .map(mTriggerEffectRangeMapper::toDto);
    }

    /**
     * Delete the mTriggerEffectRange by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTriggerEffectRange : {}", id);
        mTriggerEffectRangeRepository.deleteById(id);
    }
}
