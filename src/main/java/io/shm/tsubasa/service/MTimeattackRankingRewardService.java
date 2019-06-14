package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTimeattackRankingReward;
import io.shm.tsubasa.repository.MTimeattackRankingRewardRepository;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardDTO;
import io.shm.tsubasa.service.mapper.MTimeattackRankingRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTimeattackRankingReward}.
 */
@Service
@Transactional
public class MTimeattackRankingRewardService {

    private final Logger log = LoggerFactory.getLogger(MTimeattackRankingRewardService.class);

    private final MTimeattackRankingRewardRepository mTimeattackRankingRewardRepository;

    private final MTimeattackRankingRewardMapper mTimeattackRankingRewardMapper;

    public MTimeattackRankingRewardService(MTimeattackRankingRewardRepository mTimeattackRankingRewardRepository, MTimeattackRankingRewardMapper mTimeattackRankingRewardMapper) {
        this.mTimeattackRankingRewardRepository = mTimeattackRankingRewardRepository;
        this.mTimeattackRankingRewardMapper = mTimeattackRankingRewardMapper;
    }

    /**
     * Save a mTimeattackRankingReward.
     *
     * @param mTimeattackRankingRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MTimeattackRankingRewardDTO save(MTimeattackRankingRewardDTO mTimeattackRankingRewardDTO) {
        log.debug("Request to save MTimeattackRankingReward : {}", mTimeattackRankingRewardDTO);
        MTimeattackRankingReward mTimeattackRankingReward = mTimeattackRankingRewardMapper.toEntity(mTimeattackRankingRewardDTO);
        mTimeattackRankingReward = mTimeattackRankingRewardRepository.save(mTimeattackRankingReward);
        return mTimeattackRankingRewardMapper.toDto(mTimeattackRankingReward);
    }

    /**
     * Get all the mTimeattackRankingRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTimeattackRankingRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTimeattackRankingRewards");
        return mTimeattackRankingRewardRepository.findAll(pageable)
            .map(mTimeattackRankingRewardMapper::toDto);
    }


    /**
     * Get one mTimeattackRankingReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTimeattackRankingRewardDTO> findOne(Long id) {
        log.debug("Request to get MTimeattackRankingReward : {}", id);
        return mTimeattackRankingRewardRepository.findById(id)
            .map(mTimeattackRankingRewardMapper::toDto);
    }

    /**
     * Delete the mTimeattackRankingReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTimeattackRankingReward : {}", id);
        mTimeattackRankingRewardRepository.deleteById(id);
    }
}
