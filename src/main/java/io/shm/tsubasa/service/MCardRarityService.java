package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MCardRarity;
import io.shm.tsubasa.repository.MCardRarityRepository;
import io.shm.tsubasa.service.dto.MCardRarityDTO;
import io.shm.tsubasa.service.mapper.MCardRarityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MCardRarity}.
 */
@Service
@Transactional
public class MCardRarityService {

    private final Logger log = LoggerFactory.getLogger(MCardRarityService.class);

    private final MCardRarityRepository mCardRarityRepository;

    private final MCardRarityMapper mCardRarityMapper;

    public MCardRarityService(MCardRarityRepository mCardRarityRepository, MCardRarityMapper mCardRarityMapper) {
        this.mCardRarityRepository = mCardRarityRepository;
        this.mCardRarityMapper = mCardRarityMapper;
    }

    /**
     * Save a mCardRarity.
     *
     * @param mCardRarityDTO the entity to save.
     * @return the persisted entity.
     */
    public MCardRarityDTO save(MCardRarityDTO mCardRarityDTO) {
        log.debug("Request to save MCardRarity : {}", mCardRarityDTO);
        MCardRarity mCardRarity = mCardRarityMapper.toEntity(mCardRarityDTO);
        mCardRarity = mCardRarityRepository.save(mCardRarity);
        return mCardRarityMapper.toDto(mCardRarity);
    }

    /**
     * Get all the mCardRarities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MCardRarityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MCardRarities");
        return mCardRarityRepository.findAll(pageable)
            .map(mCardRarityMapper::toDto);
    }


    /**
     * Get one mCardRarity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MCardRarityDTO> findOne(Long id) {
        log.debug("Request to get MCardRarity : {}", id);
        return mCardRarityRepository.findById(id)
            .map(mCardRarityMapper::toDto);
    }

    /**
     * Delete the mCardRarity by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MCardRarity : {}", id);
        mCardRarityRepository.deleteById(id);
    }
}
