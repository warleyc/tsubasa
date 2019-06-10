package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MAdventQuestWorld;
import io.shm.tsubasa.repository.MAdventQuestWorldRepository;
import io.shm.tsubasa.service.dto.MAdventQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MAdventQuestWorldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MAdventQuestWorld}.
 */
@Service
@Transactional
public class MAdventQuestWorldService {

    private final Logger log = LoggerFactory.getLogger(MAdventQuestWorldService.class);

    private final MAdventQuestWorldRepository mAdventQuestWorldRepository;

    private final MAdventQuestWorldMapper mAdventQuestWorldMapper;

    public MAdventQuestWorldService(MAdventQuestWorldRepository mAdventQuestWorldRepository, MAdventQuestWorldMapper mAdventQuestWorldMapper) {
        this.mAdventQuestWorldRepository = mAdventQuestWorldRepository;
        this.mAdventQuestWorldMapper = mAdventQuestWorldMapper;
    }

    /**
     * Save a mAdventQuestWorld.
     *
     * @param mAdventQuestWorldDTO the entity to save.
     * @return the persisted entity.
     */
    public MAdventQuestWorldDTO save(MAdventQuestWorldDTO mAdventQuestWorldDTO) {
        log.debug("Request to save MAdventQuestWorld : {}", mAdventQuestWorldDTO);
        MAdventQuestWorld mAdventQuestWorld = mAdventQuestWorldMapper.toEntity(mAdventQuestWorldDTO);
        mAdventQuestWorld = mAdventQuestWorldRepository.save(mAdventQuestWorld);
        return mAdventQuestWorldMapper.toDto(mAdventQuestWorld);
    }

    /**
     * Get all the mAdventQuestWorlds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MAdventQuestWorldDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MAdventQuestWorlds");
        return mAdventQuestWorldRepository.findAll(pageable)
            .map(mAdventQuestWorldMapper::toDto);
    }


    /**
     * Get one mAdventQuestWorld by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MAdventQuestWorldDTO> findOne(Long id) {
        log.debug("Request to get MAdventQuestWorld : {}", id);
        return mAdventQuestWorldRepository.findById(id)
            .map(mAdventQuestWorldMapper::toDto);
    }

    /**
     * Delete the mAdventQuestWorld by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MAdventQuestWorld : {}", id);
        mAdventQuestWorldRepository.deleteById(id);
    }
}
