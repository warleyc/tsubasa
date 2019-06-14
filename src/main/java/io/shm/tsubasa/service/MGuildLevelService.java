package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGuildLevel;
import io.shm.tsubasa.repository.MGuildLevelRepository;
import io.shm.tsubasa.service.dto.MGuildLevelDTO;
import io.shm.tsubasa.service.mapper.MGuildLevelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGuildLevel}.
 */
@Service
@Transactional
public class MGuildLevelService {

    private final Logger log = LoggerFactory.getLogger(MGuildLevelService.class);

    private final MGuildLevelRepository mGuildLevelRepository;

    private final MGuildLevelMapper mGuildLevelMapper;

    public MGuildLevelService(MGuildLevelRepository mGuildLevelRepository, MGuildLevelMapper mGuildLevelMapper) {
        this.mGuildLevelRepository = mGuildLevelRepository;
        this.mGuildLevelMapper = mGuildLevelMapper;
    }

    /**
     * Save a mGuildLevel.
     *
     * @param mGuildLevelDTO the entity to save.
     * @return the persisted entity.
     */
    public MGuildLevelDTO save(MGuildLevelDTO mGuildLevelDTO) {
        log.debug("Request to save MGuildLevel : {}", mGuildLevelDTO);
        MGuildLevel mGuildLevel = mGuildLevelMapper.toEntity(mGuildLevelDTO);
        mGuildLevel = mGuildLevelRepository.save(mGuildLevel);
        return mGuildLevelMapper.toDto(mGuildLevel);
    }

    /**
     * Get all the mGuildLevels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuildLevelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGuildLevels");
        return mGuildLevelRepository.findAll(pageable)
            .map(mGuildLevelMapper::toDto);
    }


    /**
     * Get one mGuildLevel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGuildLevelDTO> findOne(Long id) {
        log.debug("Request to get MGuildLevel : {}", id);
        return mGuildLevelRepository.findById(id)
            .map(mGuildLevelMapper::toDto);
    }

    /**
     * Delete the mGuildLevel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGuildLevel : {}", id);
        mGuildLevelRepository.deleteById(id);
    }
}
