package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MLeagueRegulation;
import io.shm.tsubasa.repository.MLeagueRegulationRepository;
import io.shm.tsubasa.service.dto.MLeagueRegulationDTO;
import io.shm.tsubasa.service.mapper.MLeagueRegulationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MLeagueRegulation}.
 */
@Service
@Transactional
public class MLeagueRegulationService {

    private final Logger log = LoggerFactory.getLogger(MLeagueRegulationService.class);

    private final MLeagueRegulationRepository mLeagueRegulationRepository;

    private final MLeagueRegulationMapper mLeagueRegulationMapper;

    public MLeagueRegulationService(MLeagueRegulationRepository mLeagueRegulationRepository, MLeagueRegulationMapper mLeagueRegulationMapper) {
        this.mLeagueRegulationRepository = mLeagueRegulationRepository;
        this.mLeagueRegulationMapper = mLeagueRegulationMapper;
    }

    /**
     * Save a mLeagueRegulation.
     *
     * @param mLeagueRegulationDTO the entity to save.
     * @return the persisted entity.
     */
    public MLeagueRegulationDTO save(MLeagueRegulationDTO mLeagueRegulationDTO) {
        log.debug("Request to save MLeagueRegulation : {}", mLeagueRegulationDTO);
        MLeagueRegulation mLeagueRegulation = mLeagueRegulationMapper.toEntity(mLeagueRegulationDTO);
        mLeagueRegulation = mLeagueRegulationRepository.save(mLeagueRegulation);
        return mLeagueRegulationMapper.toDto(mLeagueRegulation);
    }

    /**
     * Get all the mLeagueRegulations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MLeagueRegulationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MLeagueRegulations");
        return mLeagueRegulationRepository.findAll(pageable)
            .map(mLeagueRegulationMapper::toDto);
    }


    /**
     * Get one mLeagueRegulation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MLeagueRegulationDTO> findOne(Long id) {
        log.debug("Request to get MLeagueRegulation : {}", id);
        return mLeagueRegulationRepository.findById(id)
            .map(mLeagueRegulationMapper::toDto);
    }

    /**
     * Delete the mLeagueRegulation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MLeagueRegulation : {}", id);
        mLeagueRegulationRepository.deleteById(id);
    }
}
