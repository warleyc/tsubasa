package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGuildEffectLevel;
import io.shm.tsubasa.repository.MGuildEffectLevelRepository;
import io.shm.tsubasa.service.dto.MGuildEffectLevelDTO;
import io.shm.tsubasa.service.mapper.MGuildEffectLevelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGuildEffectLevel}.
 */
@Service
@Transactional
public class MGuildEffectLevelService {

    private final Logger log = LoggerFactory.getLogger(MGuildEffectLevelService.class);

    private final MGuildEffectLevelRepository mGuildEffectLevelRepository;

    private final MGuildEffectLevelMapper mGuildEffectLevelMapper;

    public MGuildEffectLevelService(MGuildEffectLevelRepository mGuildEffectLevelRepository, MGuildEffectLevelMapper mGuildEffectLevelMapper) {
        this.mGuildEffectLevelRepository = mGuildEffectLevelRepository;
        this.mGuildEffectLevelMapper = mGuildEffectLevelMapper;
    }

    /**
     * Save a mGuildEffectLevel.
     *
     * @param mGuildEffectLevelDTO the entity to save.
     * @return the persisted entity.
     */
    public MGuildEffectLevelDTO save(MGuildEffectLevelDTO mGuildEffectLevelDTO) {
        log.debug("Request to save MGuildEffectLevel : {}", mGuildEffectLevelDTO);
        MGuildEffectLevel mGuildEffectLevel = mGuildEffectLevelMapper.toEntity(mGuildEffectLevelDTO);
        mGuildEffectLevel = mGuildEffectLevelRepository.save(mGuildEffectLevel);
        return mGuildEffectLevelMapper.toDto(mGuildEffectLevel);
    }

    /**
     * Get all the mGuildEffectLevels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildEffectLevelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGuildEffectLevels");
        return mGuildEffectLevelRepository.findAll(pageable)
            .map(mGuildEffectLevelMapper::toDto);
    }


    /**
     * Get one mGuildEffectLevel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGuildEffectLevelDTO> findOne(Long id) {
        log.debug("Request to get MGuildEffectLevel : {}", id);
        return mGuildEffectLevelRepository.findById(id)
            .map(mGuildEffectLevelMapper::toDto);
    }

    /**
     * Delete the mGuildEffectLevel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGuildEffectLevel : {}", id);
        mGuildEffectLevelRepository.deleteById(id);
    }
}
