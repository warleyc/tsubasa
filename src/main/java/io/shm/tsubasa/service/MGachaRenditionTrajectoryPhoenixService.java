package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGachaRenditionTrajectoryPhoenix;
import io.shm.tsubasa.repository.MGachaRenditionTrajectoryPhoenixRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryPhoenixDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionTrajectoryPhoenixMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGachaRenditionTrajectoryPhoenix}.
 */
@Service
@Transactional
public class MGachaRenditionTrajectoryPhoenixService {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionTrajectoryPhoenixService.class);

    private final MGachaRenditionTrajectoryPhoenixRepository mGachaRenditionTrajectoryPhoenixRepository;

    private final MGachaRenditionTrajectoryPhoenixMapper mGachaRenditionTrajectoryPhoenixMapper;

    public MGachaRenditionTrajectoryPhoenixService(MGachaRenditionTrajectoryPhoenixRepository mGachaRenditionTrajectoryPhoenixRepository, MGachaRenditionTrajectoryPhoenixMapper mGachaRenditionTrajectoryPhoenixMapper) {
        this.mGachaRenditionTrajectoryPhoenixRepository = mGachaRenditionTrajectoryPhoenixRepository;
        this.mGachaRenditionTrajectoryPhoenixMapper = mGachaRenditionTrajectoryPhoenixMapper;
    }

    /**
     * Save a mGachaRenditionTrajectoryPhoenix.
     *
     * @param mGachaRenditionTrajectoryPhoenixDTO the entity to save.
     * @return the persisted entity.
     */
    public MGachaRenditionTrajectoryPhoenixDTO save(MGachaRenditionTrajectoryPhoenixDTO mGachaRenditionTrajectoryPhoenixDTO) {
        log.debug("Request to save MGachaRenditionTrajectoryPhoenix : {}", mGachaRenditionTrajectoryPhoenixDTO);
        MGachaRenditionTrajectoryPhoenix mGachaRenditionTrajectoryPhoenix = mGachaRenditionTrajectoryPhoenixMapper.toEntity(mGachaRenditionTrajectoryPhoenixDTO);
        mGachaRenditionTrajectoryPhoenix = mGachaRenditionTrajectoryPhoenixRepository.save(mGachaRenditionTrajectoryPhoenix);
        return mGachaRenditionTrajectoryPhoenixMapper.toDto(mGachaRenditionTrajectoryPhoenix);
    }

    /**
     * Get all the mGachaRenditionTrajectoryPhoenixes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionTrajectoryPhoenixDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGachaRenditionTrajectoryPhoenixes");
        return mGachaRenditionTrajectoryPhoenixRepository.findAll(pageable)
            .map(mGachaRenditionTrajectoryPhoenixMapper::toDto);
    }


    /**
     * Get one mGachaRenditionTrajectoryPhoenix by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGachaRenditionTrajectoryPhoenixDTO> findOne(Long id) {
        log.debug("Request to get MGachaRenditionTrajectoryPhoenix : {}", id);
        return mGachaRenditionTrajectoryPhoenixRepository.findById(id)
            .map(mGachaRenditionTrajectoryPhoenixMapper::toDto);
    }

    /**
     * Delete the mGachaRenditionTrajectoryPhoenix by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGachaRenditionTrajectoryPhoenix : {}", id);
        mGachaRenditionTrajectoryPhoenixRepository.deleteById(id);
    }
}
