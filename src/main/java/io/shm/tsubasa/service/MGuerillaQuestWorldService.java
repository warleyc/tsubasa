package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MGuerillaQuestWorld;
import io.shm.tsubasa.repository.MGuerillaQuestWorldRepository;
import io.shm.tsubasa.service.dto.MGuerillaQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MGuerillaQuestWorldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MGuerillaQuestWorld}.
 */
@Service
@Transactional
public class MGuerillaQuestWorldService {

    private final Logger log = LoggerFactory.getLogger(MGuerillaQuestWorldService.class);

    private final MGuerillaQuestWorldRepository mGuerillaQuestWorldRepository;

    private final MGuerillaQuestWorldMapper mGuerillaQuestWorldMapper;

    public MGuerillaQuestWorldService(MGuerillaQuestWorldRepository mGuerillaQuestWorldRepository, MGuerillaQuestWorldMapper mGuerillaQuestWorldMapper) {
        this.mGuerillaQuestWorldRepository = mGuerillaQuestWorldRepository;
        this.mGuerillaQuestWorldMapper = mGuerillaQuestWorldMapper;
    }

    /**
     * Save a mGuerillaQuestWorld.
     *
     * @param mGuerillaQuestWorldDTO the entity to save.
     * @return the persisted entity.
     */
    public MGuerillaQuestWorldDTO save(MGuerillaQuestWorldDTO mGuerillaQuestWorldDTO) {
        log.debug("Request to save MGuerillaQuestWorld : {}", mGuerillaQuestWorldDTO);
        MGuerillaQuestWorld mGuerillaQuestWorld = mGuerillaQuestWorldMapper.toEntity(mGuerillaQuestWorldDTO);
        mGuerillaQuestWorld = mGuerillaQuestWorldRepository.save(mGuerillaQuestWorld);
        return mGuerillaQuestWorldMapper.toDto(mGuerillaQuestWorld);
    }

    /**
     * Get all the mGuerillaQuestWorlds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MGuerillaQuestWorldDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MGuerillaQuestWorlds");
        return mGuerillaQuestWorldRepository.findAll(pageable)
            .map(mGuerillaQuestWorldMapper::toDto);
    }


    /**
     * Get one mGuerillaQuestWorld by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MGuerillaQuestWorldDTO> findOne(Long id) {
        log.debug("Request to get MGuerillaQuestWorld : {}", id);
        return mGuerillaQuestWorldRepository.findById(id)
            .map(mGuerillaQuestWorldMapper::toDto);
    }

    /**
     * Delete the mGuerillaQuestWorld by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MGuerillaQuestWorld : {}", id);
        mGuerillaQuestWorldRepository.deleteById(id);
    }
}
