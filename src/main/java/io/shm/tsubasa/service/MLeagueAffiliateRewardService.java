package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MLeagueAffiliateReward;
import io.shm.tsubasa.repository.MLeagueAffiliateRewardRepository;
import io.shm.tsubasa.service.dto.MLeagueAffiliateRewardDTO;
import io.shm.tsubasa.service.mapper.MLeagueAffiliateRewardMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MLeagueAffiliateReward}.
 */
@Service
@Transactional
public class MLeagueAffiliateRewardService {

    private final Logger log = LoggerFactory.getLogger(MLeagueAffiliateRewardService.class);

    private final MLeagueAffiliateRewardRepository mLeagueAffiliateRewardRepository;

    private final MLeagueAffiliateRewardMapper mLeagueAffiliateRewardMapper;

    public MLeagueAffiliateRewardService(MLeagueAffiliateRewardRepository mLeagueAffiliateRewardRepository, MLeagueAffiliateRewardMapper mLeagueAffiliateRewardMapper) {
        this.mLeagueAffiliateRewardRepository = mLeagueAffiliateRewardRepository;
        this.mLeagueAffiliateRewardMapper = mLeagueAffiliateRewardMapper;
    }

    /**
     * Save a mLeagueAffiliateReward.
     *
     * @param mLeagueAffiliateRewardDTO the entity to save.
     * @return the persisted entity.
     */
    public MLeagueAffiliateRewardDTO save(MLeagueAffiliateRewardDTO mLeagueAffiliateRewardDTO) {
        log.debug("Request to save MLeagueAffiliateReward : {}", mLeagueAffiliateRewardDTO);
        MLeagueAffiliateReward mLeagueAffiliateReward = mLeagueAffiliateRewardMapper.toEntity(mLeagueAffiliateRewardDTO);
        mLeagueAffiliateReward = mLeagueAffiliateRewardRepository.save(mLeagueAffiliateReward);
        return mLeagueAffiliateRewardMapper.toDto(mLeagueAffiliateReward);
    }

    /**
     * Get all the mLeagueAffiliateRewards.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MLeagueAffiliateRewardDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MLeagueAffiliateRewards");
        return mLeagueAffiliateRewardRepository.findAll(pageable)
            .map(mLeagueAffiliateRewardMapper::toDto);
    }


    /**
     * Get one mLeagueAffiliateReward by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MLeagueAffiliateRewardDTO> findOne(Long id) {
        log.debug("Request to get MLeagueAffiliateReward : {}", id);
        return mLeagueAffiliateRewardRepository.findById(id)
            .map(mLeagueAffiliateRewardMapper::toDto);
    }

    /**
     * Delete the mLeagueAffiliateReward by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MLeagueAffiliateReward : {}", id);
        mLeagueAffiliateRewardRepository.deleteById(id);
    }
}
