package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTargetNationalityGroup;
import io.shm.tsubasa.repository.MTargetNationalityGroupRepository;
import io.shm.tsubasa.service.dto.MTargetNationalityGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetNationalityGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTargetNationalityGroup}.
 */
@Service
@Transactional
public class MTargetNationalityGroupService {

    private final Logger log = LoggerFactory.getLogger(MTargetNationalityGroupService.class);

    private final MTargetNationalityGroupRepository mTargetNationalityGroupRepository;

    private final MTargetNationalityGroupMapper mTargetNationalityGroupMapper;

    public MTargetNationalityGroupService(MTargetNationalityGroupRepository mTargetNationalityGroupRepository, MTargetNationalityGroupMapper mTargetNationalityGroupMapper) {
        this.mTargetNationalityGroupRepository = mTargetNationalityGroupRepository;
        this.mTargetNationalityGroupMapper = mTargetNationalityGroupMapper;
    }

    /**
     * Save a mTargetNationalityGroup.
     *
     * @param mTargetNationalityGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MTargetNationalityGroupDTO save(MTargetNationalityGroupDTO mTargetNationalityGroupDTO) {
        log.debug("Request to save MTargetNationalityGroup : {}", mTargetNationalityGroupDTO);
        MTargetNationalityGroup mTargetNationalityGroup = mTargetNationalityGroupMapper.toEntity(mTargetNationalityGroupDTO);
        mTargetNationalityGroup = mTargetNationalityGroupRepository.save(mTargetNationalityGroup);
        return mTargetNationalityGroupMapper.toDto(mTargetNationalityGroup);
    }

    /**
     * Get all the mTargetNationalityGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetNationalityGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTargetNationalityGroups");
        return mTargetNationalityGroupRepository.findAll(pageable)
            .map(mTargetNationalityGroupMapper::toDto);
    }


    /**
     * Get one mTargetNationalityGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTargetNationalityGroupDTO> findOne(Long id) {
        log.debug("Request to get MTargetNationalityGroup : {}", id);
        return mTargetNationalityGroupRepository.findById(id)
            .map(mTargetNationalityGroupMapper::toDto);
    }

    /**
     * Delete the mTargetNationalityGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTargetNationalityGroup : {}", id);
        mTargetNationalityGroupRepository.deleteById(id);
    }
}
