package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MPassiveEffectRange;
import io.shm.tsubasa.repository.MPassiveEffectRangeRepository;
import io.shm.tsubasa.service.dto.MPassiveEffectRangeDTO;
import io.shm.tsubasa.service.mapper.MPassiveEffectRangeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MPassiveEffectRange}.
 */
@Service
@Transactional
public class MPassiveEffectRangeService {

    private final Logger log = LoggerFactory.getLogger(MPassiveEffectRangeService.class);

    private final MPassiveEffectRangeRepository mPassiveEffectRangeRepository;

    private final MPassiveEffectRangeMapper mPassiveEffectRangeMapper;

    public MPassiveEffectRangeService(MPassiveEffectRangeRepository mPassiveEffectRangeRepository, MPassiveEffectRangeMapper mPassiveEffectRangeMapper) {
        this.mPassiveEffectRangeRepository = mPassiveEffectRangeRepository;
        this.mPassiveEffectRangeMapper = mPassiveEffectRangeMapper;
    }

    /**
     * Save a mPassiveEffectRange.
     *
     * @param mPassiveEffectRangeDTO the entity to save.
     * @return the persisted entity.
     */
    public MPassiveEffectRangeDTO save(MPassiveEffectRangeDTO mPassiveEffectRangeDTO) {
        log.debug("Request to save MPassiveEffectRange : {}", mPassiveEffectRangeDTO);
        MPassiveEffectRange mPassiveEffectRange = mPassiveEffectRangeMapper.toEntity(mPassiveEffectRangeDTO);
        mPassiveEffectRange = mPassiveEffectRangeRepository.save(mPassiveEffectRange);
        return mPassiveEffectRangeMapper.toDto(mPassiveEffectRange);
    }

    /**
     * Get all the mPassiveEffectRanges.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MPassiveEffectRangeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MPassiveEffectRanges");
        return mPassiveEffectRangeRepository.findAll(pageable)
            .map(mPassiveEffectRangeMapper::toDto);
    }


    /**
     * Get one mPassiveEffectRange by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MPassiveEffectRangeDTO> findOne(Long id) {
        log.debug("Request to get MPassiveEffectRange : {}", id);
        return mPassiveEffectRangeRepository.findById(id)
            .map(mPassiveEffectRangeMapper::toDto);
    }

    /**
     * Delete the mPassiveEffectRange by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MPassiveEffectRange : {}", id);
        mPassiveEffectRangeRepository.deleteById(id);
    }
}
