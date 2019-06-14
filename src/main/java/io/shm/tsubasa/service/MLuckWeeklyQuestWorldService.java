package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MLuckWeeklyQuestWorld;
import io.shm.tsubasa.repository.MLuckWeeklyQuestWorldRepository;
import io.shm.tsubasa.service.dto.MLuckWeeklyQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MLuckWeeklyQuestWorldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MLuckWeeklyQuestWorld}.
 */
@Service
@Transactional
public class MLuckWeeklyQuestWorldService {

    private final Logger log = LoggerFactory.getLogger(MLuckWeeklyQuestWorldService.class);

    private final MLuckWeeklyQuestWorldRepository mLuckWeeklyQuestWorldRepository;

    private final MLuckWeeklyQuestWorldMapper mLuckWeeklyQuestWorldMapper;

    public MLuckWeeklyQuestWorldService(MLuckWeeklyQuestWorldRepository mLuckWeeklyQuestWorldRepository, MLuckWeeklyQuestWorldMapper mLuckWeeklyQuestWorldMapper) {
        this.mLuckWeeklyQuestWorldRepository = mLuckWeeklyQuestWorldRepository;
        this.mLuckWeeklyQuestWorldMapper = mLuckWeeklyQuestWorldMapper;
    }

    /**
     * Save a mLuckWeeklyQuestWorld.
     *
     * @param mLuckWeeklyQuestWorldDTO the entity to save.
     * @return the persisted entity.
     */
    public MLuckWeeklyQuestWorldDTO save(MLuckWeeklyQuestWorldDTO mLuckWeeklyQuestWorldDTO) {
        log.debug("Request to save MLuckWeeklyQuestWorld : {}", mLuckWeeklyQuestWorldDTO);
        MLuckWeeklyQuestWorld mLuckWeeklyQuestWorld = mLuckWeeklyQuestWorldMapper.toEntity(mLuckWeeklyQuestWorldDTO);
        mLuckWeeklyQuestWorld = mLuckWeeklyQuestWorldRepository.save(mLuckWeeklyQuestWorld);
        return mLuckWeeklyQuestWorldMapper.toDto(mLuckWeeklyQuestWorld);
    }

    /**
     * Get all the mLuckWeeklyQuestWorlds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MLuckWeeklyQuestWorldDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MLuckWeeklyQuestWorlds");
        return mLuckWeeklyQuestWorldRepository.findAll(pageable)
            .map(mLuckWeeklyQuestWorldMapper::toDto);
    }


    /**
     * Get one mLuckWeeklyQuestWorld by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MLuckWeeklyQuestWorldDTO> findOne(Long id) {
        log.debug("Request to get MLuckWeeklyQuestWorld : {}", id);
        return mLuckWeeklyQuestWorldRepository.findById(id)
            .map(mLuckWeeklyQuestWorldMapper::toDto);
    }

    /**
     * Delete the mLuckWeeklyQuestWorld by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MLuckWeeklyQuestWorld : {}", id);
        mLuckWeeklyQuestWorldRepository.deleteById(id);
    }
}
