package io.shm.tsubasa.service;

import io.shm.tsubasa.domain.MTargetPlayableCardGroup;
import io.shm.tsubasa.repository.MTargetPlayableCardGroupRepository;
import io.shm.tsubasa.service.dto.MTargetPlayableCardGroupDTO;
import io.shm.tsubasa.service.mapper.MTargetPlayableCardGroupMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MTargetPlayableCardGroup}.
 */
@Service
@Transactional
public class MTargetPlayableCardGroupService {

    private final Logger log = LoggerFactory.getLogger(MTargetPlayableCardGroupService.class);

    private final MTargetPlayableCardGroupRepository mTargetPlayableCardGroupRepository;

    private final MTargetPlayableCardGroupMapper mTargetPlayableCardGroupMapper;

    public MTargetPlayableCardGroupService(MTargetPlayableCardGroupRepository mTargetPlayableCardGroupRepository, MTargetPlayableCardGroupMapper mTargetPlayableCardGroupMapper) {
        this.mTargetPlayableCardGroupRepository = mTargetPlayableCardGroupRepository;
        this.mTargetPlayableCardGroupMapper = mTargetPlayableCardGroupMapper;
    }

    /**
     * Save a mTargetPlayableCardGroup.
     *
     * @param mTargetPlayableCardGroupDTO the entity to save.
     * @return the persisted entity.
     */
    public MTargetPlayableCardGroupDTO save(MTargetPlayableCardGroupDTO mTargetPlayableCardGroupDTO) {
        log.debug("Request to save MTargetPlayableCardGroup : {}", mTargetPlayableCardGroupDTO);
        MTargetPlayableCardGroup mTargetPlayableCardGroup = mTargetPlayableCardGroupMapper.toEntity(mTargetPlayableCardGroupDTO);
        mTargetPlayableCardGroup = mTargetPlayableCardGroupRepository.save(mTargetPlayableCardGroup);
        return mTargetPlayableCardGroupMapper.toDto(mTargetPlayableCardGroup);
    }

    /**
     * Get all the mTargetPlayableCardGroups.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MTargetPlayableCardGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MTargetPlayableCardGroups");
        return mTargetPlayableCardGroupRepository.findAll(pageable)
            .map(mTargetPlayableCardGroupMapper::toDto);
    }


    /**
     * Get one mTargetPlayableCardGroup by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MTargetPlayableCardGroupDTO> findOne(Long id) {
        log.debug("Request to get MTargetPlayableCardGroup : {}", id);
        return mTargetPlayableCardGroupRepository.findById(id)
            .map(mTargetPlayableCardGroupMapper::toDto);
    }

    /**
     * Delete the mTargetPlayableCardGroup by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MTargetPlayableCardGroup : {}", id);
        mTargetPlayableCardGroupRepository.deleteById(id);
    }
}
