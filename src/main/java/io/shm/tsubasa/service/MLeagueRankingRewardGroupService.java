package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MLeagueRankingRewardGroup;
import io.shm.tsubasa.repository.MLeagueRankingRewardGroupRepository;
import io.shm.tsubasa.service.dto.MLeagueRankingRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MLeagueRankingRewardGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MLeagueRankingRewardGroup}.
 */
@Service
@Transactional
public class MLeagueRankingRewardGroupService {

    private final Logger log = LoggerFactory.getLogger(MLeagueRankingRewardGroupService.class);

    private final MLeagueRankingRewardGroupRepository mLeagueRankingRewardGroupRepository;

    private final MLeagueRankingRewardGroupMapper mLeagueRankingRewardGroupMapper;

    public MLeagueRankingRewardGroupService(MLeagueRankingRewardGroupRepository mLeagueRankingRewardGroupRepository, MLeagueRankingRewardGroupMapper mLeagueRankingRewardGroupMapper) {
        this.mLeagueRankingRewardGroupRepository = mLeagueRankingRewardGroupRepository;
        this.mLeagueRankingRewardGroupMapper = mLeagueRankingRewardGroupMapper;
    }

    /**
     * Save a mLeagueRankingRewardGroup.
     *
     * @param mLeagueRankingRewardGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MLeagueRankingRewardGroupDTO save(MLeagueRankingRewardGroupDTO mLeagueRankingRewardGroupDTO) {
        log.debug("Request to save MLeagueRankingRewardGroup : {}", mLeagueRankingRewardGroupDTO);
        MLeagueRankingRewardGroup mLeagueRankingRewardGroup = mLeagueRankingRewardGroupMapper.toEntity(mLeagueRankingRewardGroupDTO);
        mLeagueRankingRewardGroup = mLeagueRankingRewardGroupRepository.save(mLeagueRankingRewardGroup);
        return mLeagueRankingRewardGroupMapper.toDto(mLeagueRankingRewardGroup);
    }

    /**
     * Get all the mLeagueRankingRewardGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MLeagueRankingRewardGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MLeagueRankingRewardGroups");
        return mLeagueRankingRewardGroupRepository.findAll(pageable)
            .map(mLeagueRankingRewardGroupMapper::toDto);
    }


    /**
     * Get one mLeagueRankingRewardGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MLeagueRankingRewardGroupDTO> findOne(Long id) {
        log.debug("Request to get MLeagueRankingRewardGroup : {}", id);
        return mLeagueRankingRewardGroupRepository.findById(id)
            .map(mLeagueRankingRewardGroupMapper::toDto);
    }

    /**
     * Delete the mLeagueRankingRewardGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MLeagueRankingRewardGroup : {}", id);
        mLeagueRankingRewardGroupRepository.deleteById(id);
    }
}
