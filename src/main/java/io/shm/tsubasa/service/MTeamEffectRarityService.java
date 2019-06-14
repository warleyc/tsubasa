package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTeamEffectRarity;
import io.shm.tsubasa.repository.MTeamEffectRarityRepository;
import io.shm.tsubasa.service.dto.MTeamEffectRarityDTO;
import io.shm.tsubasa.service.mapper.MTeamEffectRarityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTeamEffectRarity}.
 */
@Service
@Transactional
public class MTeamEffectRarityService {

    private final Logger log = LoggerFactory.getLogger(MTeamEffectRarityService.class);

    private final MTeamEffectRarityRepository mTeamEffectRarityRepository;

    private final MTeamEffectRarityMapper mTeamEffectRarityMapper;

    public MTeamEffectRarityService(MTeamEffectRarityRepository mTeamEffectRarityRepository, MTeamEffectRarityMapper mTeamEffectRarityMapper) {
        this.mTeamEffectRarityRepository = mTeamEffectRarityRepository;
        this.mTeamEffectRarityMapper = mTeamEffectRarityMapper;
    }

    /**
     * Save a mTeamEffectRarity.
     *
     * @param mTeamEffectRarityDTO the entity to save.
     * @return the persisted entity.
     */
    public MTeamEffectRarityDTO save(MTeamEffectRarityDTO mTeamEffectRarityDTO) {
        log.debug("Request to save MTeamEffectRarity : {}", mTeamEffectRarityDTO);
        MTeamEffectRarity mTeamEffectRarity = mTeamEffectRarityMapper.toEntity(mTeamEffectRarityDTO);
        mTeamEffectRarity = mTeamEffectRarityRepository.save(mTeamEffectRarity);
        return mTeamEffectRarityMapper.toDto(mTeamEffectRarity);
    }

    /**
     * Get all the mTeamEffectRarities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTeamEffectRarityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTeamEffectRarities");
        return mTeamEffectRarityRepository.findAll(pageable)
            .map(mTeamEffectRarityMapper::toDto);
    }


    /**
     * Get one mTeamEffectRarity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTeamEffectRarityDTO> findOne(Long id) {
        log.debug("Request to get MTeamEffectRarity : {}", id);
        return mTeamEffectRarityRepository.findById(id)
            .map(mTeamEffectRarityMapper::toDto);
    }

    /**
     * Delete the mTeamEffectRarity by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTeamEffectRarity : {}", id);
        mTeamEffectRarityRepository.deleteById(id);
    }
}
