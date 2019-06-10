package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDeckRarityConditionDescription;
import io.shm.tsubasa.repository.MDeckRarityConditionDescriptionRepository;
import io.shm.tsubasa.service.dto.MDeckRarityConditionDescriptionDTO;
import io.shm.tsubasa.service.mapper.MDeckRarityConditionDescriptionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDeckRarityConditionDescription}.
 */
@Service
@Transactional
public class MDeckRarityConditionDescriptionService {

    private final Logger log = LoggerFactory.getLogger(MDeckRarityConditionDescriptionService.class);

    private final MDeckRarityConditionDescriptionRepository mDeckRarityConditionDescriptionRepository;

    private final MDeckRarityConditionDescriptionMapper mDeckRarityConditionDescriptionMapper;

    public MDeckRarityConditionDescriptionService(MDeckRarityConditionDescriptionRepository mDeckRarityConditionDescriptionRepository, MDeckRarityConditionDescriptionMapper mDeckRarityConditionDescriptionMapper) {
        this.mDeckRarityConditionDescriptionRepository = mDeckRarityConditionDescriptionRepository;
        this.mDeckRarityConditionDescriptionMapper = mDeckRarityConditionDescriptionMapper;
    }

    /**
     * Save a mDeckRarityConditionDescription.
     *
     * @param mDeckRarityConditionDescriptionDTO the entity to save.
     * @return the persisted entity.
     */
    public MDeckRarityConditionDescriptionDTO save(MDeckRarityConditionDescriptionDTO mDeckRarityConditionDescriptionDTO) {
        log.debug("Request to save MDeckRarityConditionDescription : {}", mDeckRarityConditionDescriptionDTO);
        MDeckRarityConditionDescription mDeckRarityConditionDescription = mDeckRarityConditionDescriptionMapper.toEntity(mDeckRarityConditionDescriptionDTO);
        mDeckRarityConditionDescription = mDeckRarityConditionDescriptionRepository.save(mDeckRarityConditionDescription);
        return mDeckRarityConditionDescriptionMapper.toDto(mDeckRarityConditionDescription);
    }

    /**
     * Get all the mDeckRarityConditionDescriptions.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDeckRarityConditionDescriptionDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDeckRarityConditionDescriptions");
        return mDeckRarityConditionDescriptionRepository.findAll(pageable)
            .map(mDeckRarityConditionDescriptionMapper::toDto);
    }


    /**
     * Get one mDeckRarityConditionDescription by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDeckRarityConditionDescriptionDTO> findOne(Long id) {
        log.debug("Request to get MDeckRarityConditionDescription : {}", id);
        return mDeckRarityConditionDescriptionRepository.findById(id)
            .map(mDeckRarityConditionDescriptionMapper::toDto);
    }

    /**
     * Delete the mDeckRarityConditionDescription by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDeckRarityConditionDescription : {}", id);
        mDeckRarityConditionDescriptionRepository.deleteById(id);
    }
}
