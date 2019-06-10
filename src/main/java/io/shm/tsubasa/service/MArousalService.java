package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MArousal;
import io.shm.tsubasa.repository.MArousalRepository;
import io.shm.tsubasa.service.dto.MArousalDTO;
import io.shm.tsubasa.service.mapper.MArousalMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MArousal}.
 */
@Service
@Transactional
public class MArousalService {

    private final Logger log = LoggerFactory.getLogger(MArousalService.class);

    private final MArousalRepository mArousalRepository;

    private final MArousalMapper mArousalMapper;

    public MArousalService(MArousalRepository mArousalRepository, MArousalMapper mArousalMapper) {
        this.mArousalRepository = mArousalRepository;
        this.mArousalMapper = mArousalMapper;
    }

    /**
     * Save a mArousal.
     *
     * @param mArousalDTO the entity to save.
     * @return the persisted entity.
     */
    public MArousalDTO save(MArousalDTO mArousalDTO) {
        log.debug("Request to save MArousal : {}", mArousalDTO);
        MArousal mArousal = mArousalMapper.toEntity(mArousalDTO);
        mArousal = mArousalRepository.save(mArousal);
        return mArousalMapper.toDto(mArousal);
    }

    /**
     * Get all the mArousals.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MArousalDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MArousals");
        return mArousalRepository.findAll(pageable)
            .map(mArousalMapper::toDto);
    }


    /**
     * Get one mArousal by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MArousalDTO> findOne(Long id) {
        log.debug("Request to get MArousal : {}", id);
        return mArousalRepository.findById(id)
            .map(mArousalMapper::toDto);
    }

    /**
     * Delete the mArousal by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MArousal : {}", id);
        mArousalRepository.deleteById(id);
    }
}
