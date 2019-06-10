package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MActionLevel;
import io.shm.tsubasa.repository.MActionLevelRepository;
import io.shm.tsubasa.service.dto.MActionLevelDTO;
import io.shm.tsubasa.service.mapper.MActionLevelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MActionLevel}.
 */
@Service
@Transactional
public class MActionLevelService {

    private final Logger log = LoggerFactory.getLogger(MActionLevelService.class);

    private final MActionLevelRepository mActionLevelRepository;

    private final MActionLevelMapper mActionLevelMapper;

    public MActionLevelService(MActionLevelRepository mActionLevelRepository, MActionLevelMapper mActionLevelMapper) {
        this.mActionLevelRepository = mActionLevelRepository;
        this.mActionLevelMapper = mActionLevelMapper;
    }

    /**
     * Save a mActionLevel.
     *
     * @param mActionLevelDTO the entity to save.
     * @return the persisted entity.
     */
    public MActionLevelDTO save(MActionLevelDTO mActionLevelDTO) {
        log.debug("Request to save MActionLevel : {}", mActionLevelDTO);
        MActionLevel mActionLevel = mActionLevelMapper.toEntity(mActionLevelDTO);
        mActionLevel = mActionLevelRepository.save(mActionLevel);
        return mActionLevelMapper.toDto(mActionLevel);
    }

    /**
     * Get all the mActionLevels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MActionLevelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MActionLevels");
        return mActionLevelRepository.findAll(pageable)
            .map(mActionLevelMapper::toDto);
    }


    /**
     * Get one mActionLevel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MActionLevelDTO> findOne(Long id) {
        log.debug("Request to get MActionLevel : {}", id);
        return mActionLevelRepository.findById(id)
            .map(mActionLevelMapper::toDto);
    }

    /**
     * Delete the mActionLevel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MActionLevel : {}", id);
        mActionLevelRepository.deleteById(id);
    }
}
