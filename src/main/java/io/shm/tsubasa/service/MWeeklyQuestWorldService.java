package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MWeeklyQuestWorld;
import io.shm.tsubasa.repository.MWeeklyQuestWorldRepository;
import io.shm.tsubasa.service.dto.MWeeklyQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MWeeklyQuestWorldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MWeeklyQuestWorld}.
 */
@Service
@Transactional
public class MWeeklyQuestWorldService {

    private final Logger log = LoggerFactory.getLogger(MWeeklyQuestWorldService.class);

    private final MWeeklyQuestWorldRepository mWeeklyQuestWorldRepository;

    private final MWeeklyQuestWorldMapper mWeeklyQuestWorldMapper;

    public MWeeklyQuestWorldService(MWeeklyQuestWorldRepository mWeeklyQuestWorldRepository, MWeeklyQuestWorldMapper mWeeklyQuestWorldMapper) {
        this.mWeeklyQuestWorldRepository = mWeeklyQuestWorldRepository;
        this.mWeeklyQuestWorldMapper = mWeeklyQuestWorldMapper;
    }

    /**
     * Save a mWeeklyQuestWorld.
     *
     * @param mWeeklyQuestWorldDTO the entity to save.
     * @return the persisted entity.
     */
    public MWeeklyQuestWorldDTO save(MWeeklyQuestWorldDTO mWeeklyQuestWorldDTO) {
        log.debug("Request to save MWeeklyQuestWorld : {}", mWeeklyQuestWorldDTO);
        MWeeklyQuestWorld mWeeklyQuestWorld = mWeeklyQuestWorldMapper.toEntity(mWeeklyQuestWorldDTO);
        mWeeklyQuestWorld = mWeeklyQuestWorldRepository.save(mWeeklyQuestWorld);
        return mWeeklyQuestWorldMapper.toDto(mWeeklyQuestWorld);
    }

    /**
     * Get all the mWeeklyQuestWorlds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MWeeklyQuestWorldDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MWeeklyQuestWorlds");
        return mWeeklyQuestWorldRepository.findAll(pageable)
            .map(mWeeklyQuestWorldMapper::toDto);
    }


    /**
     * Get one mWeeklyQuestWorld by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MWeeklyQuestWorldDTO> findOne(Long id) {
        log.debug("Request to get MWeeklyQuestWorld : {}", id);
        return mWeeklyQuestWorldRepository.findById(id)
            .map(mWeeklyQuestWorldMapper::toDto);
    }

    /**
     * Delete the mWeeklyQuestWorld by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MWeeklyQuestWorld : {}", id);
        mWeeklyQuestWorldRepository.deleteById(id);
    }
}
