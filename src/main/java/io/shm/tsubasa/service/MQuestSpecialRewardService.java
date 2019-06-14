package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MQuestSpecialReward;
import io.shm.tsubasa.repository.MQuestSpecialRewardRepository;
import io.shm.tsubasa.service.dto.MQuestSpecialRewardDTO;
import io.shm.tsubasa.service.mapper.MQuestSpecialRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MQuestSpecialReward}.
 */
@Service
@Transactional
public class MQuestSpecialRewardService {

    private final Logger log = LoggerFactory.getLogger(MQuestSpecialRewardService.class);

    private final MQuestSpecialRewardRepository mQuestSpecialRewardRepository;

    private final MQuestSpecialRewardMapper mQuestSpecialRewardMapper;

    public MQuestSpecialRewardService(MQuestSpecialRewardRepository mQuestSpecialRewardRepository, MQuestSpecialRewardMapper mQuestSpecialRewardMapper) {
        this.mQuestSpecialRewardRepository = mQuestSpecialRewardRepository;
        this.mQuestSpecialRewardMapper = mQuestSpecialRewardMapper;
    }

    /**
     * Save a mQuestSpecialReward.
     *
     * @param mQuestSpecialRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MQuestSpecialRewardDTO save(MQuestSpecialRewardDTO mQuestSpecialRewardDTO) {
        log.debug("Request to save MQuestSpecialReward : {}", mQuestSpecialRewardDTO);
        MQuestSpecialReward mQuestSpecialReward = mQuestSpecialRewardMapper.toEntity(mQuestSpecialRewardDTO);
        mQuestSpecialReward = mQuestSpecialRewardRepository.save(mQuestSpecialReward);
        return mQuestSpecialRewardMapper.toDto(mQuestSpecialReward);
    }

    /**
     * Get all the mQuestSpecialRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestSpecialRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MQuestSpecialRewards");
        return mQuestSpecialRewardRepository.findAll(pageable)
            .map(mQuestSpecialRewardMapper::toDto);
    }


    /**
     * Get one mQuestSpecialReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MQuestSpecialRewardDTO> findOne(Long id) {
        log.debug("Request to get MQuestSpecialReward : {}", id);
        return mQuestSpecialRewardRepository.findById(id)
            .map(mQuestSpecialRewardMapper::toDto);
    }

    /**
     * Delete the mQuestSpecialReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MQuestSpecialReward : {}", id);
        mQuestSpecialRewardRepository.deleteById(id);
    }
}
