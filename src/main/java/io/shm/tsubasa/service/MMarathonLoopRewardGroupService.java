package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MMarathonLoopRewardGroup;
import io.shm.tsubasa.repository.MMarathonLoopRewardGroupRepository;
import io.shm.tsubasa.service.dto.MMarathonLoopRewardGroupDTO;
import io.shm.tsubasa.service.mapper.MMarathonLoopRewardGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MMarathonLoopRewardGroup}.
 */
@Service
@Transactional
public class MMarathonLoopRewardGroupService {

    private final Logger log = LoggerFactory.getLogger(MMarathonLoopRewardGroupService.class);

    private final MMarathonLoopRewardGroupRepository mMarathonLoopRewardGroupRepository;

    private final MMarathonLoopRewardGroupMapper mMarathonLoopRewardGroupMapper;

    public MMarathonLoopRewardGroupService(MMarathonLoopRewardGroupRepository mMarathonLoopRewardGroupRepository, MMarathonLoopRewardGroupMapper mMarathonLoopRewardGroupMapper) {
        this.mMarathonLoopRewardGroupRepository = mMarathonLoopRewardGroupRepository;
        this.mMarathonLoopRewardGroupMapper = mMarathonLoopRewardGroupMapper;
    }

    /**
     * Save a mMarathonLoopRewardGroup.
     *
     * @param mMarathonLoopRewardGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MMarathonLoopRewardGroupDTO save(MMarathonLoopRewardGroupDTO mMarathonLoopRewardGroupDTO) {
        log.debug("Request to save MMarathonLoopRewardGroup : {}", mMarathonLoopRewardGroupDTO);
        MMarathonLoopRewardGroup mMarathonLoopRewardGroup = mMarathonLoopRewardGroupMapper.toEntity(mMarathonLoopRewardGroupDTO);
        mMarathonLoopRewardGroup = mMarathonLoopRewardGroupRepository.save(mMarathonLoopRewardGroup);
        return mMarathonLoopRewardGroupMapper.toDto(mMarathonLoopRewardGroup);
    }

    /**
     * Get all the mMarathonLoopRewardGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MMarathonLoopRewardGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MMarathonLoopRewardGroups");
        return mMarathonLoopRewardGroupRepository.findAll(pageable)
            .map(mMarathonLoopRewardGroupMapper::toDto);
    }


    /**
     * Get one mMarathonLoopRewardGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MMarathonLoopRewardGroupDTO> findOne(Long id) {
        log.debug("Request to get MMarathonLoopRewardGroup : {}", id);
        return mMarathonLoopRewardGroupRepository.findById(id)
            .map(mMarathonLoopRewardGroupMapper::toDto);
    }

    /**
     * Delete the mMarathonLoopRewardGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MMarathonLoopRewardGroup : {}", id);
        mMarathonLoopRewardGroupRepository.deleteById(id);
    }
}
