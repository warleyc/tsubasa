package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MPvpGradeRequirement;
import io.shm.tsubasa.repository.MPvpGradeRequirementRepository;
import io.shm.tsubasa.service.dto.MPvpGradeRequirementDTO;
import io.shm.tsubasa.service.mapper.MPvpGradeRequirementMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MPvpGradeRequirement}.
 */
@Service
@Transactional
public class MPvpGradeRequirementService {

    private final Logger log = LoggerFactory.getLogger(MPvpGradeRequirementService.class);

    private final MPvpGradeRequirementRepository mPvpGradeRequirementRepository;

    private final MPvpGradeRequirementMapper mPvpGradeRequirementMapper;

    public MPvpGradeRequirementService(MPvpGradeRequirementRepository mPvpGradeRequirementRepository, MPvpGradeRequirementMapper mPvpGradeRequirementMapper) {
        this.mPvpGradeRequirementRepository = mPvpGradeRequirementRepository;
        this.mPvpGradeRequirementMapper = mPvpGradeRequirementMapper;
    }

    /**
     * Save a mPvpGradeRequirement.
     *
     * @param mPvpGradeRequirementDTO the entity to save.
     * @return the persisted entity.
     */
    public MPvpGradeRequirementDTO save(MPvpGradeRequirementDTO mPvpGradeRequirementDTO) {
        log.debug("Request to save MPvpGradeRequirement : {}", mPvpGradeRequirementDTO);
        MPvpGradeRequirement mPvpGradeRequirement = mPvpGradeRequirementMapper.toEntity(mPvpGradeRequirementDTO);
        mPvpGradeRequirement = mPvpGradeRequirementRepository.save(mPvpGradeRequirement);
        return mPvpGradeRequirementMapper.toDto(mPvpGradeRequirement);
    }

    /**
     * Get all the mPvpGradeRequirements.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpGradeRequirementDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MPvpGradeRequirements");
        return mPvpGradeRequirementRepository.findAll(pageable)
            .map(mPvpGradeRequirementMapper::toDto);
    }


    /**
     * Get one mPvpGradeRequirement by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MPvpGradeRequirementDTO> findOne(Long id) {
        log.debug("Request to get MPvpGradeRequirement : {}", id);
        return mPvpGradeRequirementRepository.findById(id)
            .map(mPvpGradeRequirementMapper::toDto);
    }

    /**
     * Delete the mPvpGradeRequirement by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MPvpGradeRequirement : {}", id);
        mPvpGradeRequirementRepository.deleteById(id);
    }
}
