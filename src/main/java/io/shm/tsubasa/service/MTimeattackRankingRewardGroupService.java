package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTimeattackRankingRewardGroup;
import io.shm.tsubasa.repository.MTimeattackRankingRewardGroupRepository;
import io.shm.tsubasa.service.dto.MTimeattackRankingRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MTimeattackRankingRewardGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTimeattackRankingRewardGroup}.
 */
@Service
@Transactional
public class MTimeattackRankingRewardGroupService {

    private final Logger log = LoggerFactory.getLogger(MTimeattackRankingRewardGroupService.class);

    private final MTimeattackRankingRewardGroupRepository mTimeattackRankingRewardGroupRepository;

    private final MTimeattackRankingRewardGroupMapper mTimeattackRankingRewardGroupMapper;

    public MTimeattackRankingRewardGroupService(MTimeattackRankingRewardGroupRepository mTimeattackRankingRewardGroupRepository, MTimeattackRankingRewardGroupMapper mTimeattackRankingRewardGroupMapper) {
        this.mTimeattackRankingRewardGroupRepository = mTimeattackRankingRewardGroupRepository;
        this.mTimeattackRankingRewardGroupMapper = mTimeattackRankingRewardGroupMapper;
    }

    /**
     * Save a mTimeattackRankingRewardGroup.
     *
     * @param mTimeattackRankingRewardGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MTimeattackRankingRewardGroupDTO save(MTimeattackRankingRewardGroupDTO mTimeattackRankingRewardGroupDTO) {
        log.debug("Request to save MTimeattackRankingRewardGroup : {}", mTimeattackRankingRewardGroupDTO);
        MTimeattackRankingRewardGroup mTimeattackRankingRewardGroup = mTimeattackRankingRewardGroupMapper.toEntity(mTimeattackRankingRewardGroupDTO);
        mTimeattackRankingRewardGroup = mTimeattackRankingRewardGroupRepository.save(mTimeattackRankingRewardGroup);
        return mTimeattackRankingRewardGroupMapper.toDto(mTimeattackRankingRewardGroup);
    }

    /**
     * Get all the mTimeattackRankingRewardGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTimeattackRankingRewardGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTimeattackRankingRewardGroups");
        return mTimeattackRankingRewardGroupRepository.findAll(pageable)
            .map(mTimeattackRankingRewardGroupMapper::toDto);
    }


    /**
     * Get one mTimeattackRankingRewardGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTimeattackRankingRewardGroupDTO> findOne(Long id) {
        log.debug("Request to get MTimeattackRankingRewardGroup : {}", id);
        return mTimeattackRankingRewardGroupRepository.findById(id)
            .map(mTimeattackRankingRewardGroupMapper::toDto);
    }

    /**
     * Delete the mTimeattackRankingRewardGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTimeattackRankingRewardGroup : {}", id);
        mTimeattackRankingRewardGroupRepository.deleteById(id);
    }
}
