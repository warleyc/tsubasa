package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTargetTeamGroup;
import io.shm.tsubasa.repository.MTargetTeamGroupRepository;
import io.shm.tsubasa.service.dto.MTargetTeamGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetTeamGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTargetTeamGroup}.
 */
@Service
@Transactional
public class MTargetTeamGroupService {

    private final Logger log = LoggerFactory.getLogger(MTargetTeamGroupService.class);

    private final MTargetTeamGroupRepository mTargetTeamGroupRepository;

    private final MTargetTeamGroupMapper mTargetTeamGroupMapper;

    public MTargetTeamGroupService(MTargetTeamGroupRepository mTargetTeamGroupRepository, MTargetTeamGroupMapper mTargetTeamGroupMapper) {
        this.mTargetTeamGroupRepository = mTargetTeamGroupRepository;
        this.mTargetTeamGroupMapper = mTargetTeamGroupMapper;
    }

    /**
     * Save a mTargetTeamGroup.
     *
     * @param mTargetTeamGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MTargetTeamGroupDTO save(MTargetTeamGroupDTO mTargetTeamGroupDTO) {
        log.debug("Request to save MTargetTeamGroup : {}", mTargetTeamGroupDTO);
        MTargetTeamGroup mTargetTeamGroup = mTargetTeamGroupMapper.toEntity(mTargetTeamGroupDTO);
        mTargetTeamGroup = mTargetTeamGroupRepository.save(mTargetTeamGroup);
        return mTargetTeamGroupMapper.toDto(mTargetTeamGroup);
    }

    /**
     * Get all the mTargetTeamGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetTeamGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTargetTeamGroups");
        return mTargetTeamGroupRepository.findAll(pageable)
            .map(mTargetTeamGroupMapper::toDto);
    }


    /**
     * Get one mTargetTeamGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTargetTeamGroupDTO> findOne(Long id) {
        log.debug("Request to get MTargetTeamGroup : {}", id);
        return mTargetTeamGroupRepository.findById(id)
            .map(mTargetTeamGroupMapper::toDto);
    }

    /**
     * Delete the mTargetTeamGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTargetTeamGroup : {}", id);
        mTargetTeamGroupRepository.deleteById(id);
    }
}
