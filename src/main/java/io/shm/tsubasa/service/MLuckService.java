package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MLuck;
import io.shm.tsubasa.repository.MLuckRepository;
import io.shm.tsubasa.service.dto.MLuckDTO;
import io.shm.tsubasa.service.mapper.MLuckMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MLuck}.
 */
@Service
@Transactional
public class MLuckService {

    private final Logger log = LoggerFactory.getLogger(MLuckService.class);

    private final MLuckRepository mLuckRepository;

    private final MLuckMapper mLuckMapper;

    public MLuckService(MLuckRepository mLuckRepository, MLuckMapper mLuckMapper) {
        this.mLuckRepository = mLuckRepository;
        this.mLuckMapper = mLuckMapper;
    }

    /**
     * Save a mLuck.
     *
     * @param mLuckDTO the entity to save.
     * @return the persisted entity.
     */
    public MLuckDTO save(MLuckDTO mLuckDTO) {
        log.debug("Request to save MLuck : {}", mLuckDTO);
        MLuck mLuck = mLuckMapper.toEntity(mLuckDTO);
        mLuck = mLuckRepository.save(mLuck);
        return mLuckMapper.toDto(mLuck);
    }

    /**
     * Get all the mLucks.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MLuckDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MLucks");
        return mLuckRepository.findAll(pageable)
            .map(mLuckMapper::toDto);
    }


    /**
     * Get one mLuck by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MLuckDTO> findOne(Long id) {
        log.debug("Request to get MLuck : {}", id);
        return mLuckRepository.findById(id)
            .map(mLuckMapper::toDto);
    }

    /**
     * Delete the mLuck by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MLuck : {}", id);
        mLuckRepository.deleteById(id);
    }
}
