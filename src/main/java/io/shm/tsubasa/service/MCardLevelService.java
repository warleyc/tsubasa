package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCardLevel;
import io.shm.tsubasa.repository.MCardLevelRepository;
import io.shm.tsubasa.service.dto.MCardLevelDTO;
import io.shm.tsubasa.service.mapper.MCardLevelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCardLevel}.
 */
@Service
@Transactional
public class MCardLevelService {

    private final Logger log = LoggerFactory.getLogger(MCardLevelService.class);

    private final MCardLevelRepository mCardLevelRepository;

    private final MCardLevelMapper mCardLevelMapper;

    public MCardLevelService(MCardLevelRepository mCardLevelRepository, MCardLevelMapper mCardLevelMapper) {
        this.mCardLevelRepository = mCardLevelRepository;
        this.mCardLevelMapper = mCardLevelMapper;
    }

    /**
     * Save a mCardLevel.
     *
     * @param mCardLevelDTO the entity to save.
     * @return the persisted entity.
     */
    public MCardLevelDTO save(MCardLevelDTO mCardLevelDTO) {
        log.debug("Request to save MCardLevel : {}", mCardLevelDTO);
        MCardLevel mCardLevel = mCardLevelMapper.toEntity(mCardLevelDTO);
        mCardLevel = mCardLevelRepository.save(mCardLevel);
        return mCardLevelMapper.toDto(mCardLevel);
    }

    /**
     * Get all the mCardLevels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCardLevelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCardLevels");
        return mCardLevelRepository.findAll(pageable)
            .map(mCardLevelMapper::toDto);
    }


    /**
     * Get one mCardLevel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCardLevelDTO> findOne(Long id) {
        log.debug("Request to get MCardLevel : {}", id);
        return mCardLevelRepository.findById(id)
            .map(mCardLevelMapper::toDto);
    }

    /**
     * Delete the mCardLevel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCardLevel : {}", id);
        mCardLevelRepository.deleteById(id);
    }
}
