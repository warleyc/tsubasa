package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTrainingCost;
import io.shm.tsubasa.repository.MTrainingCostRepository;
import io.shm.tsubasa.service.dto.MTrainingCostDTO;
import io.shm.tsubasa.service.mapper.MTrainingCostMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTrainingCost}.
 */
@Service
@Transactional
public class MTrainingCostService {

    private final Logger log = LoggerFactory.getLogger(MTrainingCostService.class);

    private final MTrainingCostRepository mTrainingCostRepository;

    private final MTrainingCostMapper mTrainingCostMapper;

    public MTrainingCostService(MTrainingCostRepository mTrainingCostRepository, MTrainingCostMapper mTrainingCostMapper) {
        this.mTrainingCostRepository = mTrainingCostRepository;
        this.mTrainingCostMapper = mTrainingCostMapper;
    }

    /**
     * Save a mTrainingCost.
     *
     * @param mTrainingCostDTO the entity to save.
     * @return the persisted entity.
     */
    public MTrainingCostDTO save(MTrainingCostDTO mTrainingCostDTO) {
        log.debug("Request to save MTrainingCost : {}", mTrainingCostDTO);
        MTrainingCost mTrainingCost = mTrainingCostMapper.toEntity(mTrainingCostDTO);
        mTrainingCost = mTrainingCostRepository.save(mTrainingCost);
        return mTrainingCostMapper.toDto(mTrainingCost);
    }

    /**
     * Get all the mTrainingCosts.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTrainingCostDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTrainingCosts");
        return mTrainingCostRepository.findAll(pageable)
            .map(mTrainingCostMapper::toDto);
    }


    /**
     * Get one mTrainingCost by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTrainingCostDTO> findOne(Long id) {
        log.debug("Request to get MTrainingCost : {}", id);
        return mTrainingCostRepository.findById(id)
            .map(mTrainingCostMapper::toDto);
    }

    /**
     * Delete the mTrainingCost by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTrainingCost : {}", id);
        mTrainingCostRepository.deleteById(id);
    }
}
