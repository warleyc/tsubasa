package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMarathonRankingReward;
import io.shm.tsubasa.repository.MMarathonRankingRewardRepository;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardDTO;
import io.shm.tsubasa.service.mapper.MMarathonRankingRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMarathonRankingReward}.
 */
@Service
@Transactional
public class MMarathonRankingRewardService {

    private final Logger log = LoggerFactory.getLogger(MMarathonRankingRewardService.class);

    private final MMarathonRankingRewardRepository mMarathonRankingRewardRepository;

    private final MMarathonRankingRewardMapper mMarathonRankingRewardMapper;

    public MMarathonRankingRewardService(MMarathonRankingRewardRepository mMarathonRankingRewardRepository, MMarathonRankingRewardMapper mMarathonRankingRewardMapper) {
        this.mMarathonRankingRewardRepository = mMarathonRankingRewardRepository;
        this.mMarathonRankingRewardMapper = mMarathonRankingRewardMapper;
    }

    /**
     * Save a mMarathonRankingReward.
     *
     * @param mMarathonRankingRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MMarathonRankingRewardDTO save(MMarathonRankingRewardDTO mMarathonRankingRewardDTO) {
        log.debug("Request to save MMarathonRankingReward : {}", mMarathonRankingRewardDTO);
        MMarathonRankingReward mMarathonRankingReward = mMarathonRankingRewardMapper.toEntity(mMarathonRankingRewardDTO);
        mMarathonRankingReward = mMarathonRankingRewardRepository.save(mMarathonRankingReward);
        return mMarathonRankingRewardMapper.toDto(mMarathonRankingReward);
    }

    /**
     * Get all the mMarathonRankingRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonRankingRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMarathonRankingRewards");
        return mMarathonRankingRewardRepository.findAll(pageable)
            .map(mMarathonRankingRewardMapper::toDto);
    }


    /**
     * Get one mMarathonRankingReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMarathonRankingRewardDTO> findOne(Long id) {
        log.debug("Request to get MMarathonRankingReward : {}", id);
        return mMarathonRankingRewardRepository.findById(id)
            .map(mMarathonRankingRewardMapper::toDto);
    }

    /**
     * Delete the mMarathonRankingReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMarathonRankingReward : {}", id);
        mMarathonRankingRewardRepository.deleteById(id);
    }
}
