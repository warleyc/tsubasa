package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMatchFormation;
import io.shm.tsubasa.repository.MMatchFormationRepository;
import io.shm.tsubasa.service.dto.MMatchFormationDTO;
import io.shm.tsubasa.service.mapper.MMatchFormationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMatchFormation}.
 */
@Service
@Transactional
public class MMatchFormationService {

    private final Logger log = LoggerFactory.getLogger(MMatchFormationService.class);

    private final MMatchFormationRepository mMatchFormationRepository;

    private final MMatchFormationMapper mMatchFormationMapper;

    public MMatchFormationService(MMatchFormationRepository mMatchFormationRepository, MMatchFormationMapper mMatchFormationMapper) {
        this.mMatchFormationRepository = mMatchFormationRepository;
        this.mMatchFormationMapper = mMatchFormationMapper;
    }

    /**
     * Save a mMatchFormation.
     *
     * @param mMatchFormationDTO the entity to save.
     * @return the persisted entity.
     */
    public MMatchFormationDTO save(MMatchFormationDTO mMatchFormationDTO) {
        log.debug("Request to save MMatchFormation : {}", mMatchFormationDTO);
        MMatchFormation mMatchFormation = mMatchFormationMapper.toEntity(mMatchFormationDTO);
        mMatchFormation = mMatchFormationRepository.save(mMatchFormation);
        return mMatchFormationMapper.toDto(mMatchFormation);
    }

    /**
     * Get all the mMatchFormations.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMatchFormationDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMatchFormations");
        return mMatchFormationRepository.findAll(pageable)
            .map(mMatchFormationMapper::toDto);
    }


    /**
     * Get one mMatchFormation by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMatchFormationDTO> findOne(Long id) {
        log.debug("Request to get MMatchFormation : {}", id);
        return mMatchFormationRepository.findById(id)
            .map(mMatchFormationMapper::toDto);
    }

    /**
     * Delete the mMatchFormation by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMatchFormation : {}", id);
        mMatchFormationRepository.deleteById(id);
    }
}
