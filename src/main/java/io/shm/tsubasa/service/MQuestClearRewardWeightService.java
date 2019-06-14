package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MQuestClearRewardWeight;
import io.shm.tsubasa.repository.MQuestClearRewardWeightRepository;
import io.shm.tsubasa.service.dto.MQuestClearRewardWeightDTO;
import io.shm.tsubasa.service.mapper.MQuestClearRewardWeightMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MQuestClearRewardWeight}.
 */
@Service
@Transactional
public class MQuestClearRewardWeightService {

    private final Logger log = LoggerFactory.getLogger(MQuestClearRewardWeightService.class);

    private final MQuestClearRewardWeightRepository mQuestClearRewardWeightRepository;

    private final MQuestClearRewardWeightMapper mQuestClearRewardWeightMapper;

    public MQuestClearRewardWeightService(MQuestClearRewardWeightRepository mQuestClearRewardWeightRepository, MQuestClearRewardWeightMapper mQuestClearRewardWeightMapper) {
        this.mQuestClearRewardWeightRepository = mQuestClearRewardWeightRepository;
        this.mQuestClearRewardWeightMapper = mQuestClearRewardWeightMapper;
    }

    /**
     * Save a mQuestClearRewardWeight.
     *
     * @param mQuestClearRewardWeightDTO the entity to save.
     * @return the persisted entity.
     */
    public MQuestClearRewardWeightDTO save(MQuestClearRewardWeightDTO mQuestClearRewardWeightDTO) {
        log.debug("Request to save MQuestClearRewardWeight : {}", mQuestClearRewardWeightDTO);
        MQuestClearRewardWeight mQuestClearRewardWeight = mQuestClearRewardWeightMapper.toEntity(mQuestClearRewardWeightDTO);
        mQuestClearRewardWeight = mQuestClearRewardWeightRepository.save(mQuestClearRewardWeight);
        return mQuestClearRewardWeightMapper.toDto(mQuestClearRewardWeight);
    }

    /**
     * Get all the mQuestClearRewardWeights.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestClearRewardWeightDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MQuestClearRewardWeights");
        return mQuestClearRewardWeightRepository.findAll(pageable)
            .map(mQuestClearRewardWeightMapper::toDto);
    }


    /**
     * Get one mQuestClearRewardWeight by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MQuestClearRewardWeightDTO> findOne(Long id) {
        log.debug("Request to get MQuestClearRewardWeight : {}", id);
        return mQuestClearRewardWeightRepository.findById(id)
            .map(mQuestClearRewardWeightMapper::toDto);
    }

    /**
     * Delete the mQuestClearRewardWeight by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MQuestClearRewardWeight : {}", id);
        mQuestClearRewardWeightRepository.deleteById(id);
    }
}
