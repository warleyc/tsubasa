package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMarathonLoopReward;
import io.shm.tsubasa.repository.MMarathonLoopRewardRepository;
import io.shm.tsubasa.service.dto.MMarathonLoopRewardDTO;
import io.shm.tsubasa.service.mapper.MMarathonLoopRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMarathonLoopReward}.
 */
@Service
@Transactional
public class MMarathonLoopRewardService {

    private final Logger log = LoggerFactory.getLogger(MMarathonLoopRewardService.class);

    private final MMarathonLoopRewardRepository mMarathonLoopRewardRepository;

    private final MMarathonLoopRewardMapper mMarathonLoopRewardMapper;

    public MMarathonLoopRewardService(MMarathonLoopRewardRepository mMarathonLoopRewardRepository, MMarathonLoopRewardMapper mMarathonLoopRewardMapper) {
        this.mMarathonLoopRewardRepository = mMarathonLoopRewardRepository;
        this.mMarathonLoopRewardMapper = mMarathonLoopRewardMapper;
    }

    /**
     * Save a mMarathonLoopReward.
     *
     * @param mMarathonLoopRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MMarathonLoopRewardDTO save(MMarathonLoopRewardDTO mMarathonLoopRewardDTO) {
        log.debug("Request to save MMarathonLoopReward : {}", mMarathonLoopRewardDTO);
        MMarathonLoopReward mMarathonLoopReward = mMarathonLoopRewardMapper.toEntity(mMarathonLoopRewardDTO);
        mMarathonLoopReward = mMarathonLoopRewardRepository.save(mMarathonLoopReward);
        return mMarathonLoopRewardMapper.toDto(mMarathonLoopReward);
    }

    /**
     * Get all the mMarathonLoopRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonLoopRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMarathonLoopRewards");
        return mMarathonLoopRewardRepository.findAll(pageable)
            .map(mMarathonLoopRewardMapper::toDto);
    }


    /**
     * Get one mMarathonLoopReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMarathonLoopRewardDTO> findOne(Long id) {
        log.debug("Request to get MMarathonLoopReward : {}", id);
        return mMarathonLoopRewardRepository.findById(id)
            .map(mMarathonLoopRewardMapper::toDto);
    }

    /**
     * Delete the mMarathonLoopReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMarathonLoopReward : {}", id);
        mMarathonLoopRewardRepository.deleteById(id);
    }
}
