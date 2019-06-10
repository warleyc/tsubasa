package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MAreaActionWeight;
import io.shm.tsubasa.repository.MAreaActionWeightRepository;
import io.shm.tsubasa.service.dto.MAreaActionWeightDTO;
import io.shm.tsubasa.service.mapper.MAreaActionWeightMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MAreaActionWeight}.
 */
@Service
@Transactional
public class MAreaActionWeightService {

    private final Logger log = LoggerFactory.getLogger(MAreaActionWeightService.class);

    private final MAreaActionWeightRepository mAreaActionWeightRepository;

    private final MAreaActionWeightMapper mAreaActionWeightMapper;

    public MAreaActionWeightService(MAreaActionWeightRepository mAreaActionWeightRepository, MAreaActionWeightMapper mAreaActionWeightMapper) {
        this.mAreaActionWeightRepository = mAreaActionWeightRepository;
        this.mAreaActionWeightMapper = mAreaActionWeightMapper;
    }

    /**
     * Save a mAreaActionWeight.
     *
     * @param mAreaActionWeightDTO the entity to save.
     * @return the persisted entity.
     */
    public MAreaActionWeightDTO save(MAreaActionWeightDTO mAreaActionWeightDTO) {
        log.debug("Request to save MAreaActionWeight : {}", mAreaActionWeightDTO);
        MAreaActionWeight mAreaActionWeight = mAreaActionWeightMapper.toEntity(mAreaActionWeightDTO);
        mAreaActionWeight = mAreaActionWeightRepository.save(mAreaActionWeight);
        return mAreaActionWeightMapper.toDto(mAreaActionWeight);
    }

    /**
     * Get all the mAreaActionWeights.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MAreaActionWeightDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MAreaActionWeights");
        return mAreaActionWeightRepository.findAll(pageable)
            .map(mAreaActionWeightMapper::toDto);
    }


    /**
     * Get one mAreaActionWeight by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MAreaActionWeightDTO> findOne(Long id) {
        log.debug("Request to get MAreaActionWeight : {}", id);
        return mAreaActionWeightRepository.findById(id)
            .map(mAreaActionWeightMapper::toDto);
    }

    /**
     * Delete the mAreaActionWeight by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MAreaActionWeight : {}", id);
        mAreaActionWeightRepository.deleteById(id);
    }
}
