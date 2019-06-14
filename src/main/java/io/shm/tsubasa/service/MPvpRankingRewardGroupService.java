package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MPvpRankingRewardGroup;
import io.shm.tsubasa.repository.MPvpRankingRewardGroupRepository;
import io.shm.tsubasa.service.dto.MPvpRankingRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MPvpRankingRewardGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MPvpRankingRewardGroup}.
 */
@Service
@Transactional
public class MPvpRankingRewardGroupService {

    private final Logger log = LoggerFactory.getLogger(MPvpRankingRewardGroupService.class);

    private final MPvpRankingRewardGroupRepository mPvpRankingRewardGroupRepository;

    private final MPvpRankingRewardGroupMapper mPvpRankingRewardGroupMapper;

    public MPvpRankingRewardGroupService(MPvpRankingRewardGroupRepository mPvpRankingRewardGroupRepository, MPvpRankingRewardGroupMapper mPvpRankingRewardGroupMapper) {
        this.mPvpRankingRewardGroupRepository = mPvpRankingRewardGroupRepository;
        this.mPvpRankingRewardGroupMapper = mPvpRankingRewardGroupMapper;
    }

    /**
     * Save a mPvpRankingRewardGroup.
     *
     * @param mPvpRankingRewardGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MPvpRankingRewardGroupDTO save(MPvpRankingRewardGroupDTO mPvpRankingRewardGroupDTO) {
        log.debug("Request to save MPvpRankingRewardGroup : {}", mPvpRankingRewardGroupDTO);
        MPvpRankingRewardGroup mPvpRankingRewardGroup = mPvpRankingRewardGroupMapper.toEntity(mPvpRankingRewardGroupDTO);
        mPvpRankingRewardGroup = mPvpRankingRewardGroupRepository.save(mPvpRankingRewardGroup);
        return mPvpRankingRewardGroupMapper.toDto(mPvpRankingRewardGroup);
    }

    /**
     * Get all the mPvpRankingRewardGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MPvpRankingRewardGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MPvpRankingRewardGroups");
        return mPvpRankingRewardGroupRepository.findAll(pageable)
            .map(mPvpRankingRewardGroupMapper::toDto);
    }


    /**
     * Get one mPvpRankingRewardGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MPvpRankingRewardGroupDTO> findOne(Long id) {
        log.debug("Request to get MPvpRankingRewardGroup : {}", id);
        return mPvpRankingRewardGroupRepository.findById(id)
            .map(mPvpRankingRewardGroupMapper::toDto);
    }

    /**
     * Delete the mPvpRankingRewardGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MPvpRankingRewardGroup : {}", id);
        mPvpRankingRewardGroupRepository.deleteById(id);
    }
}
