package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MRegulatedLeagueRankingReward;
import io.shm.tsubasa.repository.MRegulatedLeagueRankingRewardRepository;
import io.shm.tsubasa.service.dto.MRegulatedLeagueRankingRewardDTO;
import io.shm.tsubasa.service.mapper.MRegulatedLeagueRankingRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MRegulatedLeagueRankingReward}.
 */
@Service
@Transactional
public class MRegulatedLeagueRankingRewardService {

    private final Logger log = LoggerFactory.getLogger(MRegulatedLeagueRankingRewardService.class);

    private final MRegulatedLeagueRankingRewardRepository mRegulatedLeagueRankingRewardRepository;

    private final MRegulatedLeagueRankingRewardMapper mRegulatedLeagueRankingRewardMapper;

    public MRegulatedLeagueRankingRewardService(MRegulatedLeagueRankingRewardRepository mRegulatedLeagueRankingRewardRepository, MRegulatedLeagueRankingRewardMapper mRegulatedLeagueRankingRewardMapper) {
        this.mRegulatedLeagueRankingRewardRepository = mRegulatedLeagueRankingRewardRepository;
        this.mRegulatedLeagueRankingRewardMapper = mRegulatedLeagueRankingRewardMapper;
    }

    /**
     * Save a mRegulatedLeagueRankingReward.
     *
     * @param mRegulatedLeagueRankingRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MRegulatedLeagueRankingRewardDTO save(MRegulatedLeagueRankingRewardDTO mRegulatedLeagueRankingRewardDTO) {
        log.debug("Request to save MRegulatedLeagueRankingReward : {}", mRegulatedLeagueRankingRewardDTO);
        MRegulatedLeagueRankingReward mRegulatedLeagueRankingReward = mRegulatedLeagueRankingRewardMapper.toEntity(mRegulatedLeagueRankingRewardDTO);
        mRegulatedLeagueRankingReward = mRegulatedLeagueRankingRewardRepository.save(mRegulatedLeagueRankingReward);
        return mRegulatedLeagueRankingRewardMapper.toDto(mRegulatedLeagueRankingReward);
    }

    /**
     * Get all the mRegulatedLeagueRankingRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MRegulatedLeagueRankingRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MRegulatedLeagueRankingRewards");
        return mRegulatedLeagueRankingRewardRepository.findAll(pageable)
            .map(mRegulatedLeagueRankingRewardMapper::toDto);
    }


    /**
     * Get one mRegulatedLeagueRankingReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MRegulatedLeagueRankingRewardDTO> findOne(Long id) {
        log.debug("Request to get MRegulatedLeagueRankingReward : {}", id);
        return mRegulatedLeagueRankingRewardRepository.findById(id)
            .map(mRegulatedLeagueRankingRewardMapper::toDto);
    }

    /**
     * Delete the mRegulatedLeagueRankingReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MRegulatedLeagueRankingReward : {}", id);
        mRegulatedLeagueRankingRewardRepository.deleteById(id);
    }
}
