package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MLeagueRankingReward;
import io.shm.tsubasa.repository.MLeagueRankingRewardRepository;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardDTO;
import io.shm.tsubasa.service.mapper.MLeagueRankingRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MLeagueRankingReward}.
 */
@Service
@Transactional
public class MLeagueRankingRewardService {

    private final Logger log = LoggerFactory.getLogger(MLeagueRankingRewardService.class);

    private final MLeagueRankingRewardRepository mLeagueRankingRewardRepository;

    private final MLeagueRankingRewardMapper mLeagueRankingRewardMapper;

    public MLeagueRankingRewardService(MLeagueRankingRewardRepository mLeagueRankingRewardRepository, MLeagueRankingRewardMapper mLeagueRankingRewardMapper) {
        this.mLeagueRankingRewardRepository = mLeagueRankingRewardRepository;
        this.mLeagueRankingRewardMapper = mLeagueRankingRewardMapper;
    }

    /**
     * Save a mLeagueRankingReward.
     *
     * @param mLeagueRankingRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MLeagueRankingRewardDTO save(MLeagueRankingRewardDTO mLeagueRankingRewardDTO) {
        log.debug("Request to save MLeagueRankingReward : {}", mLeagueRankingRewardDTO);
        MLeagueRankingReward mLeagueRankingReward = mLeagueRankingRewardMapper.toEntity(mLeagueRankingRewardDTO);
        mLeagueRankingReward = mLeagueRankingRewardRepository.save(mLeagueRankingReward);
        return mLeagueRankingRewardMapper.toDto(mLeagueRankingReward);
    }

    /**
     * Get all the mLeagueRankingRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MLeagueRankingRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MLeagueRankingRewards");
        return mLeagueRankingRewardRepository.findAll(pageable)
            .map(mLeagueRankingRewardMapper::toDto);
    }


    /**
     * Get one mLeagueRankingReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MLeagueRankingRewardDTO> findOne(Long id) {
        log.debug("Request to get MLeagueRankingReward : {}", id);
        return mLeagueRankingRewardRepository.findById(id)
            .map(mLeagueRankingRewardMapper::toDto);
    }

    /**
     * Delete the mLeagueRankingReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MLeagueRankingReward : {}", id);
        mLeagueRankingRewardRepository.deleteById(id);
    }
}
