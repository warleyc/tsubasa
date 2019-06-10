package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MConstant;
import io.shm.tsubasa.repository.MConstantRepository;
import io.shm.tsubasa.service.dto.MConstantDTO;
import io.shm.tsubasa.service.mapper.MConstantMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MConstant}.
 */
@Service
@Transactional
public class MConstantService {

    private final Logger log = LoggerFactory.getLogger(MConstantService.class);

    private final MConstantRepository mConstantRepository;

    private final MConstantMapper mConstantMapper;

    public MConstantService(MConstantRepository mConstantRepository, MConstantMapper mConstantMapper) {
        this.mConstantRepository = mConstantRepository;
        this.mConstantMapper = mConstantMapper;
    }

    /**
     * Save a mConstant.
     *
     * @param mConstantDTO the entity to save.
     * @return the persisted entity.
     */
    public MConstantDTO save(MConstantDTO mConstantDTO) {
        log.debug("Request to save MConstant : {}", mConstantDTO);
        MConstant mConstant = mConstantMapper.toEntity(mConstantDTO);
        mConstant = mConstantRepository.save(mConstant);
        return mConstantMapper.toDto(mConstant);
    }

    /**
     * Get all the mConstants.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MConstantDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MConstants");
        return mConstantRepository.findAll(pageable)
            .map(mConstantMapper::toDto);
    }


    /**
     * Get one mConstant by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MConstantDTO> findOne(Long id) {
        log.debug("Request to get MConstant : {}", id);
        return mConstantRepository.findById(id)
            .map(mConstantMapper::toDto);
    }

    /**
     * Delete the mConstant by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MConstant : {}", id);
        mConstantRepository.deleteById(id);
    }
}
