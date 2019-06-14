package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MQuestCoopReward;
import io.shm.tsubasa.repository.MQuestCoopRewardRepository;
import io.shm.tsubasa.service.dto.MQuestCoopRewardDTO;
import io.shm.tsubasa.service.mapper.MQuestCoopRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MQuestCoopReward}.
 */
@Service
@Transactional
public class MQuestCoopRewardService {

    private final Logger log = LoggerFactory.getLogger(MQuestCoopRewardService.class);

    private final MQuestCoopRewardRepository mQuestCoopRewardRepository;

    private final MQuestCoopRewardMapper mQuestCoopRewardMapper;

    public MQuestCoopRewardService(MQuestCoopRewardRepository mQuestCoopRewardRepository, MQuestCoopRewardMapper mQuestCoopRewardMapper) {
        this.mQuestCoopRewardRepository = mQuestCoopRewardRepository;
        this.mQuestCoopRewardMapper = mQuestCoopRewardMapper;
    }

    /**
     * Save a mQuestCoopReward.
     *
     * @param mQuestCoopRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MQuestCoopRewardDTO save(MQuestCoopRewardDTO mQuestCoopRewardDTO) {
        log.debug("Request to save MQuestCoopReward : {}", mQuestCoopRewardDTO);
        MQuestCoopReward mQuestCoopReward = mQuestCoopRewardMapper.toEntity(mQuestCoopRewardDTO);
        mQuestCoopReward = mQuestCoopRewardRepository.save(mQuestCoopReward);
        return mQuestCoopRewardMapper.toDto(mQuestCoopReward);
    }

    /**
     * Get all the mQuestCoopRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestCoopRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MQuestCoopRewards");
        return mQuestCoopRewardRepository.findAll(pageable)
            .map(mQuestCoopRewardMapper::toDto);
    }


    /**
     * Get one mQuestCoopReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MQuestCoopRewardDTO> findOne(Long id) {
        log.debug("Request to get MQuestCoopReward : {}", id);
        return mQuestCoopRewardRepository.findById(id)
            .map(mQuestCoopRewardMapper::toDto);
    }

    /**
     * Delete the mQuestCoopReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MQuestCoopReward : {}", id);
        mQuestCoopRewardRepository.deleteById(id);
    }
}
