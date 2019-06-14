package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MWeeklyQuestTsubasaPointReward;
import io.shm.tsubasa.repository.MWeeklyQuestTsubasaPointRewardRepository;
import io.shm.tsubasa.service.dto.MWeeklyQuestTsubasaPointRewardDTO;
import io.shm.tsubasa.service.mapper.MWeeklyQuestTsubasaPointRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MWeeklyQuestTsubasaPointReward}.
 */
@Service
@Transactional
public class MWeeklyQuestTsubasaPointRewardService {

    private final Logger log = LoggerFactory.getLogger(MWeeklyQuestTsubasaPointRewardService.class);

    private final MWeeklyQuestTsubasaPointRewardRepository mWeeklyQuestTsubasaPointRewardRepository;

    private final MWeeklyQuestTsubasaPointRewardMapper mWeeklyQuestTsubasaPointRewardMapper;

    public MWeeklyQuestTsubasaPointRewardService(MWeeklyQuestTsubasaPointRewardRepository mWeeklyQuestTsubasaPointRewardRepository, MWeeklyQuestTsubasaPointRewardMapper mWeeklyQuestTsubasaPointRewardMapper) {
        this.mWeeklyQuestTsubasaPointRewardRepository = mWeeklyQuestTsubasaPointRewardRepository;
        this.mWeeklyQuestTsubasaPointRewardMapper = mWeeklyQuestTsubasaPointRewardMapper;
    }

    /**
     * Save a mWeeklyQuestTsubasaPointReward.
     *
     * @param mWeeklyQuestTsubasaPointRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MWeeklyQuestTsubasaPointRewardDTO save(MWeeklyQuestTsubasaPointRewardDTO mWeeklyQuestTsubasaPointRewardDTO) {
        log.debug("Request to save MWeeklyQuestTsubasaPointReward : {}", mWeeklyQuestTsubasaPointRewardDTO);
        MWeeklyQuestTsubasaPointReward mWeeklyQuestTsubasaPointReward = mWeeklyQuestTsubasaPointRewardMapper.toEntity(mWeeklyQuestTsubasaPointRewardDTO);
        mWeeklyQuestTsubasaPointReward = mWeeklyQuestTsubasaPointRewardRepository.save(mWeeklyQuestTsubasaPointReward);
        return mWeeklyQuestTsubasaPointRewardMapper.toDto(mWeeklyQuestTsubasaPointReward);
    }

    /**
     * Get all the mWeeklyQuestTsubasaPointRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MWeeklyQuestTsubasaPointRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MWeeklyQuestTsubasaPointRewards");
        return mWeeklyQuestTsubasaPointRewardRepository.findAll(pageable)
            .map(mWeeklyQuestTsubasaPointRewardMapper::toDto);
    }


    /**
     * Get one mWeeklyQuestTsubasaPointReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MWeeklyQuestTsubasaPointRewardDTO> findOne(Long id) {
        log.debug("Request to get MWeeklyQuestTsubasaPointReward : {}", id);
        return mWeeklyQuestTsubasaPointRewardRepository.findById(id)
            .map(mWeeklyQuestTsubasaPointRewardMapper::toDto);
    }

    /**
     * Delete the mWeeklyQuestTsubasaPointReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MWeeklyQuestTsubasaPointReward : {}", id);
        mWeeklyQuestTsubasaPointRewardRepository.deleteById(id);
    }
}
