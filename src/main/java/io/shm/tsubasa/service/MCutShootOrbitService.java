package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCutShootOrbit;
import io.shm.tsubasa.repository.MCutShootOrbitRepository;
import io.shm.tsubasa.service.dto.MCutShootOrbitDTO;
import io.shm.tsubasa.service.mapper.MCutShootOrbitMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCutShootOrbit}.
 */
@Service
@Transactional
public class MCutShootOrbitService {

    private final Logger log = LoggerFactory.getLogger(MCutShootOrbitService.class);

    private final MCutShootOrbitRepository mCutShootOrbitRepository;

    private final MCutShootOrbitMapper mCutShootOrbitMapper;

    public MCutShootOrbitService(MCutShootOrbitRepository mCutShootOrbitRepository, MCutShootOrbitMapper mCutShootOrbitMapper) {
        this.mCutShootOrbitRepository = mCutShootOrbitRepository;
        this.mCutShootOrbitMapper = mCutShootOrbitMapper;
    }

    /**
     * Save a mCutShootOrbit.
     *
     * @param mCutShootOrbitDTO the entity to save.
     * @return the persisted entity.
     */
    public MCutShootOrbitDTO save(MCutShootOrbitDTO mCutShootOrbitDTO) {
        log.debug("Request to save MCutShootOrbit : {}", mCutShootOrbitDTO);
        MCutShootOrbit mCutShootOrbit = mCutShootOrbitMapper.toEntity(mCutShootOrbitDTO);
        mCutShootOrbit = mCutShootOrbitRepository.save(mCutShootOrbit);
        return mCutShootOrbitMapper.toDto(mCutShootOrbit);
    }

    /**
     * Get all the mCutShootOrbits.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCutShootOrbitDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCutShootOrbits");
        return mCutShootOrbitRepository.findAll(pageable)
            .map(mCutShootOrbitMapper::toDto);
    }


    /**
     * Get one mCutShootOrbit by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCutShootOrbitDTO> findOne(Long id) {
        log.debug("Request to get MCutShootOrbit : {}", id);
        return mCutShootOrbitRepository.findById(id)
            .map(mCutShootOrbitMapper::toDto);
    }

    /**
     * Delete the mCutShootOrbit by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCutShootOrbit : {}", id);
        mCutShootOrbitRepository.deleteById(id);
    }
}
