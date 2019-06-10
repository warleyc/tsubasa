package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGachaRenditionBeforeShootCutInPattern;
import io.shm.tsubasa.repository.MGachaRenditionBeforeShootCutInPatternRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionBeforeShootCutInPatternDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionBeforeShootCutInPatternMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGachaRenditionBeforeShootCutInPattern}.
 */
@Service
@Transactional
public class MGachaRenditionBeforeShootCutInPatternService {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionBeforeShootCutInPatternService.class);

    private final MGachaRenditionBeforeShootCutInPatternRepository mGachaRenditionBeforeShootCutInPatternRepository;

    private final MGachaRenditionBeforeShootCutInPatternMapper mGachaRenditionBeforeShootCutInPatternMapper;

    public MGachaRenditionBeforeShootCutInPatternService(MGachaRenditionBeforeShootCutInPatternRepository mGachaRenditionBeforeShootCutInPatternRepository, MGachaRenditionBeforeShootCutInPatternMapper mGachaRenditionBeforeShootCutInPatternMapper) {
        this.mGachaRenditionBeforeShootCutInPatternRepository = mGachaRenditionBeforeShootCutInPatternRepository;
        this.mGachaRenditionBeforeShootCutInPatternMapper = mGachaRenditionBeforeShootCutInPatternMapper;
    }

    /**
     * Save a mGachaRenditionBeforeShootCutInPattern.
     *
     * @param mGachaRenditionBeforeShootCutInPatternDTO the entity to save.
     * @return the persisted entity.
     */
    public MGachaRenditionBeforeShootCutInPatternDTO save(MGachaRenditionBeforeShootCutInPatternDTO mGachaRenditionBeforeShootCutInPatternDTO) {
        log.debug("Request to save MGachaRenditionBeforeShootCutInPattern : {}", mGachaRenditionBeforeShootCutInPatternDTO);
        MGachaRenditionBeforeShootCutInPattern mGachaRenditionBeforeShootCutInPattern = mGachaRenditionBeforeShootCutInPatternMapper.toEntity(mGachaRenditionBeforeShootCutInPatternDTO);
        mGachaRenditionBeforeShootCutInPattern = mGachaRenditionBeforeShootCutInPatternRepository.save(mGachaRenditionBeforeShootCutInPattern);
        return mGachaRenditionBeforeShootCutInPatternMapper.toDto(mGachaRenditionBeforeShootCutInPattern);
    }

    /**
     * Get all the mGachaRenditionBeforeShootCutInPatterns.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionBeforeShootCutInPatternDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGachaRenditionBeforeShootCutInPatterns");
        return mGachaRenditionBeforeShootCutInPatternRepository.findAll(pageable)
            .map(mGachaRenditionBeforeShootCutInPatternMapper::toDto);
    }


    /**
     * Get one mGachaRenditionBeforeShootCutInPattern by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGachaRenditionBeforeShootCutInPatternDTO> findOne(Long id) {
        log.debug("Request to get MGachaRenditionBeforeShootCutInPattern : {}", id);
        return mGachaRenditionBeforeShootCutInPatternRepository.findById(id)
            .map(mGachaRenditionBeforeShootCutInPatternMapper::toDto);
    }

    /**
     * Delete the mGachaRenditionBeforeShootCutInPattern by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGachaRenditionBeforeShootCutInPattern : {}", id);
        mGachaRenditionBeforeShootCutInPatternRepository.deleteById(id);
    }
}
