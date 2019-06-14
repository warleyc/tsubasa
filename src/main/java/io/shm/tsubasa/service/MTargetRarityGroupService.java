package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTargetRarityGroup;
import io.shm.tsubasa.repository.MTargetRarityGroupRepository;
import io.shm.tsubasa.service.dto.MTargetRarityGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetRarityGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTargetRarityGroup}.
 */
@Service
@Transactional
public class MTargetRarityGroupService {

    private final Logger log = LoggerFactory.getLogger(MTargetRarityGroupService.class);

    private final MTargetRarityGroupRepository mTargetRarityGroupRepository;

    private final MTargetRarityGroupMapper mTargetRarityGroupMapper;

    public MTargetRarityGroupService(MTargetRarityGroupRepository mTargetRarityGroupRepository, MTargetRarityGroupMapper mTargetRarityGroupMapper) {
        this.mTargetRarityGroupRepository = mTargetRarityGroupRepository;
        this.mTargetRarityGroupMapper = mTargetRarityGroupMapper;
    }

    /**
     * Save a mTargetRarityGroup.
     *
     * @param mTargetRarityGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MTargetRarityGroupDTO save(MTargetRarityGroupDTO mTargetRarityGroupDTO) {
        log.debug("Request to save MTargetRarityGroup : {}", mTargetRarityGroupDTO);
        MTargetRarityGroup mTargetRarityGroup = mTargetRarityGroupMapper.toEntity(mTargetRarityGroupDTO);
        mTargetRarityGroup = mTargetRarityGroupRepository.save(mTargetRarityGroup);
        return mTargetRarityGroupMapper.toDto(mTargetRarityGroup);
    }

    /**
     * Get all the mTargetRarityGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetRarityGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTargetRarityGroups");
        return mTargetRarityGroupRepository.findAll(pageable)
            .map(mTargetRarityGroupMapper::toDto);
    }


    /**
     * Get one mTargetRarityGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTargetRarityGroupDTO> findOne(Long id) {
        log.debug("Request to get MTargetRarityGroup : {}", id);
        return mTargetRarityGroupRepository.findById(id)
            .map(mTargetRarityGroupMapper::toDto);
    }

    /**
     * Delete the mTargetRarityGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTargetRarityGroup : {}", id);
        mTargetRarityGroupRepository.deleteById(id);
    }
}
