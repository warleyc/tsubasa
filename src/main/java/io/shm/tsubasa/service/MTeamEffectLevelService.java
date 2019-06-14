package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTeamEffectLevel;
import io.shm.tsubasa.repository.MTeamEffectLevelRepository;
import io.shm.tsubasa.service.dto.MTeamEffectLevelDTO;
import io.shm.tsubasa.service.mapper.MTeamEffectLevelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTeamEffectLevel}.
 */
@Service
@Transactional
public class MTeamEffectLevelService {

    private final Logger log = LoggerFactory.getLogger(MTeamEffectLevelService.class);

    private final MTeamEffectLevelRepository mTeamEffectLevelRepository;

    private final MTeamEffectLevelMapper mTeamEffectLevelMapper;

    public MTeamEffectLevelService(MTeamEffectLevelRepository mTeamEffectLevelRepository, MTeamEffectLevelMapper mTeamEffectLevelMapper) {
        this.mTeamEffectLevelRepository = mTeamEffectLevelRepository;
        this.mTeamEffectLevelMapper = mTeamEffectLevelMapper;
    }

    /**
     * Save a mTeamEffectLevel.
     *
     * @param mTeamEffectLevelDTO the entity to save.
     * @return the persisted entity.
     */
    public MTeamEffectLevelDTO save(MTeamEffectLevelDTO mTeamEffectLevelDTO) {
        log.debug("Request to save MTeamEffectLevel : {}", mTeamEffectLevelDTO);
        MTeamEffectLevel mTeamEffectLevel = mTeamEffectLevelMapper.toEntity(mTeamEffectLevelDTO);
        mTeamEffectLevel = mTeamEffectLevelRepository.save(mTeamEffectLevel);
        return mTeamEffectLevelMapper.toDto(mTeamEffectLevel);
    }

    /**
     * Get all the mTeamEffectLevels.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTeamEffectLevelDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTeamEffectLevels");
        return mTeamEffectLevelRepository.findAll(pageable)
            .map(mTeamEffectLevelMapper::toDto);
    }


    /**
     * Get one mTeamEffectLevel by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTeamEffectLevelDTO> findOne(Long id) {
        log.debug("Request to get MTeamEffectLevel : {}", id);
        return mTeamEffectLevelRepository.findById(id)
            .map(mTeamEffectLevelMapper::toDto);
    }

    /**
     * Delete the mTeamEffectLevel by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTeamEffectLevel : {}", id);
        mTeamEffectLevelRepository.deleteById(id);
    }
}
