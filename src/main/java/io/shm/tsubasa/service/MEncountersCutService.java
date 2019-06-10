package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MEncountersCut;
import io.shm.tsubasa.repository.MEncountersCutRepository;
import io.shm.tsubasa.service.dto.MEncountersCutDTO;
import io.shm.tsubasa.service.mapper.MEncountersCutMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MEncountersCut}.
 */
@Service
@Transactional
public class MEncountersCutService {

    private final Logger log = LoggerFactory.getLogger(MEncountersCutService.class);

    private final MEncountersCutRepository mEncountersCutRepository;

    private final MEncountersCutMapper mEncountersCutMapper;

    public MEncountersCutService(MEncountersCutRepository mEncountersCutRepository, MEncountersCutMapper mEncountersCutMapper) {
        this.mEncountersCutRepository = mEncountersCutRepository;
        this.mEncountersCutMapper = mEncountersCutMapper;
    }

    /**
     * Save a mEncountersCut.
     *
     * @param mEncountersCutDTO the entity to save.
     * @return the persisted entity.
     */
    public MEncountersCutDTO save(MEncountersCutDTO mEncountersCutDTO) {
        log.debug("Request to save MEncountersCut : {}", mEncountersCutDTO);
        MEncountersCut mEncountersCut = mEncountersCutMapper.toEntity(mEncountersCutDTO);
        mEncountersCut = mEncountersCutRepository.save(mEncountersCut);
        return mEncountersCutMapper.toDto(mEncountersCut);
    }

    /**
     * Get all the mEncountersCuts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MEncountersCutDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MEncountersCuts");
        return mEncountersCutRepository.findAll(pageable)
            .map(mEncountersCutMapper::toDto);
    }


    /**
     * Get one mEncountersCut by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MEncountersCutDTO> findOne(Long id) {
        log.debug("Request to get MEncountersCut : {}", id);
        return mEncountersCutRepository.findById(id)
            .map(mEncountersCutMapper::toDto);
    }

    /**
     * Delete the mEncountersCut by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MEncountersCut : {}", id);
        mEncountersCutRepository.deleteById(id);
    }
}
