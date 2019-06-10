package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCombinationCutPosition;
import io.shm.tsubasa.repository.MCombinationCutPositionRepository;
import io.shm.tsubasa.service.dto.MCombinationCutPositionDTO;
import io.shm.tsubasa.service.mapper.MCombinationCutPositionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCombinationCutPosition}.
 */
@Service
@Transactional
public class MCombinationCutPositionService {

    private final Logger log = LoggerFactory.getLogger(MCombinationCutPositionService.class);

    private final MCombinationCutPositionRepository mCombinationCutPositionRepository;

    private final MCombinationCutPositionMapper mCombinationCutPositionMapper;

    public MCombinationCutPositionService(MCombinationCutPositionRepository mCombinationCutPositionRepository, MCombinationCutPositionMapper mCombinationCutPositionMapper) {
        this.mCombinationCutPositionRepository = mCombinationCutPositionRepository;
        this.mCombinationCutPositionMapper = mCombinationCutPositionMapper;
    }

    /**
     * Save a mCombinationCutPosition.
     *
     * @param mCombinationCutPositionDTO the entity to save.
     * @return the persisted entity.
     */
    public MCombinationCutPositionDTO save(MCombinationCutPositionDTO mCombinationCutPositionDTO) {
        log.debug("Request to save MCombinationCutPosition : {}", mCombinationCutPositionDTO);
        MCombinationCutPosition mCombinationCutPosition = mCombinationCutPositionMapper.toEntity(mCombinationCutPositionDTO);
        mCombinationCutPosition = mCombinationCutPositionRepository.save(mCombinationCutPosition);
        return mCombinationCutPositionMapper.toDto(mCombinationCutPosition);
    }

    /**
     * Get all the mCombinationCutPositions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCombinationCutPositionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCombinationCutPositions");
        return mCombinationCutPositionRepository.findAll(pageable)
            .map(mCombinationCutPositionMapper::toDto);
    }


    /**
     * Get one mCombinationCutPosition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCombinationCutPositionDTO> findOne(Long id) {
        log.debug("Request to get MCombinationCutPosition : {}", id);
        return mCombinationCutPositionRepository.findById(id)
            .map(mCombinationCutPositionMapper::toDto);
    }

    /**
     * Delete the mCombinationCutPosition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCombinationCutPosition : {}", id);
        mCombinationCutPositionRepository.deleteById(id);
    }
}
