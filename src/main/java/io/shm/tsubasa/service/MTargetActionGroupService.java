package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTargetActionGroup;
import io.shm.tsubasa.repository.MTargetActionGroupRepository;
import io.shm.tsubasa.service.dto.MTargetActionGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetActionGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTargetActionGroup}.
 */
@Service
@Transactional
public class MTargetActionGroupService {

    private final Logger log = LoggerFactory.getLogger(MTargetActionGroupService.class);

    private final MTargetActionGroupRepository mTargetActionGroupRepository;

    private final MTargetActionGroupMapper mTargetActionGroupMapper;

    public MTargetActionGroupService(MTargetActionGroupRepository mTargetActionGroupRepository, MTargetActionGroupMapper mTargetActionGroupMapper) {
        this.mTargetActionGroupRepository = mTargetActionGroupRepository;
        this.mTargetActionGroupMapper = mTargetActionGroupMapper;
    }

    /**
     * Save a mTargetActionGroup.
     *
     * @param mTargetActionGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MTargetActionGroupDTO save(MTargetActionGroupDTO mTargetActionGroupDTO) {
        log.debug("Request to save MTargetActionGroup : {}", mTargetActionGroupDTO);
        MTargetActionGroup mTargetActionGroup = mTargetActionGroupMapper.toEntity(mTargetActionGroupDTO);
        mTargetActionGroup = mTargetActionGroupRepository.save(mTargetActionGroup);
        return mTargetActionGroupMapper.toDto(mTargetActionGroup);
    }

    /**
     * Get all the mTargetActionGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetActionGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTargetActionGroups");
        return mTargetActionGroupRepository.findAll(pageable)
            .map(mTargetActionGroupMapper::toDto);
    }


    /**
     * Get one mTargetActionGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTargetActionGroupDTO> findOne(Long id) {
        log.debug("Request to get MTargetActionGroup : {}", id);
        return mTargetActionGroupRepository.findById(id)
            .map(mTargetActionGroupMapper::toDto);
    }

    /**
     * Delete the mTargetActionGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTargetActionGroup : {}", id);
        mTargetActionGroupRepository.deleteById(id);
    }
}
