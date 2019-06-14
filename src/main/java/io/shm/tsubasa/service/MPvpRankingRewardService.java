package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MPvpRankingReward;
import io.shm.tsubasa.repository.MPvpRankingRewardRepository;
import io.shm.tsubasa.service.dto.MPvpRankingRewardDTO;
import io.shm.tsubasa.service.mapper.MPvpRankingRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MPvpRankingReward}.
 */
@Service
@Transactional
public class MPvpRankingRewardService {

    private final Logger log = LoggerFactory.getLogger(MPvpRankingRewardService.class);

    private final MPvpRankingRewardRepository mPvpRankingRewardRepository;

    private final MPvpRankingRewardMapper mPvpRankingRewardMapper;

    public MPvpRankingRewardService(MPvpRankingRewardRepository mPvpRankingRewardRepository, MPvpRankingRewardMapper mPvpRankingRewardMapper) {
        this.mPvpRankingRewardRepository = mPvpRankingRewardRepository;
        this.mPvpRankingRewardMapper = mPvpRankingRewardMapper;
    }

    /**
     * Save a mPvpRankingReward.
     *
     * @param mPvpRankingRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MPvpRankingRewardDTO save(MPvpRankingRewardDTO mPvpRankingRewardDTO) {
        log.debug("Request to save MPvpRankingReward : {}", mPvpRankingRewardDTO);
        MPvpRankingReward mPvpRankingReward = mPvpRankingRewardMapper.toEntity(mPvpRankingRewardDTO);
        mPvpRankingReward = mPvpRankingRewardRepository.save(mPvpRankingReward);
        return mPvpRankingRewardMapper.toDto(mPvpRankingReward);
    }

    /**
     * Get all the mPvpRankingRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpRankingRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MPvpRankingRewards");
        return mPvpRankingRewardRepository.findAll(pageable)
            .map(mPvpRankingRewardMapper::toDto);
    }


    /**
     * Get one mPvpRankingReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MPvpRankingRewardDTO> findOne(Long id) {
        log.debug("Request to get MPvpRankingReward : {}", id);
        return mPvpRankingRewardRepository.findById(id)
            .map(mPvpRankingRewardMapper::toDto);
    }

    /**
     * Delete the mPvpRankingReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MPvpRankingReward : {}", id);
        mPvpRankingRewardRepository.deleteById(id);
    }
}
