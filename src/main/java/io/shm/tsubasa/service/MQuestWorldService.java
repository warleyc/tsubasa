package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MQuestWorld;
import io.shm.tsubasa.repository.MQuestWorldRepository;
import io.shm.tsubasa.service.dto.MQuestWorldDTO;
import io.shm.tsubasa.service.mapper.MQuestWorldMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MQuestWorld}.
 */
@Service
@Transactional
public class MQuestWorldService {

    private final Logger log = LoggerFactory.getLogger(MQuestWorldService.class);

    private final MQuestWorldRepository mQuestWorldRepository;

    private final MQuestWorldMapper mQuestWorldMapper;

    public MQuestWorldService(MQuestWorldRepository mQuestWorldRepository, MQuestWorldMapper mQuestWorldMapper) {
        this.mQuestWorldRepository = mQuestWorldRepository;
        this.mQuestWorldMapper = mQuestWorldMapper;
    }

    /**
     * Save a mQuestWorld.
     *
     * @param mQuestWorldDTO the entity to save.
     * @return the persisted entity.
     */
    public MQuestWorldDTO save(MQuestWorldDTO mQuestWorldDTO) {
        log.debug("Request to save MQuestWorld : {}", mQuestWorldDTO);
        MQuestWorld mQuestWorld = mQuestWorldMapper.toEntity(mQuestWorldDTO);
        mQuestWorld = mQuestWorldRepository.save(mQuestWorld);
        return mQuestWorldMapper.toDto(mQuestWorld);
    }

    /**
     * Get all the mQuestWorlds.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestWorldDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MQuestWorlds");
        return mQuestWorldRepository.findAll(pageable)
            .map(mQuestWorldMapper::toDto);
    }


    /**
     * Get one mQuestWorld by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MQuestWorldDTO> findOne(Long id) {
        log.debug("Request to get MQuestWorld : {}", id);
        return mQuestWorldRepository.findById(id)
            .map(mQuestWorldMapper::toDto);
    }

    /**
     * Delete the mQuestWorld by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MQuestWorld : {}", id);
        mQuestWorldRepository.deleteById(id);
    }
}
