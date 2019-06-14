package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGachaRenditionTrajectory;
import io.shm.tsubasa.repository.MGachaRenditionTrajectoryRepository;
import io.shm.tsubasa.service.dto.MGachaRenditionTrajectoryDTO;
import io.shm.tsubasa.service.mapper.MGachaRenditionTrajectoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGachaRenditionTrajectory}.
 */
@Service
@Transactional
public class MGachaRenditionTrajectoryService {

    private final Logger log = LoggerFactory.getLogger(MGachaRenditionTrajectoryService.class);

    private final MGachaRenditionTrajectoryRepository mGachaRenditionTrajectoryRepository;

    private final MGachaRenditionTrajectoryMapper mGachaRenditionTrajectoryMapper;

    public MGachaRenditionTrajectoryService(MGachaRenditionTrajectoryRepository mGachaRenditionTrajectoryRepository, MGachaRenditionTrajectoryMapper mGachaRenditionTrajectoryMapper) {
        this.mGachaRenditionTrajectoryRepository = mGachaRenditionTrajectoryRepository;
        this.mGachaRenditionTrajectoryMapper = mGachaRenditionTrajectoryMapper;
    }

    /**
     * Save a mGachaRenditionTrajectory.
     *
     * @param mGachaRenditionTrajectoryDTO the entity to save.
     * @return the persisted entity.
     */
    public MGachaRenditionTrajectoryDTO save(MGachaRenditionTrajectoryDTO mGachaRenditionTrajectoryDTO) {
        log.debug("Request to save MGachaRenditionTrajectory : {}", mGachaRenditionTrajectoryDTO);
        MGachaRenditionTrajectory mGachaRenditionTrajectory = mGachaRenditionTrajectoryMapper.toEntity(mGachaRenditionTrajectoryDTO);
        mGachaRenditionTrajectory = mGachaRenditionTrajectoryRepository.save(mGachaRenditionTrajectory);
        return mGachaRenditionTrajectoryMapper.toDto(mGachaRenditionTrajectory);
    }

    /**
     * Get all the mGachaRenditionTrajectories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGachaRenditionTrajectoryDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGachaRenditionTrajectories");
        return mGachaRenditionTrajectoryRepository.findAll(pageable)
            .map(mGachaRenditionTrajectoryMapper::toDto);
    }


    /**
     * Get one mGachaRenditionTrajectory by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGachaRenditionTrajectoryDTO> findOne(Long id) {
        log.debug("Request to get MGachaRenditionTrajectory : {}", id);
        return mGachaRenditionTrajectoryRepository.findById(id)
            .map(mGachaRenditionTrajectoryMapper::toDto);
    }

    /**
     * Delete the mGachaRenditionTrajectory by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGachaRenditionTrajectory : {}", id);
        mGachaRenditionTrajectoryRepository.deleteById(id);
    }
}
