package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MEncountersCommandCompatibility;
import io.shm.tsubasa.repository.MEncountersCommandCompatibilityRepository;
import io.shm.tsubasa.service.dto.MEncountersCommandCompatibilityDTO;
import io.shm.tsubasa.service.mapper.MEncountersCommandCompatibilityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MEncountersCommandCompatibility}.
 */
@Service
@Transactional
public class MEncountersCommandCompatibilityService {

    private final Logger log = LoggerFactory.getLogger(MEncountersCommandCompatibilityService.class);

    private final MEncountersCommandCompatibilityRepository mEncountersCommandCompatibilityRepository;

    private final MEncountersCommandCompatibilityMapper mEncountersCommandCompatibilityMapper;

    public MEncountersCommandCompatibilityService(MEncountersCommandCompatibilityRepository mEncountersCommandCompatibilityRepository, MEncountersCommandCompatibilityMapper mEncountersCommandCompatibilityMapper) {
        this.mEncountersCommandCompatibilityRepository = mEncountersCommandCompatibilityRepository;
        this.mEncountersCommandCompatibilityMapper = mEncountersCommandCompatibilityMapper;
    }

    /**
     * Save a mEncountersCommandCompatibility.
     *
     * @param mEncountersCommandCompatibilityDTO the entity to save.
     * @return the persisted entity.
     */
    public MEncountersCommandCompatibilityDTO save(MEncountersCommandCompatibilityDTO mEncountersCommandCompatibilityDTO) {
        log.debug("Request to save MEncountersCommandCompatibility : {}", mEncountersCommandCompatibilityDTO);
        MEncountersCommandCompatibility mEncountersCommandCompatibility = mEncountersCommandCompatibilityMapper.toEntity(mEncountersCommandCompatibilityDTO);
        mEncountersCommandCompatibility = mEncountersCommandCompatibilityRepository.save(mEncountersCommandCompatibility);
        return mEncountersCommandCompatibilityMapper.toDto(mEncountersCommandCompatibility);
    }

    /**
     * Get all the mEncountersCommandCompatibilities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MEncountersCommandCompatibilityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MEncountersCommandCompatibilities");
        return mEncountersCommandCompatibilityRepository.findAll(pageable)
            .map(mEncountersCommandCompatibilityMapper::toDto);
    }


    /**
     * Get one mEncountersCommandCompatibility by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MEncountersCommandCompatibilityDTO> findOne(Long id) {
        log.debug("Request to get MEncountersCommandCompatibility : {}", id);
        return mEncountersCommandCompatibilityRepository.findById(id)
            .map(mEncountersCommandCompatibilityMapper::toDto);
    }

    /**
     * Delete the mEncountersCommandCompatibility by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MEncountersCommandCompatibility : {}", id);
        mEncountersCommandCompatibilityRepository.deleteById(id);
    }
}
