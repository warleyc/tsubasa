package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDeckCondition;
import io.shm.tsubasa.repository.MDeckConditionRepository;
import io.shm.tsubasa.service.dto.MDeckConditionDTO;
import io.shm.tsubasa.service.mapper.MDeckConditionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDeckCondition}.
 */
@Service
@Transactional
public class MDeckConditionService {

    private final Logger log = LoggerFactory.getLogger(MDeckConditionService.class);

    private final MDeckConditionRepository mDeckConditionRepository;

    private final MDeckConditionMapper mDeckConditionMapper;

    public MDeckConditionService(MDeckConditionRepository mDeckConditionRepository, MDeckConditionMapper mDeckConditionMapper) {
        this.mDeckConditionRepository = mDeckConditionRepository;
        this.mDeckConditionMapper = mDeckConditionMapper;
    }

    /**
     * Save a mDeckCondition.
     *
     * @param mDeckConditionDTO the entity to save.
     * @return the persisted entity.
     */
    public MDeckConditionDTO save(MDeckConditionDTO mDeckConditionDTO) {
        log.debug("Request to save MDeckCondition : {}", mDeckConditionDTO);
        MDeckCondition mDeckCondition = mDeckConditionMapper.toEntity(mDeckConditionDTO);
        mDeckCondition = mDeckConditionRepository.save(mDeckCondition);
        return mDeckConditionMapper.toDto(mDeckCondition);
    }

    /**
     * Get all the mDeckConditions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDeckConditionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDeckConditions");
        return mDeckConditionRepository.findAll(pageable)
            .map(mDeckConditionMapper::toDto);
    }


    /**
     * Get one mDeckCondition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDeckConditionDTO> findOne(Long id) {
        log.debug("Request to get MDeckCondition : {}", id);
        return mDeckConditionRepository.findById(id)
            .map(mDeckConditionMapper::toDto);
    }

    /**
     * Delete the mDeckCondition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDeckCondition : {}", id);
        mDeckConditionRepository.deleteById(id);
    }
}
