package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMatchEnvironment;
import io.shm.tsubasa.repository.MMatchEnvironmentRepository;
import io.shm.tsubasa.service.dto.MMatchEnvironmentDTO;
import io.shm.tsubasa.service.mapper.MMatchEnvironmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMatchEnvironment}.
 */
@Service
@Transactional
public class MMatchEnvironmentService {

    private final Logger log = LoggerFactory.getLogger(MMatchEnvironmentService.class);

    private final MMatchEnvironmentRepository mMatchEnvironmentRepository;

    private final MMatchEnvironmentMapper mMatchEnvironmentMapper;

    public MMatchEnvironmentService(MMatchEnvironmentRepository mMatchEnvironmentRepository, MMatchEnvironmentMapper mMatchEnvironmentMapper) {
        this.mMatchEnvironmentRepository = mMatchEnvironmentRepository;
        this.mMatchEnvironmentMapper = mMatchEnvironmentMapper;
    }

    /**
     * Save a mMatchEnvironment.
     *
     * @param mMatchEnvironmentDTO the entity to save.
     * @return the persisted entity.
     */
    public MMatchEnvironmentDTO save(MMatchEnvironmentDTO mMatchEnvironmentDTO) {
        log.debug("Request to save MMatchEnvironment : {}", mMatchEnvironmentDTO);
        MMatchEnvironment mMatchEnvironment = mMatchEnvironmentMapper.toEntity(mMatchEnvironmentDTO);
        mMatchEnvironment = mMatchEnvironmentRepository.save(mMatchEnvironment);
        return mMatchEnvironmentMapper.toDto(mMatchEnvironment);
    }

    /**
     * Get all the mMatchEnvironments.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMatchEnvironmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMatchEnvironments");
        return mMatchEnvironmentRepository.findAll(pageable)
            .map(mMatchEnvironmentMapper::toDto);
    }


    /**
     * Get one mMatchEnvironment by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMatchEnvironmentDTO> findOne(Long id) {
        log.debug("Request to get MMatchEnvironment : {}", id);
        return mMatchEnvironmentRepository.findById(id)
            .map(mMatchEnvironmentMapper::toDto);
    }

    /**
     * Delete the mMatchEnvironment by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMatchEnvironment : {}", id);
        mMatchEnvironmentRepository.deleteById(id);
    }
}
