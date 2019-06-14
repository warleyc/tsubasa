package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MPvpRegulation;
import io.shm.tsubasa.repository.MPvpRegulationRepository;
import io.shm.tsubasa.service.dto.MPvpRegulationDTO;
import io.shm.tsubasa.service.mapper.MPvpRegulationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MPvpRegulation}.
 */
@Service
@Transactional
public class MPvpRegulationService {

    private final Logger log = LoggerFactory.getLogger(MPvpRegulationService.class);

    private final MPvpRegulationRepository mPvpRegulationRepository;

    private final MPvpRegulationMapper mPvpRegulationMapper;

    public MPvpRegulationService(MPvpRegulationRepository mPvpRegulationRepository, MPvpRegulationMapper mPvpRegulationMapper) {
        this.mPvpRegulationRepository = mPvpRegulationRepository;
        this.mPvpRegulationMapper = mPvpRegulationMapper;
    }

    /**
     * Save a mPvpRegulation.
     *
     * @param mPvpRegulationDTO the entity to save.
     * @return the persisted entity.
     */
    public MPvpRegulationDTO save(MPvpRegulationDTO mPvpRegulationDTO) {
        log.debug("Request to save MPvpRegulation : {}", mPvpRegulationDTO);
        MPvpRegulation mPvpRegulation = mPvpRegulationMapper.toEntity(mPvpRegulationDTO);
        mPvpRegulation = mPvpRegulationRepository.save(mPvpRegulation);
        return mPvpRegulationMapper.toDto(mPvpRegulation);
    }

    /**
     * Get all the mPvpRegulations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpRegulationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MPvpRegulations");
        return mPvpRegulationRepository.findAll(pageable)
            .map(mPvpRegulationMapper::toDto);
    }


    /**
     * Get one mPvpRegulation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MPvpRegulationDTO> findOne(Long id) {
        log.debug("Request to get MPvpRegulation : {}", id);
        return mPvpRegulationRepository.findById(id)
            .map(mPvpRegulationMapper::toDto);
    }

    /**
     * Delete the mPvpRegulation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MPvpRegulation : {}", id);
        mPvpRegulationRepository.deleteById(id);
    }
}
