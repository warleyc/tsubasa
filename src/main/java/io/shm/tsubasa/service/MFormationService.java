package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MFormation;
import io.shm.tsubasa.repository.MFormationRepository;
import io.shm.tsubasa.service.dto.MFormationDTO;
import io.shm.tsubasa.service.mapper.MFormationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MFormation}.
 */
@Service
@Transactional
public class MFormationService {

    private final Logger log = LoggerFactory.getLogger(MFormationService.class);

    private final MFormationRepository mFormationRepository;

    private final MFormationMapper mFormationMapper;

    public MFormationService(MFormationRepository mFormationRepository, MFormationMapper mFormationMapper) {
        this.mFormationRepository = mFormationRepository;
        this.mFormationMapper = mFormationMapper;
    }

    /**
     * Save a mFormation.
     *
     * @param mFormationDTO the entity to save.
     * @return the persisted entity.
     */
    public MFormationDTO save(MFormationDTO mFormationDTO) {
        log.debug("Request to save MFormation : {}", mFormationDTO);
        MFormation mFormation = mFormationMapper.toEntity(mFormationDTO);
        mFormation = mFormationRepository.save(mFormation);
        return mFormationMapper.toDto(mFormation);
    }

    /**
     * Get all the mFormations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MFormationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MFormations");
        return mFormationRepository.findAll(pageable)
            .map(mFormationMapper::toDto);
    }


    /**
     * Get one mFormation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MFormationDTO> findOne(Long id) {
        log.debug("Request to get MFormation : {}", id);
        return mFormationRepository.findById(id)
            .map(mFormationMapper::toDto);
    }

    /**
     * Delete the mFormation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MFormation : {}", id);
        mFormationRepository.deleteById(id);
    }
}
