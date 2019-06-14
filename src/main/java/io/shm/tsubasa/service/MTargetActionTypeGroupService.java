package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTargetActionTypeGroup;
import io.shm.tsubasa.repository.MTargetActionTypeGroupRepository;
import io.shm.tsubasa.service.dto.MTargetActionTypeGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetActionTypeGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTargetActionTypeGroup}.
 */
@Service
@Transactional
public class MTargetActionTypeGroupService {

    private final Logger log = LoggerFactory.getLogger(MTargetActionTypeGroupService.class);

    private final MTargetActionTypeGroupRepository mTargetActionTypeGroupRepository;

    private final MTargetActionTypeGroupMapper mTargetActionTypeGroupMapper;

    public MTargetActionTypeGroupService(MTargetActionTypeGroupRepository mTargetActionTypeGroupRepository, MTargetActionTypeGroupMapper mTargetActionTypeGroupMapper) {
        this.mTargetActionTypeGroupRepository = mTargetActionTypeGroupRepository;
        this.mTargetActionTypeGroupMapper = mTargetActionTypeGroupMapper;
    }

    /**
     * Save a mTargetActionTypeGroup.
     *
     * @param mTargetActionTypeGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MTargetActionTypeGroupDTO save(MTargetActionTypeGroupDTO mTargetActionTypeGroupDTO) {
        log.debug("Request to save MTargetActionTypeGroup : {}", mTargetActionTypeGroupDTO);
        MTargetActionTypeGroup mTargetActionTypeGroup = mTargetActionTypeGroupMapper.toEntity(mTargetActionTypeGroupDTO);
        mTargetActionTypeGroup = mTargetActionTypeGroupRepository.save(mTargetActionTypeGroup);
        return mTargetActionTypeGroupMapper.toDto(mTargetActionTypeGroup);
    }

    /**
     * Get all the mTargetActionTypeGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetActionTypeGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTargetActionTypeGroups");
        return mTargetActionTypeGroupRepository.findAll(pageable)
            .map(mTargetActionTypeGroupMapper::toDto);
    }


    /**
     * Get one mTargetActionTypeGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTargetActionTypeGroupDTO> findOne(Long id) {
        log.debug("Request to get MTargetActionTypeGroup : {}", id);
        return mTargetActionTypeGroupRepository.findById(id)
            .map(mTargetActionTypeGroupMapper::toDto);
    }

    /**
     * Delete the mTargetActionTypeGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTargetActionTypeGroup : {}", id);
        mTargetActionTypeGroupRepository.deleteById(id);
    }
}
