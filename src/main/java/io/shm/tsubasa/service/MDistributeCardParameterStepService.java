package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MDistributeCardParameterStep;
import io.shm.tsubasa.repository.MDistributeCardParameterStepRepository;
import io.shm.tsubasa.service.dto.MDistributeCardParameterStepDTO;
import io.shm.tsubasa.service.mapper.MDistributeCardParameterStepMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MDistributeCardParameterStep}.
 */
@Service
@Transactional
public class MDistributeCardParameterStepService {

    private final Logger log = LoggerFactory.getLogger(MDistributeCardParameterStepService.class);

    private final MDistributeCardParameterStepRepository mDistributeCardParameterStepRepository;

    private final MDistributeCardParameterStepMapper mDistributeCardParameterStepMapper;

    public MDistributeCardParameterStepService(MDistributeCardParameterStepRepository mDistributeCardParameterStepRepository, MDistributeCardParameterStepMapper mDistributeCardParameterStepMapper) {
        this.mDistributeCardParameterStepRepository = mDistributeCardParameterStepRepository;
        this.mDistributeCardParameterStepMapper = mDistributeCardParameterStepMapper;
    }

    /**
     * Save a mDistributeCardParameterStep.
     *
     * @param mDistributeCardParameterStepDTO the entity to save.
     * @return the persisted entity.
     */
    public MDistributeCardParameterStepDTO save(MDistributeCardParameterStepDTO mDistributeCardParameterStepDTO) {
        log.debug("Request to save MDistributeCardParameterStep : {}", mDistributeCardParameterStepDTO);
        MDistributeCardParameterStep mDistributeCardParameterStep = mDistributeCardParameterStepMapper.toEntity(mDistributeCardParameterStepDTO);
        mDistributeCardParameterStep = mDistributeCardParameterStepRepository.save(mDistributeCardParameterStep);
        return mDistributeCardParameterStepMapper.toDto(mDistributeCardParameterStep);
    }

    /**
     * Get all the mDistributeCardParameterSteps.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MDistributeCardParameterStepDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MDistributeCardParameterSteps");
        return mDistributeCardParameterStepRepository.findAll(pageable)
            .map(mDistributeCardParameterStepMapper::toDto);
    }


    /**
     * Get one mDistributeCardParameterStep by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MDistributeCardParameterStepDTO> findOne(Long id) {
        log.debug("Request to get MDistributeCardParameterStep : {}", id);
        return mDistributeCardParameterStepRepository.findById(id)
            .map(mDistributeCardParameterStepMapper::toDto);
    }

    /**
     * Delete the mDistributeCardParameterStep by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MDistributeCardParameterStep : {}", id);
        mDistributeCardParameterStepRepository.deleteById(id);
    }
}
