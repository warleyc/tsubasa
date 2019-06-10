package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MActionRarity;
import io.shm.tsubasa.repository.MActionRarityRepository;
import io.shm.tsubasa.service.dto.MActionRarityDTO;
import io.shm.tsubasa.service.mapper.MActionRarityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MActionRarity}.
 */
@Service
@Transactional
public class MActionRarityService {

    private final Logger log = LoggerFactory.getLogger(MActionRarityService.class);

    private final MActionRarityRepository mActionRarityRepository;

    private final MActionRarityMapper mActionRarityMapper;

    public MActionRarityService(MActionRarityRepository mActionRarityRepository, MActionRarityMapper mActionRarityMapper) {
        this.mActionRarityRepository = mActionRarityRepository;
        this.mActionRarityMapper = mActionRarityMapper;
    }

    /**
     * Save a mActionRarity.
     *
     * @param mActionRarityDTO the entity to save.
     * @return the persisted entity.
     */
    public MActionRarityDTO save(MActionRarityDTO mActionRarityDTO) {
        log.debug("Request to save MActionRarity : {}", mActionRarityDTO);
        MActionRarity mActionRarity = mActionRarityMapper.toEntity(mActionRarityDTO);
        mActionRarity = mActionRarityRepository.save(mActionRarity);
        return mActionRarityMapper.toDto(mActionRarity);
    }

    /**
     * Get all the mActionRarities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MActionRarityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MActionRarities");
        return mActionRarityRepository.findAll(pageable)
            .map(mActionRarityMapper::toDto);
    }


    /**
     * Get one mActionRarity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MActionRarityDTO> findOne(Long id) {
        log.debug("Request to get MActionRarity : {}", id);
        return mActionRarityRepository.findById(id)
            .map(mActionRarityMapper::toDto);
    }

    /**
     * Delete the mActionRarity by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MActionRarity : {}", id);
        mActionRarityRepository.deleteById(id);
    }
}
