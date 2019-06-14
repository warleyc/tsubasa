package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTimeattackQuestWorld;
import io.shm.tsubasa.repository.MTimeattackQuestWorldRepository;
import io.shm.tsubasa.service.dto.MTimeattackQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MTimeattackQuestWorldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTimeattackQuestWorld}.
 */
@Service
@Transactional
public class MTimeattackQuestWorldService {

    private final Logger log = LoggerFactory.getLogger(MTimeattackQuestWorldService.class);

    private final MTimeattackQuestWorldRepository mTimeattackQuestWorldRepository;

    private final MTimeattackQuestWorldMapper mTimeattackQuestWorldMapper;

    public MTimeattackQuestWorldService(MTimeattackQuestWorldRepository mTimeattackQuestWorldRepository, MTimeattackQuestWorldMapper mTimeattackQuestWorldMapper) {
        this.mTimeattackQuestWorldRepository = mTimeattackQuestWorldRepository;
        this.mTimeattackQuestWorldMapper = mTimeattackQuestWorldMapper;
    }

    /**
     * Save a mTimeattackQuestWorld.
     *
     * @param mTimeattackQuestWorldDTO the entity to save.
     * @return the persisted entity.
     */
    public MTimeattackQuestWorldDTO save(MTimeattackQuestWorldDTO mTimeattackQuestWorldDTO) {
        log.debug("Request to save MTimeattackQuestWorld : {}", mTimeattackQuestWorldDTO);
        MTimeattackQuestWorld mTimeattackQuestWorld = mTimeattackQuestWorldMapper.toEntity(mTimeattackQuestWorldDTO);
        mTimeattackQuestWorld = mTimeattackQuestWorldRepository.save(mTimeattackQuestWorld);
        return mTimeattackQuestWorldMapper.toDto(mTimeattackQuestWorld);
    }

    /**
     * Get all the mTimeattackQuestWorlds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTimeattackQuestWorldDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTimeattackQuestWorlds");
        return mTimeattackQuestWorldRepository.findAll(pageable)
            .map(mTimeattackQuestWorldMapper::toDto);
    }


    /**
     * Get one mTimeattackQuestWorld by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTimeattackQuestWorldDTO> findOne(Long id) {
        log.debug("Request to get MTimeattackQuestWorld : {}", id);
        return mTimeattackQuestWorldRepository.findById(id)
            .map(mTimeattackQuestWorldMapper::toDto);
    }

    /**
     * Delete the mTimeattackQuestWorld by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTimeattackQuestWorld : {}", id);
        mTimeattackQuestWorldRepository.deleteById(id);
    }
}
