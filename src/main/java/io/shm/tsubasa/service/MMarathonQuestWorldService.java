package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMarathonQuestWorld;
import io.shm.tsubasa.repository.MMarathonQuestWorldRepository;
import io.shm.tsubasa.service.dto.MMarathonQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MMarathonQuestWorldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMarathonQuestWorld}.
 */
@Service
@Transactional
public class MMarathonQuestWorldService {

    private final Logger log = LoggerFactory.getLogger(MMarathonQuestWorldService.class);

    private final MMarathonQuestWorldRepository mMarathonQuestWorldRepository;

    private final MMarathonQuestWorldMapper mMarathonQuestWorldMapper;

    public MMarathonQuestWorldService(MMarathonQuestWorldRepository mMarathonQuestWorldRepository, MMarathonQuestWorldMapper mMarathonQuestWorldMapper) {
        this.mMarathonQuestWorldRepository = mMarathonQuestWorldRepository;
        this.mMarathonQuestWorldMapper = mMarathonQuestWorldMapper;
    }

    /**
     * Save a mMarathonQuestWorld.
     *
     * @param mMarathonQuestWorldDTO the entity to save.
     * @return the persisted entity.
     */
    public MMarathonQuestWorldDTO save(MMarathonQuestWorldDTO mMarathonQuestWorldDTO) {
        log.debug("Request to save MMarathonQuestWorld : {}", mMarathonQuestWorldDTO);
        MMarathonQuestWorld mMarathonQuestWorld = mMarathonQuestWorldMapper.toEntity(mMarathonQuestWorldDTO);
        mMarathonQuestWorld = mMarathonQuestWorldRepository.save(mMarathonQuestWorld);
        return mMarathonQuestWorldMapper.toDto(mMarathonQuestWorld);
    }

    /**
     * Get all the mMarathonQuestWorlds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonQuestWorldDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMarathonQuestWorlds");
        return mMarathonQuestWorldRepository.findAll(pageable)
            .map(mMarathonQuestWorldMapper::toDto);
    }


    /**
     * Get one mMarathonQuestWorld by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMarathonQuestWorldDTO> findOne(Long id) {
        log.debug("Request to get MMarathonQuestWorld : {}", id);
        return mMarathonQuestWorldRepository.findById(id)
            .map(mMarathonQuestWorldMapper::toDto);
    }

    /**
     * Delete the mMarathonQuestWorld by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMarathonQuestWorld : {}", id);
        mMarathonQuestWorldRepository.deleteById(id);
    }
}
