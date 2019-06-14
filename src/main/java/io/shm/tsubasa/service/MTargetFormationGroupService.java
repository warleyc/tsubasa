package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTargetFormationGroup;
import io.shm.tsubasa.repository.MTargetFormationGroupRepository;
import io.shm.tsubasa.service.dto.MTargetFormationGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetFormationGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTargetFormationGroup}.
 */
@Service
@Transactional
public class MTargetFormationGroupService {

    private final Logger log = LoggerFactory.getLogger(MTargetFormationGroupService.class);

    private final MTargetFormationGroupRepository mTargetFormationGroupRepository;

    private final MTargetFormationGroupMapper mTargetFormationGroupMapper;

    public MTargetFormationGroupService(MTargetFormationGroupRepository mTargetFormationGroupRepository, MTargetFormationGroupMapper mTargetFormationGroupMapper) {
        this.mTargetFormationGroupRepository = mTargetFormationGroupRepository;
        this.mTargetFormationGroupMapper = mTargetFormationGroupMapper;
    }

    /**
     * Save a mTargetFormationGroup.
     *
     * @param mTargetFormationGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MTargetFormationGroupDTO save(MTargetFormationGroupDTO mTargetFormationGroupDTO) {
        log.debug("Request to save MTargetFormationGroup : {}", mTargetFormationGroupDTO);
        MTargetFormationGroup mTargetFormationGroup = mTargetFormationGroupMapper.toEntity(mTargetFormationGroupDTO);
        mTargetFormationGroup = mTargetFormationGroupRepository.save(mTargetFormationGroup);
        return mTargetFormationGroupMapper.toDto(mTargetFormationGroup);
    }

    /**
     * Get all the mTargetFormationGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetFormationGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTargetFormationGroups");
        return mTargetFormationGroupRepository.findAll(pageable)
            .map(mTargetFormationGroupMapper::toDto);
    }


    /**
     * Get one mTargetFormationGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTargetFormationGroupDTO> findOne(Long id) {
        log.debug("Request to get MTargetFormationGroup : {}", id);
        return mTargetFormationGroupRepository.findById(id)
            .map(mTargetFormationGroupMapper::toDto);
    }

    /**
     * Delete the mTargetFormationGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTargetFormationGroup : {}", id);
        mTargetFormationGroupRepository.deleteById(id);
    }
}
