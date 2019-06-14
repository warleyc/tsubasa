package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMarathonRankingRewardGroup;
import io.shm.tsubasa.repository.MMarathonRankingRewardGroupRepository;
import io.shm.tsubasa.service.dto.MMarathonRankingRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MMarathonRankingRewardGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMarathonRankingRewardGroup}.
 */
@Service
@Transactional
public class MMarathonRankingRewardGroupService {

    private final Logger log = LoggerFactory.getLogger(MMarathonRankingRewardGroupService.class);

    private final MMarathonRankingRewardGroupRepository mMarathonRankingRewardGroupRepository;

    private final MMarathonRankingRewardGroupMapper mMarathonRankingRewardGroupMapper;

    public MMarathonRankingRewardGroupService(MMarathonRankingRewardGroupRepository mMarathonRankingRewardGroupRepository, MMarathonRankingRewardGroupMapper mMarathonRankingRewardGroupMapper) {
        this.mMarathonRankingRewardGroupRepository = mMarathonRankingRewardGroupRepository;
        this.mMarathonRankingRewardGroupMapper = mMarathonRankingRewardGroupMapper;
    }

    /**
     * Save a mMarathonRankingRewardGroup.
     *
     * @param mMarathonRankingRewardGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MMarathonRankingRewardGroupDTO save(MMarathonRankingRewardGroupDTO mMarathonRankingRewardGroupDTO) {
        log.debug("Request to save MMarathonRankingRewardGroup : {}", mMarathonRankingRewardGroupDTO);
        MMarathonRankingRewardGroup mMarathonRankingRewardGroup = mMarathonRankingRewardGroupMapper.toEntity(mMarathonRankingRewardGroupDTO);
        mMarathonRankingRewardGroup = mMarathonRankingRewardGroupRepository.save(mMarathonRankingRewardGroup);
        return mMarathonRankingRewardGroupMapper.toDto(mMarathonRankingRewardGroup);
    }

    /**
     * Get all the mMarathonRankingRewardGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonRankingRewardGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMarathonRankingRewardGroups");
        return mMarathonRankingRewardGroupRepository.findAll(pageable)
            .map(mMarathonRankingRewardGroupMapper::toDto);
    }


    /**
     * Get one mMarathonRankingRewardGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMarathonRankingRewardGroupDTO> findOne(Long id) {
        log.debug("Request to get MMarathonRankingRewardGroup : {}", id);
        return mMarathonRankingRewardGroupRepository.findById(id)
            .map(mMarathonRankingRewardGroupMapper::toDto);
    }

    /**
     * Delete the mMarathonRankingRewardGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMarathonRankingRewardGroup : {}", id);
        mMarathonRankingRewardGroupRepository.deleteById(id);
    }
}
