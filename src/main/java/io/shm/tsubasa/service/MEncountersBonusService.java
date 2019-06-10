package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MEncountersBonus;
import io.shm.tsubasa.repository.MEncountersBonusRepository;
import io.shm.tsubasa.service.dto.MEncountersBonusDTO;
import io.shm.tsubasa.service.mapper.MEncountersBonusMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MEncountersBonus}.
 */
@Service
@Transactional
public class MEncountersBonusService {

    private final Logger log = LoggerFactory.getLogger(MEncountersBonusService.class);

    private final MEncountersBonusRepository mEncountersBonusRepository;

    private final MEncountersBonusMapper mEncountersBonusMapper;

    public MEncountersBonusService(MEncountersBonusRepository mEncountersBonusRepository, MEncountersBonusMapper mEncountersBonusMapper) {
        this.mEncountersBonusRepository = mEncountersBonusRepository;
        this.mEncountersBonusMapper = mEncountersBonusMapper;
    }

    /**
     * Save a mEncountersBonus.
     *
     * @param mEncountersBonusDTO the entity to save.
     * @return the persisted entity.
     */
    public MEncountersBonusDTO save(MEncountersBonusDTO mEncountersBonusDTO) {
        log.debug("Request to save MEncountersBonus : {}", mEncountersBonusDTO);
        MEncountersBonus mEncountersBonus = mEncountersBonusMapper.toEntity(mEncountersBonusDTO);
        mEncountersBonus = mEncountersBonusRepository.save(mEncountersBonus);
        return mEncountersBonusMapper.toDto(mEncountersBonus);
    }

    /**
     * Get all the mEncountersBonuses.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MEncountersBonusDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MEncountersBonuses");
        return mEncountersBonusRepository.findAll(pageable)
            .map(mEncountersBonusMapper::toDto);
    }


    /**
     * Get one mEncountersBonus by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MEncountersBonusDTO> findOne(Long id) {
        log.debug("Request to get MEncountersBonus : {}", id);
        return mEncountersBonusRepository.findById(id)
            .map(mEncountersBonusMapper::toDto);
    }

    /**
     * Delete the mEncountersBonus by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MEncountersBonus : {}", id);
        mEncountersBonusRepository.deleteById(id);
    }
}
