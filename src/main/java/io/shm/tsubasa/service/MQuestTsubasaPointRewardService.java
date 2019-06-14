package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MQuestTsubasaPointReward;
import io.shm.tsubasa.repository.MQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.dto.MQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MQuestTsubasaPointRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MQuestTsubasaPointReward}.
 */
@Service
@Transactional
public class MQuestTsubasaPointRewardService {

    private final Logger log = LoggerFactory.getLogger(MQuestTsubasaPointRewardService.class);

    private final MQuestTsubasaPointRewardRepository mQuestTsubasaPointRewardRepository;

    private final MQuestTsubasaPointRewardMapper mQuestTsubasaPointRewardMapper;

    public MQuestTsubasaPointRewardService(MQuestTsubasaPointRewardRepository mQuestTsubasaPointRewardRepository, MQuestTsubasaPointRewardMapper mQuestTsubasaPointRewardMapper) {
        this.mQuestTsubasaPointRewardRepository = mQuestTsubasaPointRewardRepository;
        this.mQuestTsubasaPointRewardMapper = mQuestTsubasaPointRewardMapper;
    }

    /**
     * Save a mQuestTsubasaPointReward.
     *
     * @param mQuestTsubasaPointRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MQuestTsubasaPointRewardDTO save(MQuestTsubasaPointRewardDTO mQuestTsubasaPointRewardDTO) {
        log.debug("Request to save MQuestTsubasaPointReward : {}", mQuestTsubasaPointRewardDTO);
        MQuestTsubasaPointReward mQuestTsubasaPointReward = mQuestTsubasaPointRewardMapper.toEntity(mQuestTsubasaPointRewardDTO);
        mQuestTsubasaPointReward = mQuestTsubasaPointRewardRepository.save(mQuestTsubasaPointReward);
        return mQuestTsubasaPointRewardMapper.toDto(mQuestTsubasaPointReward);
    }

    /**
     * Get all the mQuestTsubasaPointRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MQuestTsubasaPointRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MQuestTsubasaPointRewards");
        return mQuestTsubasaPointRewardRepository.findAll(pageable)
            .map(mQuestTsubasaPointRewardMapper::toDto);
    }


    /**
     * Get one mQuestTsubasaPointReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MQuestTsubasaPointRewardDTO> findOne(Long id) {
        log.debug("Request to get MQuestTsubasaPointReward : {}", id);
        return mQuestTsubasaPointRewardRepository.findById(id)
            .map(mQuestTsubasaPointRewardMapper::toDto);
    }

    /**
     * Delete the mQuestTsubasaPointReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MQuestTsubasaPointReward : {}", id);
        mQuestTsubasaPointRewardRepository.deleteById(id);
    }
}
